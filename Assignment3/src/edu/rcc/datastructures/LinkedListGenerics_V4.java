/**
 * Class based on Dr. Lehr's lecture, code, and notes and on Gaddis ch. 17
 */

package edu.rcc.datastructures;

public class LinkedListGenerics_V4<E extends Comparable<E>> {
	private GenericNode<E> head;
	private GenericNode<E> tail;
	Integer numberOfEntries;

	// Default constructor
	public LinkedListGenerics_V4() {
		tail = null;
		head = null;
		numberOfEntries = 0;
	}

	/**
	 * Inserts an entry at the back of the list.
	 * 
	 * @param number
	 */

	public void insert(E number) {
		GenericNode<E> newNode = new GenericNode<E>();
		newNode.setData(number);
		newNode.setNext(null);

		// Check if this is the only node in the list so far
		if (head == null) {
			head = newNode;
			numberOfEntries++;
		}
		// If not then put it at the end of the list
		else {
			// The tail points to the head to start from beginning of list
			tail = head;
			// Go through each node until you get to the end
			while (tail.getNext() != null) {
				tail = tail.getNext();
			}
			// Could also after this point the tail to the new node so we know
			// its address
			// Add the new node at the tail
			tail.setNext(newNode);
			// Increase the number of entries in the list
			numberOfEntries++;
		}
	}

	/**
	 * Deletes an entry from the list. It maintains the original order.
	 * 
	 * @param entry
	 * @return boolean True if entry was deleted, false otherwise
	 */
	public boolean delete(E entry) throws MyEmptyStackException {
		if (!isEmpty()) {
			boolean deleted = false;
			GenericNode<E> previousNode = tail = head;

			// Test the first node to see if the entry is there
			if (head.getData().compareTo(entry) == 0) {
				head = head.getNext();
				previousNode = null; 
				tail = null;
				deleted = true;
				numberOfEntries--;
			}
			// If not then go through the rest of the nodes
			else {
				// Stop when we have found it or we reach the end of the list
				while (!deleted && tail != null) {
					if (tail.getData().compareTo(entry) == 0) {
						deleted = true;
						// Change the link set the previousNode's internal
						// reference
						// variable equal to the the current node's internal
						// reference variable
						previousNode.setNext(tail.getNext());
						// Delete the node
						tail = null;
						numberOfEntries--;
					} else {
						previousNode = tail;// Remember the previous GenericNode
						tail = tail.getNext(); // Go to the next node
					}
				}
			}
			return deleted;
		} else {
			throw new MyEmptyStackException(numberOfEntries);
		}
	}

	public E remove(E entry) throws MyEmptyStackException {
		if (!isEmpty()) {
			E returnStuff = null;
			GenericNode<E> previous = tail = head;
			boolean deleted = false;

			// If the node is the first spot
			if (head.getData().compareTo(entry) == 0) {
				returnStuff = head.getData();// The node to return
				head = head.getNext();// Change the head node to be the next one
				previous = tail = null;// Delete the node
				// reduce the number of entries.
				numberOfEntries--;
			} else {
				while (!deleted && tail != null) {
					if (tail.getData().compareTo(entry) == 0) {
						deleted = true;// To stop the loop
						// Change where the nodes are linked.
						previous.setNext((tail.getNext()));
						// This is the node to delete. Prepare to return it.
						returnStuff = tail.getData();
						// Delete the entry
						tail = null;
						// Reduce the number of entries in the list
						numberOfEntries--;
					} else {
						// keep traversing the list until we get to the end
						previous = tail;
						tail = tail.getNext();
					}
				}
			}
			return returnStuff;
		} else {
			throw new MyEmptyStackException(numberOfEntries);
		}
	}

	/**
	 * Clear the whole list of all nodes
	 */

	public void clear() throws MyEmptyStackException {
		if (!isEmpty()) {
			while (head != null) {
				// Go to the current node at the front of the list.
				tail = head;
				// Change the front/head node to be the next node (traverse the
				// list).
				head = head.getNext();
				// Delete the current node.
				tail = null;
				numberOfEntries--;
			}
		} else {
			throw new MyEmptyStackException(numberOfEntries);
		}
	}

	/**
	 * Test if the list is empty
	 */
	public boolean isEmpty() {
		// We only need to check if the front node is null
		// If it is then we have an empty list.
		if (head == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gives you the total number of entries in the list.
	 * 
	 * @return size
	 */
	public int getSize() {
		return numberOfEntries;
	}

	/**
	 * Display the list
	 */

	public void displayList() {
		if (head == null) {
			System.out.println("List is empty!");
		} else {
			// Start at the beginning
			tail = head;
			// Traverse until you get to the end
			while (tail != null) {
				// Print the current node's data
				System.out.println(tail.getData());
				// Go to the next one
				tail = tail.getNext();
			}
		}
	}

}

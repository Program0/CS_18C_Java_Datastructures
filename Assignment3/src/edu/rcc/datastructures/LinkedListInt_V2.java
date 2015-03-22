/**
 * Class based on Dr. Lehr's lecture, code, and notes and on Gaddis ch. 17
 */

package edu.rcc.datastructures;

public class LinkedListInt_V2 {
	private Node head;
	private Node tail;
	int numberOfEntries;

	// Default constructor
	public LinkedListInt_V2() {
		tail = null;
		head = null;
		numberOfEntries = 0;
	}

	/**
	 * Inserts an entry at the back of the list.
	 * 
	 * @param number
	 */

	public void insert(int number) {
		Node newNode = new Node();
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
	public boolean delete(int entry) {
		boolean deleted = false;
		Node previousNode = tail = head;

		// Test the first node to see if the entry is there
		if (head.getData() == entry) {
			head = head.getNext();
			previousNode = tail = null;
			deleted = true;
		}
		// If not then go through the rest of the nodes
		else {
			// Stop when we have found it or we reach the end of the list
			while (!deleted && tail != null) {
				if (tail.getData() == entry) {
					deleted = true;
					// Change the list. Set the previousNode's internal reference
					// variable equal to the the current node's internal
					// reference variable
					previousNode.setNext(tail.getNext());
					// Delete the node
					tail = null;
				} else {
					previousNode = tail;// Remember the previous Node
					tail = tail.getNext(); // Go to the next node
				}
			}
		}
		return deleted;
	}

	/**
	 * Clear the whole list of all nodes
	 */

	public void clear() {
		while (head != null) {
			// Go to the current node at the front of the list.
			tail = head;
			// Change the front/head node to be the next node (traverse the
			// list).
			head = head.getNext();
			// Delete the current node.
			tail = null;
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

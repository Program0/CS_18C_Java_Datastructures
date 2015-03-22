/**
 * Class based on Carrano chapter 3 for a singly linked list with only one reference.
 * The first node created becomes the tail. New nodes become the head or front.
 * With the exception of insertion, this does not maintain a sequential order after it 
 * deletes a node.
 */

package edu.rcc.datastructures;

public class LinkedListInt_V1 {

	private Node head; //reference to hold address of front node
	private int numberOfEntries;

	/* constructors */
	public LinkedListInt_V1() {
		head = null;
		numberOfEntries = 0;
	}

	/**
	 * Inserts an entry into the list by putting it at the front of the list.
	 * The node created first keeps its newNode.next as null and becomes the tail.
	 * Subsequent nodes are then given the first nodes newNode.net and 
	 * become the head until a new node is added.
	 * @param number
	 */
	public void insert(int number) {
		Node newNode = new Node(number);
		newNode.setNext(head);
		head = newNode;
		numberOfEntries++;
	}

	/**
	 * Removes an entry from the list if found.
	 * The front node is removed after its data is copied to the node
	 * where the entry was originally found.
	 * @param number
	 * @return boolean 
	 * True if the entry was removed, or false otherwise.
	 */
	public boolean delete(int number) {
		boolean result = false;
		Node nodeN = getReferenceTo(number); // find the entry to delete

		// If the entry was found copy the head's data into the node where the
		// entry was found then just change the head to point to the next node
		// and decrement the number of entries in the list
		if (nodeN != null) {
			nodeN.setData(head.getData());
			head = head.getNext();
			numberOfEntries--;
			result = true;
		}
		return result;
	}

	/**
	 * Clears the list of all entries
	 */

	public void clear() {
		// Keep removing entries until the list is empty
		while (!isEmpty()) {
			remove();
		}
	}

	/**
	 * Checks if the list is empty.
	 * 
	 * @return True if the list is empty, false otherwise.
	 */
	public boolean isEmpty() {
		// Make sure the number of entries is 0
		if (numberOfEntries == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Show what it is in the list currently.
	 */

	public void displayList() {
		// Prepare an index and a temporary node to access the list
		int index = 0;
		Node currentNode = head;
		// Make sure we don't go past the total number of entries
		// and to stop only at the last node
		while (index < numberOfEntries && currentNode != null) {
			// print out the data
			System.out.println(currentNode.getData());
			// go to the next node
			currentNode = currentNode.getNext();
		}
	}

	/**
	 * Deletes an unspecified entry
	 * 
	 * @return Returns the data field value of the entry that was removed
	 */
	private int remove() {
		int result = 0;// Initialize result so there won't be errors.
		if (head != null) {
			result = head.getData();// Set result to what will be removed.
			head = head.getNext(); // Get rid of first node on the list (the front).
			numberOfEntries--;
		}
		return result;
	}

	/* Helper functions */

	/**
	 * Search for an entry within the list.
	 * Searches for an entry and returns a reference to the node containing the entry.
	 * @param int
	 * @return Node 
	 */
	private Node getReferenceTo(int number) {
		boolean found = false; // If we found the entry
		Node currentNode = head; // temporary node to hold reference
		// Keep looping until we either find number or reach the end of the list
		while (!found && currentNode != null) {
			if (number == currentNode.getData()) {
				found = true;
			} else {
				// go to the next node
				currentNode = currentNode.getNext();
			}
		}
		return currentNode;
	}
}

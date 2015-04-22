package edu.rcc.datastructures;

public class DoublyLinkedList<E> {

	/* Constructors */
	public DoublyLinkedList() {
		head = tail = null;
		numberOfEntries = 0;
	}

	/* Instantiating object with one entry and null references */
	public DoublyLinkedList(E entry) {
		head = tail = null;
		Node<E> newNode = new Node<E>(entry);
		head = tail = newNode;
		numberOfEntries++;
	}

	/**
	 * Pushes an entry onto the top of the list.
	 * 
	 * @param entry
	 */
	public void pushToFront(E entry) {

		Node<E> newNode = new Node<E>(entry);
		// We only need to manipulate the head after the first entry.
		// For the first node the head and the tail are the same
		if (isEmpty()) {
			tail = newNode;
			head = newNode;
			numberOfEntries++;
		} else {
			// The new node is the new head and needs to point/link to the next
			// node
			newNode.next = head;
			// The node behind needs to point to the node in front of it now
			// to link to it.
			head.previous = newNode;
			// Make the new node the head/top
			head = newNode;
			numberOfEntries++;
		}

	}

	public void pushToBack(E entry) {
		// We only need to manipulate the tail after the first entry since we
		// are adding
		// at the back.
		Node<E> newNode = new Node<E>(entry);
		// We only need to manipulate the head after the first entry.
		// For the first node the head and the tail are the same
		if (isEmpty()) {
			tail = newNode;
			head = newNode;
			numberOfEntries++;
		} else {
			// The new node is the new tail and needs to point/link to the node
			// before it
			newNode.previous = tail;
			// The node in front needs to point to the node behind it
			tail.next = newNode;
			// Change the tail to be the new node
			tail = newNode;
			numberOfEntries++;
		}
	}

	/**
	 * Removes an entry from the front/first element in the list
	 * 
	 * @return <E> The entry at the front/top of the list
	 */
	public E popFromFront() throws EmptyListException {
		// If the stack is not empty pop from the front
		if (!isEmpty()) {
			Node<E> returnNode = new Node<E>();
			// Set the node to return equal to the front/top
			returnNode = head;
			// If the current node is the only node
			if (head == tail) {
				// Clear the list of the only node
				head = null;
				tail = null;
				numberOfEntries--;
				return returnNode.data;
			} else {

				// Change the head to be the one in front of it
				head = head.next;
				// Delete the old head. Remove the links to the one before and
				// after it.
				head.previous = null;
				returnNode.next = null;
				// Reduce the number of elements in the list
				numberOfEntries--;
				return returnNode.data;
			}

		} else {
			throw new EmptyListException(numberOfEntries);
		}
	}

	/**
	 * Removes and returns the last/back element in the list
	 * 
	 * @return <E> The entry at the bottom/back of the list
	 */

	public E popFromBack() throws EmptyListException {
		// If the stack is not empty pop from the back
		if (!isEmpty()) {

			Node<E> returnNode = new Node<E>();
			// Set the node to return equal to the back
			returnNode = tail;
			// If there is only one node in the list
			if (head == tail) {
				// Clear the list and return the data
				tail = null;
				head = null;
				numberOfEntries--;
				return returnNode.data;
			} else {
				// Change the tail to be the one behind it
				tail = tail.previous;
				// Delete the old tail. Remove the links to the one before and
				// after it.
				tail.next = null;
				returnNode.previous = null;
				// Reduce the number of elements in the list
				numberOfEntries--;
				return returnNode.data;
			}
		} else {
			throw new EmptyListException(numberOfEntries);
		}
	}

	/**
	 * Empties the entire list
	 */
	public void clear() throws EmptyListException {
		// If the list is not empty, then empty it.
		if (!isEmpty()) {
			head = null;
			tail = null;
			numberOfEntries = 0;
		} else {
			throw new EmptyListException(numberOfEntries);
		}
	}

	/**
	 * Returns whether there are any elements in the list
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return (numberOfEntries == 0);
	}

	public int size() {
		return numberOfEntries;
	}

	/**
	 * Print the contents of the list in order
	 */
	public void displayList() {
		int index = 0;// So we don't exceed the number of entries in the list
		Node<E> tempNode = new Node<E>();// To traverse the list
		tempNode = head;
		while (tempNode != null && index < numberOfEntries) {
			System.out.println(tempNode.data);
			tempNode = tempNode.next;
		}
	}

	// Node nested class
	private static class Node<E> {

		/* Node Constructors */
		public Node() {
			next = null;
		}

		public Node(E data) {
			this.data = data;
			this.next = null;
			this.previous = null;
		}

		/* Node member variables */
		private E data;
		// The head will always have the previous node member variable null
		// and the tail will always have its next node member variable null
		private Node<E> next;
		private Node<E> previous;
	}

	/* DoublyLinkedList Member variables */
	private Node<E> head;// Reference to top/head node
	private Node<E> tail;// Reference to last/tail node
	private int numberOfEntries;
}

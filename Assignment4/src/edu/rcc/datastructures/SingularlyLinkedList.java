package edu.rcc.datastructures;

public class SingularlyLinkedList<E> {

	/**
	 * Constructor makes an empty list
	 */
	public SingularlyLinkedList() {
		head = null;
		numberOfEntries = 0;
	}

	/**
	 * Creates a list with one item with the given entry
	 * 
	 * @param Receives an entry of type E
	 */
	public SingularlyLinkedList(E entry) {
		head = null;
		SingleNode<E> node = new SingleNode<E>(entry, head);
		head = node;
		numberOfEntries++;
	}

	/**
	 * Returns true if the list is empty
	 * 
	 * @return True if the list is empty; false otherwise
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Adds an entry to the front of the list *
	 * 
	 * @param Receives
	 *            an entry of type <E>
	 */

	public void pushToFront(E entry) {
		// Create a new node to the list
		SingleNode<E> node = new SingleNode<E>(entry, head);
		// node.next = head;
		head = node;// The new node is the head now.
		numberOfEntries++;

	}

	/**
	 * Adds an entry to the back of the list.
	 * 
	 * @param entry
	 */
	public void pushToBack(E entry) {
		// Create a new node, set its data member to entry and its
		// node variable equal to the head
		SingleNode<E> newNode = new SingleNode<E>();
		newNode.data = entry;
		if (head == null) {
			// Put the new node at the head if the list is empty
			head = newNode;
			numberOfEntries++;
		} else {
			// Create a tail to traverse the list and start it at the head
			SingleNode<E> tail = new SingleNode<E>();
			tail = head;
			// Find the end of the list
			while (tail.next != null) {
				tail = tail.next;
			}
			numberOfEntries++;
			// Add the newNode to the back of the node.
			tail.next = newNode;
		}
	}

	/**
	 * Pops the entry at the top. Throws an EmptyStackException if the list is
	 * empty.
	 * 
	 * @return <E> The entry at the top of the stack
	 * @throws An EmptyListException
	 */
	public E popFromFront() throws EmptyListException {
		// Make sure the list is not empty
		if (head != null) {
			// Set the node to return equal to the head
			// then change the head to be the next node
			SingleNode<E> returnNode = new SingleNode<E>();
			returnNode = head;
			// Delete the top node
			head = head.next;
			numberOfEntries--;
			return returnNode.data;
		} else {
			throw new EmptyListException(numberOfEntries);
		}
	}

	/**
	 * Pops the entry at the bottom/back of the stack. Throws an
	 * EmptyStackException if the list is empty 
	 * @return <E> The entry at the back of the stack
	 * @throws An EmptyListException
	 */
	public E popFromBack() throws EmptyListException{
		// Make sure the list is not empty
		if (head != null) {
			// Nodes to traverse the list and to return the node at the
			// end of the list.
			SingleNode<E> returnNode = new SingleNode<E>();
			SingleNode<E> tailNode = new SingleNode<E>();
			returnNode = tailNode = head;
			int index = 0;
			while (tailNode.next != null && index < numberOfEntries) {
				returnNode = tailNode;
				tailNode = tailNode.next;
			}
			// Delete the bottom node
			returnNode.next = null;
			numberOfEntries--;
			return returnNode.data;
		} else {
			throw new EmptyListException(numberOfEntries);
		}
	}

	/**
	 * Empties the list. Throws and emptyStackException
	 * @throws An EmptyListException
	 */
	public void clear() throws EmptyListException{

		if (head != null) {
			head = null;
			numberOfEntries = 0;
		} else {
			throw new EmptyListException(numberOfEntries);
		}
	}

	public int size() {
		return numberOfEntries;
	}

	/**
	 * Print the contents of the list in order
	 */
	public void displayList() {
		int index = 0;// So we don't exceed the number of entries in the list
		SingleNode<E> tempNode = new SingleNode<E>();// To traverse the list
		tempNode = head;
		while (tempNode != null && index < numberOfEntries) {
			System.out.println(tempNode.data);
			tempNode = tempNode.next;
		}
	}

	// Nested class
	private static class SingleNode<E> {
		private E data;
		private SingleNode<E> next;

		/* SingleNode Constructors */
		public SingleNode() {
			next = null;
		}

		public SingleNode(E data, SingleNode<E> next) {
			this.data = data;
			this.next = next;
		}
	}

	/* Member variables */
	private SingleNode<E> head;// Reference to top/head node
	private int numberOfEntries;
}

package edu.rcc.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PriorityLinkedList<E extends Comparable<? super E>> {


	PriorityLinkedList() {
		size = 0;
		head = tail = null;
	}

	// Main constructor
	PriorityLinkedList(E entry) {
		// Allocate memory for the new entry
		Node<E> newNode = new Node<E>(entry);
		// Increase the size and set this as the head/tail
		size++;
		tail = head = newNode;
	}
		
	/***********************************************************************
	 *************************** Mutator Functions**************************
	 ***********************************************************************/

	/**
	 * Adds an entry to the back of the list.
	 * 
	 * @param entry
	 */
	public void queue(E entry) {
		// We only need to manipulate the tail after the first entry since we
		// are adding
		// at the back.
		Node<E> newNode = new Node<E>(entry);
		// We only need to manipulate the head after the first entry.
		// For the first node the head and the tail are the same
		if (isEmpty()) {
			tail = newNode;
			head = newNode;
			size++;
		} else {
			// The new node is the new tail and needs to point/link
			// to the node before it
			newNode.previous = tail;
			// The node in front needs to point to the node behind it
			tail.next = newNode;
			// Change the tail to be the new node
			tail = newNode;
			size++;
		}

	}

	/**
	 * Delete the item at the front of the list. Returns the entry deleted or
	 * null if the queue is empty.
	 * 
	 * @return
	 */
	public E dequeue() {
		E item = null;
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
				size--;
				item = returnNode.data;
			} else {

				// Change the head to be the one in front of it
				head = head.next;
				// Delete the old head. Remove the links to the one before and
				// after it.
				head.previous = null;
				returnNode.next = null;
				// Reduce the number of elements in the queue
				size--;
				item = returnNode.data;
			}
		}
		return item;
	}
	
    /**
	 * Returns true if an entry is in the list and moves the entry
	 * to the front otherwise it returns false and does nothing
	 * 
	 * @param entry An entry to search for in the list.
	 * If it is in the list it will be moved to the front
	 * @return boolean Returns true if in the list, false otherwise
	 */
	public boolean contains(E entry) {
		// If the list is not empty traverse it.
		if (!isEmpty()) {
			// Cursor to traverse and change the order of the node
			Node<E> cursor = new Node<E>();
			cursor = head;
			while (cursor != null) {
				if (cursor.data.compareTo(entry) == 0) {

					// If it is the last node shift move it to the front	
					if (cursor.next == null
							&& cursor.previous != null) {
						
						// The previous node before cursor is now the last
						// node
						cursor.previous.next=null;
						// The head node is now the next node after cursor
						cursor.next=head;
						// Cursor is now the node before head
						head.previous=cursor;
						// Cursor is now the new head
						cursor.previous=null;
						head=cursor;						
					}
					// If it is in between two other nodes
					else if (cursor.next != null
							&& cursor.previous != null) {
						// Link the nodes on either side of cursor together
						// Node to the right
						Node <E> nodeBeforeCursor = cursor.previous;
						Node <E> nodeAfterCursor = cursor.next;						
						nodeBeforeCursor.next=cursor.next;
						nodeAfterCursor.previous=cursor.previous;
						
						// Node to the left
						cursor.previous=null;
						cursor.next=head;
						// Now just put cursor at the front and shift head
						// to next Cursor is now the node before head
						head.previous=cursor;
						// Cursor is now the new head						
						head=cursor;
					}
					return true;
				}
				// Go to the next node
				cursor = cursor.next;
			}
		}
		return false;
	}

	/**
	 * Clears a queue of all elements and sets the size of the queue to 0
	 * if the queue is not empty
	 */
	public void clear() {
		if (!isEmpty()) {
			head = null;
			size = 0;
		}
	}

	/***********************************************************************
	 *************************** Accessor Functions**************************
	 ***********************************************************************/

	/**
	 * Iterator to go through a linked list
	 */
	public Iterator<E> iterator() {
		return new PriorityIterator();
	}

	/**
	 * Returns the first item in the queue without modifying it
	 * 
	 * @return E
	 */
	public E first() {
		// Get the first item in the queue
		E item = tail.data;
		return item;
	}

	/**
	 * Returns whether the queue is empty
	 * 
	 * @return boolean
	 */
	public boolean isEmpty() {
		// If there are no entries the queue is empty
		return (size == 0) ? true : false;
	}

	/**
	 * Returns the size of the queue
	 * 
	 * @return int
	 */
	public int size() {
		return size;
	}

	/**
	 * Displays the list in order
	 */
	public void displayList(){
		int index = 0;// So we don't exceed the number of entries in the list
		Node<E> tempNode = new Node<E>();// To traverse the list
		tempNode = head;
		while (tempNode != null && index < size) {
			System.out.println(tempNode.data);
			tempNode = tempNode.next;
		}
	}
	
	/***************************************************************************
	 ****************************** Member Variables*****************************
	 **************************************************************************/

	// Total number key entries in the queue
	private int size;
	// Node to point to the beginning of the queue
	private Node<E> head;
	// Node to point to the end of the queue
	private Node<E> tail;

	/***************************************************************************
	 ****************************** Nested Classes******************************
	 **************************************************************************/
	// Node nested class
	private static class Node<E> {

		// Default constructor
		public Node() {
			next = null;
			previous = null;
			data = null;
		}

		// Main Constructor
		public Node(E guess) {
			// Set the key and value variables equal to the input.
			this.data = guess;			
			// Set the reference nodes to null.
			// These will be changed in the queue
			// depending on where the node is inserted.
			this.next = null;
			this.previous = null;
		}

		/* Node member variables */
		// The data for the node
		private E data;
		// The tail will always have the next variable be null
		private Node<E> next;
		// The head will always have the previous variable be null
		private Node<E> previous;
	}

	// Nested Iterator class
	private class PriorityIterator implements Iterator<E> {
		// Node to traverse the list
		private Node<E> nextNode;
		// Index to keep track of iterated node
		private int index = 0;

		// Default constructor
		public PriorityIterator() {
			nextNode = head;
		}

		@Override
		public E next() {
			// If there is not a next node throw an error
			if (!hasNext()) {
				throw new NoSuchElementException("Illegal call to next(); "
						+ "iterator is after end of list");

			}
			// Otherwise return the current key and advance to the next node
			E returnKey = nextNode.data;
			nextNode = nextNode.next;
			index++;
			return returnKey;
		}

		@Override
		public boolean hasNext() {
			// If the index is less than the total nodes in
			// the list we have a next node
			return index < size;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("remove() is not "
					+ "supported by this iterator");
		}
	}

}

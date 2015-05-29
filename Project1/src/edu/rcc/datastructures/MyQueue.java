/**
 * @File MyQueue.java
 * @author Marlo Zeroth
 * @date May 18, 2015
 * @course CSC18C DataStructures
 */

package edu.rcc.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyQueue<E> implements Iterable<E> {

	/************************************************************************
	 **************************** Constructors ******************************
	 ************************************************************************/

	MyQueue() {
		size = 0;
		head = tail = null;
	}

	// Main constructor
	MyQueue(E entry) {
		// Allocate memory for the new entry
		Node<E> newNode = new Node<E>(entry, 1);
		// Increase the size and set this as the head/tail
		size++;
		tail = head = newNode;
	}

	/***********************************************************************
	 *************************** Mutator Functions**************************
	 ***********************************************************************/

	/**
	 * Adds an entry to the back of the queue.
	 * 
	 * @param entry
	 */
	public void enqueue(E entry) {
		// We only need to manipulate the tail after the first entry since we
		// are adding
		// at the back.
		Node<E> newNode = new Node<E>(entry, size + 1);
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
	 * Delete the item at the front of the queue. Returns the entry deleted or
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
				item = returnNode.guess;
			} else {

				// Change the head to be the one in front of it
				head = head.next;
				// Delete the old head. Remove the links to the one before and
				// after it.
				head.previous = null;
				returnNode.next = null;
				// Reduce the number of elements in the queue
				size--;
				item = returnNode.guess;
			}
		}
		return item;
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
		return new MyQueueIterator();
	}

	/**
	 * Returns the first item in the queue without modifying it
	 * 
	 * @return E
	 */
	public E first() {
		// Get the first item in the queue
		E item = tail.guess;
		return item;
	}

	/**
	 * Returns the cardinal order of the current element without changing it
	 * 
	 * @return
	 */
	public int order() {
		return tail.number;
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
			guess = null;
			number = 0;
		}

		// Main Constructor
		public Node(E guess, int number) {
			// Set the key and value variables equal to the input.
			this.guess = guess;
			this.number = number;
			// Set the reference nodes to null.
			// These will be changed in the queue
			// depending on where the node is inserted.
			this.next = null;
			this.previous = null;
		}

		/* Node member variables */
		// The data for the guess/node
		private E guess;
		// The order/number of the guess (which one is it)
		private int number;
		// The tail will always have the next variable be null
		private Node<E> next;
		// The head will always have the previous variable be null
		private Node<E> previous;
	}

	// Nested Iterator class
	private class MyQueueIterator implements Iterator<E> {
		// Node to traverse the list
		private Node<E> nextNode;
		// Index to keep track of iterated node
		private int index = 0;

		// Default constructor
		public MyQueueIterator() {
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
			E returnKey = nextNode.guess;
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

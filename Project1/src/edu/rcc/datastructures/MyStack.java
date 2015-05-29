/**
 * @File MyStack.java
 * @author Marlo Zeroth
 * @date May 18, 2015
 * @course CSC18C DataStructures
 */

package edu.rcc.datastructures;

import java.util.EmptyStackException;

public class MyStack<E> {

	/************************************************************************
	 ****************************** Constructors******************************
	 ************************************************************************/

	/* Default Constructor */
	public MyStack() {
		top = null;
		size = 0;

	}

	/***********************************************************************
	 **************************** Mutator Functions**************************
	 ***********************************************************************/

	/**
	 * Adds an entry to the top of the stack.
	 * 
	 * @param entry
	 */
	public void push(E entry) {
		// Create a new node to the list
		Node<E> node = new Node<E>(entry, top);		
		top = node;// The new node is the head now.
		size++;

	}

	/**
	 * Deletes and returns an entry from the top of the stack.
	 * 
	 * @param entry
	 * @return Value Returns the value if the key was deleted, null otherwise
	 */
	public E pop() {
		if(top!=null){		
			// Set the top node to return
			//System.out.println("Is data null " + (top.data==null?true:false));
			E topItem = top.data;
			// Delete the top node
			top = top.next;
			size--;
			return topItem;
		}else{
			throw new EmptyStackException();
		}
	}

	/**
	 * Peek at the top item without removing it from the stack
	 * 
	 * @return
	 */
	public E peek() {
		if (isEmpty()) {
			throw new EmptyStackException();
		} else {
			//System.out.println("Is data null: " + top.data);
			return top.data;
		}
	}

	/**
	 * Clears the linked list of all entries
	 */

	public void clear() {
		// If the list is not empty, then empty it.
		if (!isEmpty()) {
			top = null;
			size = 0;
		}
	}

	/***********************************************************************
	 *************************** Accessor Functions**************************
	 ***********************************************************************/

	/**
	 * Returns whether there are any entries in the stack
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * Returns the total number of entries in the stack
	 * 
	 * @return
	 */

	public int size() {
		return size;
	}

	/***************************************************************************
	 ****************************** Member Variables*****************************
	 **************************************************************************/

	// Total number key pairs in the linked list
	private int size;
	// Node to point to the beginning of the list
	private Node<E> top;

	/***************************************************************************
	 ****************************** Nested Classes*******************************
	 **************************************************************************/
	// Node nested class
	private static class Node<E> {

		/* Node Constructors */
		public Node(E entry, Node<E> next) {
			// Set the entry and reference for the next node
			this.data = entry;
			this.next = next;
		}

		/* Node member variables */
		private E data;
		// The head will always have the previous node member variable null
		// and the tail will always have its next node member variable null
		private Node<E> next;
	}
}

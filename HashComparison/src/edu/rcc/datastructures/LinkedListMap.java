/**
 * @File LinkedListMap.java
 * @author Marlo Zeroth
 * @date May 18, 2015
 * @course CSC18C DataStructures
 * The class is used to as hash table for HashMaps
 */

package edu.rcc.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListMap<Key extends Comparable<? super Key>, Value extends Comparable<? super Value>>
		implements Iterable<Key> {

	/************************************************************************
	 ****************************** Constructors******************************
	 ************************************************************************/

	/* Default Constructor */
	public LinkedListMap() {
		head = null;
		listSize = 0;

	}

	/***********************************************************************
	 **************************** Mutator Functions**************************
	 ***********************************************************************/

	/**
	 * Adds an entry to the front of the list.
	 * 
	 * @param entry
	 */
	public void insert(Key key, Value entry) {
		// Create a new node to the list
		Node<Key, Value> node = new Node<Key, Value>(key, entry, head);
		// node.next = head;
		head = node;// The new node is the head now.
		listSize++;

	}

	/**
	 * Deletes an entry from the list. It maintains the chronological order. if
	 * the key is found it returns the value of the key, otherwise returns null
	 * 
	 * @param entry
	 * @return Value Returns the value if the key was deleted, null otherwise
	 */
	public Value delete(Key entry) {
		// To return if the entry was found and deleted.
		boolean deleted = false;
		Value found = null;
		if (!isEmpty()) {
			// Cursor node to traverse the list
			Node<Key, Value> cursor = head;
			Node<Key, Value> previousNode = null;
			// Test the first node to see if the entry is there.
			if (head.key.compareTo(entry) == 0) {
				// Set the next node to be the new head/first node
				head = head.next;
				// Set the value to be returned
				found = cursor.value;
				// Delete the node with the entry
				cursor = null;
				deleted = true;
				listSize--;
			}
			// If not then go through the rest of the nodes and delete the node
			// that contains the key if it exists in the list.
			else {
				// Stop when we have we reached the end of the list
				while (!deleted && cursor != null) {
					if (cursor.key.compareTo(entry) == 0) {
						// We found it set the boolean and the return found
						// value
						deleted = true;
						found = cursor.value;

						// Delete the node
						previousNode.next = cursor.next;
						// cursor = null;
						listSize--;
					} else {
						previousNode = cursor;
						cursor = cursor.next; // Go to the next node
					}
				}
			}
		}
		return found;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void updateValue(Key key, Value newValue) {
		// Cursor to traverse the list
		Node<Key, Value> cursor = head;
		// Boolean to break from while loop once we find the key
		boolean found = false;

		// Iterate through the list until we find the key or we are at the end
		while (cursor != null && !found) {
			// If we found the key update its value pair
			if (cursor.key.equals(key)) {
				cursor.value = newValue;
				found = true;
			}
			// Search the next node
			cursor = cursor.next;
		}
	}

	/**
	 * Clears the linked list of all entries
	 */

	public void clear() {
		// If the list is not empty, then empty it.
		if (!isEmpty()) {
			head = null;
			listSize = 0;
		}
	}

	/***********************************************************************
	 *************************** Accessor Functions**************************
	 ***********************************************************************/

	/**
	 * Iterator to go through a linked list
	 */
	public Iterator<Key> iterator() {
		return new MyListIterator();
	}

	/**
	 * Returns a value associated with a key
	 * 
	 * @param key
	 * @return value
	 */
	public Value getValue(Key key) {
		// Cursor to traverse the list
		Node<Key, Value> cursor = head;
		while (cursor != null) {
			// If we found the key return the value pair
			if (cursor.key.equals(key)) {
				return cursor.value;
			}
			// Search the next node
			cursor = cursor.next;
		}
		// If not return that we don't have it
		return null;
	}

	/**
	 * Get the data at a given position
	 * @param position
	 * @return
	 */
	public Key get(int position) {
		int index = 0;
		Node<Key, Value> cursor = head;
		Key key = null;
		while (index < listSize && cursor != null) {
			if (index == position) {
				return (key = cursor.key);
			}
			index++;
			cursor = cursor.next;
		}
		return key;
	}

	/**
	 * Returns a key associate with a particular key
	 * 
	 * @param key
	 * @return
	 */
	public Key get(Key key) {
		// Cursor to traverse the list
		Node<Key, Value> cursor = head;
		while (cursor != null) {
			// If we found the key return the value pair
			if (cursor.key.equals(key)) {
				return cursor.key;
			}
			// Search the next node
			cursor = cursor.next;
		}
		// If not return that we don't have it
		return null;
	}

	/**
	 * Returns true if a key is in the list or false otherwise
	 * 
	 * @param E
	 * @param size
	 * @return
	 */

	public boolean contains(Key key) {
		// If there is nothing in the list we return false
		// If there is something we return true
		return get(key) != null;
	}

	/**
	 * Returns whether there are any key pairs in the list
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return (listSize == 0 && head == null);
	}

	/**
	 * Returns the total number of key pairs in the linked list
	 * 
	 * @return
	 */

	public int size() {
		return listSize;
	}

	/**
	 * Returns an array of strings of the linked list
	 * 
	 * @return String
	 */
	public String[] toArray() {
		// Allocate memory for the array
		String[] result = new String[listSize];
		// Node to traverse the list
		Node<Key, Value> currentNode = head;
		int index = 0;
		// Traverse the list until we reach the end
		while (index < listSize && currentNode != null) {
			// Set array element value equal to list node value 
			result[index++] = currentNode.key.toString();
			currentNode = currentNode.next;
		}
		return result;
	}

	/**
	 * Print the contents of the list in order
	 */
	public void displayList() {
		int index = 0;// So we don't exceed the number of entries in the list
		Node<Key, Value> tempNode = new Node<Key, Value>();// To traverse the
															// list
		tempNode = head;
		while (tempNode != null && index < listSize) {
			System.out.println(tempNode.key + " " + tempNode.value);
			tempNode = tempNode.next;
		}
	}

	/***************************************************************************
	 ****************************** Member Variables*****************************
	 **************************************************************************/

	// Total number key pairs in the linked list
	private int listSize;
	// Node to point to the beginning of the list
	private Node<Key, Value> head;

	/***************************************************************************
	 ****************************** Nested Classes*******************************
	 **************************************************************************/
	// Node nested class
	private static class Node<Key, Value> {

		/* Node Constructors */
		public Node() {
			this(null, null, null);
		}

		public Node(Key searchKey, Value dataValue, Node<Key, Value> next) {
			// Set the key and value variables equal to the input.
			this.key = searchKey;
			this.value = dataValue;
			// Set the reference nodes to null. These will be changed in the
			// list
			// depending on where the node is inserted
			this.next = next;
		}

		/* Node member variables */
		private Key key;
		private Value value;
		// The head will always have the previous node member variable null
		// and the tail will always have its next node member variable null
		private Node<Key, Value> next;
	}

	// Nested Iterator class
	private class MyListIterator implements Iterator<Key> {
		// Node to traverse the list
		private Node<Key, Value> nextNode;
		// Index to keep track of iterated node
		private int index = 0;

		// Default constructor
		public MyListIterator() {
			nextNode = head;
		}

		@Override
		public Key next() {
			// If there is not a next node throw an error
			if (!hasNext()) {
				throw new NoSuchElementException("Illegal call to next(); "
						+ "iterator is after end of list");

			}
			// Otherwise return the current key and advance to the next node
			Key returnKey = nextNode.key;
			nextNode = nextNode.next;
			index++;
			return returnKey;
		}

		@Override
		public boolean hasNext() {

			// If the index is less than the total nodes in
			// the list we have a next node
			return index < listSize;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("remove() is not "
					+ "supported by this iterator");
		}
	}
}

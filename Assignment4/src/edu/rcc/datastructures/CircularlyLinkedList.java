package edu.rcc.datastructures;

public class CircularlyLinkedList<E> {

	/* constructors */
	public CircularlyLinkedList() {
		currentNode = null;
	}

	public CircularlyLinkedList(E entry) {
		SingleNode<E> node = new SingleNode<E>(entry);
		// First node created must point to itself.
		currentNode = node;
		node.next = node;
		node.previous = node;
		numberOfEntries++;
	}

	/**
	 * Adds a node to the front of list
	 * 
	 * @param An
	 *            object to add to the list
	 */
	public void addFront(E entry) {

		SingleNode<E> node = new SingleNode<E>(entry);
		// If the list is empty add one node and set its node member variables
		// equal to its address.
		if (currentNode == null) {
			currentNode = node;
			node.next = currentNode;
			node.previous = currentNode;

			numberOfEntries++;
		}
		// If there are other nodes we need to insert the new node between the
		// current node and the next node so that it becomes the new current
		// node.
		else {
			// Link to the currentNode which becomes the next node
			node.next = currentNode;
			// Link to the previous node
			node.previous = currentNode.previous;
			// Change the last node to point to the new node
			currentNode.previous.next = node;
			// The current node should point to the new node
			currentNode.previous = node;
			// The new node is now the current node
			currentNode = node;
			numberOfEntries++;
		}
	}

	public void addBack(E entry) {

		SingleNode<E> node = new SingleNode<E>(entry);
		// If the list is empty add one node and set its node member variables
		// equal to its address.
		if (currentNode == null) {
			currentNode = node;
			node.next = currentNode;
			node.previous = currentNode;

			numberOfEntries++;
		}
		// If there are other nodes we need to insert the new node between the
		// current node and the next node so that it becomes the new current
		// node.
		else {
			// Link to the currentNode which becomes the next node
			node.previous = currentNode;
			// Link to the previous node
			node.next = currentNode.next;
			currentNode.next = node;			
			currentNode.previous.previous = node;
			// The new node is now the current node
			currentNode = node;
			numberOfEntries++;
		}
	}
	
	public E popFront(){
		SingleNode<E> node = new SingleNode<E>();
		
		return node.data;
	}
	
	public E popBack(){
		SingleNode<E> node = new SingleNode<E>();
		return node.data;
		
	}

	/* helper functions */
	
	public void displayList(){
		SingleNode <E> cursor = currentNode;
		int index = 0;
		do {
			// go to the next node
			System.out.println(cursor.data);
			//System.out.println("current cursor address is:" + cursor);
			//System.out.println("next point to:" + cursor.next);
			//System.out.println("previous points to:" + cursor.previous);
			cursor = cursor.next;
			index++;
		} while (index<numberOfEntries);

	}
	
	public String toString() {
		String myList = "";
		SingleNode<E> cursor;
		cursor = currentNode;
		/*
		 * if (cursor.next == currentNode) { System.out.println(cursor.data); }
		 */

		do {
			// go to the next node
			myList += cursor.data + " ";
			cursor = cursor.next;
		} while (cursor.next != currentNode);
		// myList+=cursor.data;

		return myList;
	}

	public int getSize() {
		return numberOfEntries;
	}

	/* member variables */
	private SingleNode<E> currentNode;
	private int numberOfEntries;

	/* static nested class */
	private static class SingleNode<E> {

		/* constructors */
		public SingleNode(){
			data = null;
		}
		public SingleNode(E data) {
			this.data = data;
		}

		/* member variables */
		private E data;
		private SingleNode<E> next;
		private SingleNode<E> previous;

	}

}

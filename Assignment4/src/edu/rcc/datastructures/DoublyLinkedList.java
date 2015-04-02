package edu.rcc.datastructures;

import java.util.EmptyStackException;

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
	public E popFromFront() {
		// If the stack is not empty pop from the front
		if (!isEmpty()) {
			Node<E> returnNode = new Node<E>();
			// Set the node to return equal to the front/top
			returnNode = head;
			// Change the head to be the one in front of it
			head = head.next;
			// Delete the old head. Remove the links to the one before and
			// after it.
			head.previous = null;
			returnNode.next = null;
			// Reduce the number of elements in the list
			numberOfEntries--;
			return returnNode.data;
		} else {
			throw new EmptyStackException();
		}
	}

	/**
	 * Removes and returns the last/back element in the list
	 * 
	 * @return <E> The entry at the bottom/back of the list
	 */

	public E popFromBack() {
		// If the stack is not empty pop from the back
		if (!isEmpty()) {

			Node<E> returnNode = new Node<E>();
			// Set the node to return equal to the back
			returnNode = tail;
			// Change the tail to be the one behind it
			tail = tail.previous;
			// Delete the old tail. Remove the links to the one before and
			// after it.
			tail.next = null;
			returnNode.previous = null;
			// Reduce the number of elements in the list
			numberOfEntries--;
			return returnNode.data;
		} else {
			throw new EmptyStackException();
		}
	}
	
	public void clear(){
		if(!isEmpty()){
			head = null;
			tail = null;
			numberOfEntries = 0;
		}
		else {
			throw new EmptyStackException();
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DoublyLinkedList<Stuff> list1 = new DoublyLinkedList<Stuff>();
		System.out.println("Is list empty? " + list1.isEmpty());
		Stuff mystuff = new Stuff(1, "apples", false);
		Stuff mystuff2 = new Stuff(1, "oranges", true);
		Stuff mystuff3 = new Stuff(2, "melons", true);
		Stuff mystuff4 = new Stuff(-1, "carrot", false);
		list1.pushToFront(mystuff);

		System.out.println("Is list empty? " + list1.isEmpty());

		System.out.println("After pushing at the front list  has "
				+ list1.size() + " elements and now contains: ");
		list1.displayList();
		list1.pushToFront(mystuff2);
		System.out.println("After pushing at the front list  has "
				+ list1.size() + " elements and now contains: ");
		list1.displayList();
		list1.pushToFront(mystuff3);
		System.out.println("After pushing at the front list  has "
				+ list1.size() + " elements and now contains: ");
		list1.displayList();

		list1.pushToBack(mystuff4);
		System.out.println("After pushing at the back list  has "
				+ list1.size() + " elements and now contains: ");
		list1.displayList();

		list1.popFromFront();
		System.out.println("After popping from front list has " + list1.size()
				+ " elements and now contains: ");
		list1.displayList();

		list1.popFromBack();
		System.out.println("After popping from back list has " + list1.size()
				+ " now contains: ");
		list1.displayList();
		list1.popFromBack();
		System.out.println("After popping from back list has " + list1.size()
				+ " now contains: ");
		list1.displayList();
		boolean thing3 = false;
		// Fill the list with random stuff
		for (int i = 0; i < 10; i++) {
			if (i % 2 == 0) {
				thing3 = true;
			} else {
				thing3 = false;
			}
			Stuff stuff = new Stuff(i, "thing2#" + i, thing3);
			list1.pushToFront(stuff);
		}
		System.out.println("After pushing ten itmes at the front list has "
				+ list1.size() + " now contains: ");
		list1.displayList();
		list1.clear();
		System.out.println("Clearing the list. Stack has " + list1.size()
				+ " and is empty: " + list1.isEmpty());

	}
}

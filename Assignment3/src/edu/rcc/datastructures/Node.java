package edu.rcc.datastructures;

public class Node {
	private int data;
	private Node nextNode;

	/* Constructors */
	// Copy constructor
	public Node(int data){
		this(data,null);
	}

	public Node(int number, Node node) {
		data = number;
		nextNode = node;
	}

	//Default constructor
	public Node() {
		// Leave empty. Compiler complains if default constructor is not in class.
	}

	/* Accessor and Mutator Functions */
	public Node getNext() {
		return nextNode;
	}

	public void setNext(Node node) {
		nextNode = node;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}
}

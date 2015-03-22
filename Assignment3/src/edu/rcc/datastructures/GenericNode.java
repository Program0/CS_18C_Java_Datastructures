package edu.rcc.datastructures;

public class GenericNode <E> {
	private E data;
	private GenericNode <E> nextNode;

	/* Constructors */
	// Copy constructor
	public GenericNode(E data){
		this(data,null);
	}

	public GenericNode(E newData, GenericNode <E> node) {
		data = newData;
		nextNode = node;
	}

	//Default constructor
	public GenericNode() {
		// Leave empty. Compiler complains if default constructor is not in class.
	}

	/* Accessor and Mutator Functions */
	public GenericNode <E> getNext() {
		return nextNode;
	}

	public void setNext(GenericNode <E> node) {
		nextNode = node;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

}

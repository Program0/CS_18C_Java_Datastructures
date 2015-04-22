/**
 * @author Marlo Zeroth
 * Stack based on Carrano Ch 6
 */

package edu.rcc.datastructures;

// Generic class used with this class must extend Comparable and Comparator
public class StackGenerics_V1<E extends Comparable<E>> {
	GenericNode<E> topNode;//Node to navigate stack
	Integer sizeOfStack;
	
	//Default constructor
	public StackGenerics_V1() {
		topNode = null;
		sizeOfStack = 0;
	}

	public void push(E entry) {
		//Create a new node and add entry to its data variable
		GenericNode<E> newNode = new GenericNode<E>();
		newNode.setData(entry);
		// Link the node
		newNode.setNext(topNode);
		// Put the new node a the top of the stack
		topNode = newNode;
		sizeOfStack++;
	}

	/**
	 * Remove and return the top element in the stack.
	 * @return Object <E> 
	 */
	public E pop() throws MyEmptyStackException{
		//Only pop nodes is stack is not empty
		if (topNode != null) {
			//Get the top nodes value and return it
			E top = topNode.getData();
			//Change the top to be the next node
			topNode = topNode.getNext();
			sizeOfStack--;
			return top;
		} else {
			throw new MyEmptyStackException(sizeOfStack);
		}
	}

	/**
	 * Returns the top element in the stack without removing it.
	 * @return Object <E> 
	 */
	public E peek() throws MyEmptyStackException {
		if (topNode != null) {
			E top = topNode.getData();			
			return top;
		} else
			throw new MyEmptyStackException(sizeOfStack);
	}
	
	/**
	 * Empties the whole list
	 * @throws MyEmptyStackException
	 */
	
	public void clear() throws MyEmptyStackException{
		if(!isEmpty()){
		topNode = null;// Get rid of the top and everything else goes away
		sizeOfStack = 0;}
		else{
			throw new MyEmptyStackException(sizeOfStack);
		}
	}
	
	boolean isEmpty(){
		// If the stack is empty return true, false otherwise
		return topNode == null;
	}
	
	int getSize(){
		return sizeOfStack;
	}
}

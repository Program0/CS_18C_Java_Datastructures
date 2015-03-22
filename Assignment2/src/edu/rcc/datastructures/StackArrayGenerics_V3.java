package edu.rcc.datastructures;

public class StackArrayGenerics_V3 <E>{
	/*Member variables*/
	private static final Integer DEFAULTSIZE = 10;
	private Integer top; // hold the total number of elements and the place of the top element
	private E[] array; // hold the elements in the stack

	/*Constructors*/
	@SuppressWarnings("unchecked")
	public StackArrayGenerics_V3() {
		top = 0;
		array = (E[]) new Object[DEFAULTSIZE];
	}

	@SuppressWarnings("unchecked")
	public StackArrayGenerics_V3(Integer size) {
		array = (E[]) new Object[size];
		top = 0;
	}

	/* Stack operations */
	/**
	 * Push an entry into the stack.
	 * @param Entry to push onto the stack.
	 */
	@SuppressWarnings("unchecked")
	public void push(E number) {
		// Test to make sure there is room in the current array
		if (top < array.length - 1) {
			array[top++] = number;
		}
		// If not then create a new array, copy the current into the new,
		// assign reference from arrayB to this.array. push number into new
		// array.
		else {
			E[] arrayB = (E[]) new Object[array.length * 2];
			copyArray(array, arrayB);
			array = arrayB;
			array[top++] = number;
		}
	}

	/**
	 * Pops the entry at the top of the stack
	 * @return The entry at the top of the stack
	 * @throws EmptyStackException
	 */
	@SuppressWarnings("unchecked")
	public E pop() throws EmptyStackException {
		// Test to make sure stack is not empty
		
		if (top > 0) {
			// Test the number of elements in stack, then resize if meet
			// condition
			if (top < ((array.length / 2) - 1) && top > DEFAULTSIZE) {
				// create new array
				E[] arrayB = (E[]) new Object[array.length / 2];
				copyArray(array, arrayB);
				array = null;
				array = arrayB;

			}
			return array[--top];
		} else {
			throw new EmptyStackException(top);
		}
	}

	/**
	 * Looks at the top element of the stack without changing it.
	 * @return The element at the top of the stack
	 * @throws EmptyStackException
	 */
	public E peek() throws EmptyStackException {
		// Test to make sure the stack is not empty.
		// If it is then throw an exception.
		if (top > 0) {
			//We want the element at top of stack
			return array[top - 1];
		} else {
			throw new EmptyStackException(top);
		}
	}

	/**
	 * Removes all elements from the stack
	 */
	@SuppressWarnings("unchecked")
	public void clear() {
		// Prepare for garbage collection then set position of
		// top element equal to 0 to start over
		array = null;
		array = (E[])new Object[DEFAULTSIZE];
		top = 0;
	}
	
	/**
	 * Checks if the stack is empty or not.
	 * @return True if the list is empty, false otherwise.
	 */

	public boolean isEmpty() {
		// if top is 0, then we are at element 0 and are empty
		if (top == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @return True if the stack is getting full, false otherwise.
	 */
	//TODO should be made private and test program should be changed accordingly
	//TODO write a junit test for class?
	public boolean isFull() {
		// if we are at last available element, the array is full
		if (top == array.length - 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return The total number of elements in the stack
	 */
	public Integer stackSize() {
		return top;
	}

	/**
	 * @return The actual size of the stack. Depends on the internal implementation
	 */
	public Integer stackLength() {
		return array.length;
	}

	/**
	 * Serialize the stack
	 */
	public String toString() {
		String contents = "";
		for (int i = 0; i < top; i++) {
			contents += " " + array[i];
		}
		return contents;
	}

	/*Helper functions*/
	/**
	 * Copies one array into another. Used to resize the stack.
	 * @param originalArray to copy
	 * @param copyArray to copy into
	 */
	private void copyArray(E[] originalArray, E[] copyArray) {
		for (int i = 0; i < top; i++) {
			copyArray[i] = originalArray[i];
		}
	}
}

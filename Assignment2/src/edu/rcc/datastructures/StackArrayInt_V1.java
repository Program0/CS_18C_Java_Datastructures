package edu.rcc.datastructures;

public class StackArrayInt_V1 {
	private static final int DEFAULTSIZE = 10;
	private int[] array;
	private int top;// top free element

	/* Constructors */
	// Default constructor
	public StackArrayInt_V1() {
		array = new int[DEFAULTSIZE];
		top = 0;
	}

	public StackArrayInt_V1(int size) {
		array = new int[size];
		top = 0;
	}

	/* Stack operations */
	public void push(int number) {
		// Test to make sure there is room in the current array
		if (top < array.length - 1) {
			array[top++] = number;
		}
		// If not then create a new array, copy the current into the new,
		// assign reference from arrayB to this.array. push number into new
		// array.
		else {
			int[] arrayB = new int[array.length * 2];
			copyArray(array, arrayB);
			array = arrayB;
			array[top++] = number;

		}

	}

	// Top should never be less than 0. 0 is empty.
	public int pop() throws EmptyStackException {
		// Test to make sure stack is not empty
		if (top > 0) {
			// Test the number of elements in stack, then resize if meet
			// condition
			if (top < ((array.length / 2) - 1) && top > DEFAULTSIZE) {
				// create new array
				int[] arrayB = new int[array.length / 2];
				copyArray(array, arrayB);
				array = null;
				array = arrayB;

			}
			return array[--top];
		} else {
			throw new EmptyStackException(top);
		}
	}

	public int peek() throws EmptyStackException {
		// Test to make sure the stack is not empty.
		// If it is then throw an exception.
		if (top > 0) {
			// We want the element at top of stack
			return array[top - 1];
		} else {
			throw new EmptyStackException(top);
		}
	}

	public void clear() {
		// Prepare for garbage collection then set position of
		// top element equal to 0 to start over
		array = null;
		array = new int[DEFAULTSIZE];
		top = 0;
	}

	public boolean isEmpty() {
		// if top is 0, then we are at element 0 and are empty
		if (top == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isFull() {
		// if we are at last available element, the array is full
		if (top == array.length - 1) {
			return true;
		} else {
			return false;
		}

	}

	public int stackSize() {
		return top;
	}

	public int stackLength() {
		return array.length;
	}

	public String toString() {
		String contents = "";
		for (int i = 0; i < top; i++) {
			contents += " " + array[i];
		}
		return contents;
	}

	private void copyArray(int[] originalArray, int[] copyArray) {
		for (int i = 0; i < top; i++) {
			copyArray[i] = originalArray[i];
		}

	}

}

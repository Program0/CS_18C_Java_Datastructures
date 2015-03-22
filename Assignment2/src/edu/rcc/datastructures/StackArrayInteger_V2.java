package edu.rcc.datastructures;

public class StackArrayInteger_V2 {

	private static final Integer DEFAULTSIZE = 10;
	private Integer top;
	private Integer[] array;

	public StackArrayInteger_V2() {
		top = 0;
		array = new Integer[DEFAULTSIZE];
	}

	public StackArrayInteger_V2(Integer size) {
		array = new Integer[size];
		top = 0;
	}

	/* Stack operations */
	public void push(Integer number) {
		// Test to make sure there is room in the current array
		if (top < array.length - 1) {
			array[top++] = number;
		}
		// If not then create a new array, copy the current into the new,
		// assign reference from arrayB to this.array. push number into new
		// array.
		else {
			Integer[] arrayB = new Integer[array.length * 2];
			copyArray(array, arrayB);
			array = arrayB;
			array[top++] = number;
		}
	}

	// TODO Implement with try-catch
	public Integer pop() throws EmptyStackException {
		// Test to make sure stack is not empty.
		// If it is then throw an exception.
		if (top > 0) {
			// Test the number of elements in stack, then resize if meet
			// condition
			if (top < ((array.length / 2) - 1) && top > DEFAULTSIZE) {
				// create new array
				Integer[] arrayB = new Integer[array.length / 2];
				copyArray(array, arrayB);
				array = null;
				array = arrayB;

			}
			return array[--top];
		} else {
			throw new EmptyStackException(top);
		}
	}

	public Integer peek() throws EmptyStackException {
		// Test to make sure the stack is not empty.
		// If it is then throw an exception.
		if (top > 0) {
			//We want the element at top of stack
			return array[top - 1];
		} else {
			throw new EmptyStackException(top);
		}
	}

	public void clear() {
		// Prepare for garbage collection, create new array, then set position of
		// top element equal to 0 to start over
		array = null;
		array = new Integer[DEFAULTSIZE];
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

	public Integer stackSize() {
		return top;
	}

	public Integer stackLength() {
		return array.length;
	}

	public String toString() {
		String contents = "";
		for (Integer i = 0; i < top; i++) {
			contents += " " + array[i];
		}
		return contents;
	}

	private void copyArray(Integer[] originalArray, Integer[] copyArray) {
		for (Integer i = 0; i < top; i++) {
			copyArray[i] = originalArray[i];
		}
	}
}
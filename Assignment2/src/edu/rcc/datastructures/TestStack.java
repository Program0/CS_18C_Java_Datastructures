package edu.rcc.datastructures;

public class TestStack {

	public static void main(String[] args) {
		// Test section for using int
		StackArrayInt_V1 sampleStack = new StackArrayInt_V1();
		System.out.println("Testing stack with array of primitives");
		System.out.println("The new stack is empty: " + sampleStack.isEmpty());
		System.out.println("Filling stack with 50 elements,"
				+ " testing peek(), stackSize(), stackLength(), isFull():");
		for (int i = 0; i < 50; i++) {
			sampleStack.push(i + 50);
			if (sampleStack.isFull()) {
				System.out.println("Stack is full, now resizing at iteration: "
						+ i);
			}
			if (i % 5 == 0) {
				try {
					System.out.println("Element pushed at iteration " + i
							+ " is " + (i + 50)
							+ ". Element at top of stack is: "
							+ sampleStack.peek() + ". Size of the stack is "
							+ +sampleStack.stackSize() + " and has length "
							+ sampleStack.stackLength());
				} catch (EmptyStackException e) {
					// Let user know the stack is empty and has 0 elements
					System.out.println("The stack has " + e.getInputNumber()
							+ " elements and is empty.");
					e.printStackTrace();
				}
			}
		}
		System.out.println("The stack now contains: " + sampleStack);
		System.out.println("Testing the clear() function:");
		sampleStack.clear();
		System.out.println("The stack now contains: " + sampleStack);
		System.out.println("Its size is: " + sampleStack.stackSize()
				+ " length is: " + sampleStack.stackLength());
		System.out.println("Stack is empty: " + sampleStack.isEmpty());
		// Testing pop, empty, size, length, and peek
		System.out.println("Stack is now being filled again.");
		for (int i = 0; i < 50; i++) {
			sampleStack.push(i + 50);
		}

		System.out.println("Poping the items from the stack: ");
		for (int i = 0; i < 55; i++) {
			try {
				sampleStack.pop();
				if (i % 5 == 0) {
					System.out.println("Element at top of stack after " + i
							+ " iteration is: " + sampleStack.peek()
							+ " the stack size is " + sampleStack.stackSize()
							+ " and length is " + sampleStack.stackLength()
							+ ". The stack empty: " + sampleStack.isEmpty());
				}

			} catch (EmptyStackException e) {
				System.out.println("Error! The stack has total elements:"
						+ e.getInputNumber());
			}
		}

		System.out.println("After poping all elements its size is: "
				+ sampleStack.stackSize() + " length is: "
				+ sampleStack.stackLength());
		System.out.println("Stack is empty: " + sampleStack.isEmpty());

		// Section for Integer wrapper class
		StackArrayInteger_V2 sampleStack2 = new StackArrayInteger_V2();
		System.out.println();
		System.out.println("Testing stack with array of Integer wrapper class");
		System.out.println("The new stack is empty: " + sampleStack2.isEmpty());
		System.out.println("Filling stack with 50 elements,"
				+ " testing peek(), stackSize(), stackLength(), isFull():");
		for (int i = 0; i < 50; i++) {
			sampleStack2.push(i + 50);
			if (sampleStack2.isFull()) {
				System.out.println("Stack is full, now resizing at iteration: "
						+ i);
			}
			if (i % 5 == 0) {
				try {
					System.out.println("Element pushed at iteration " + i
							+ " is " + (i + 50)
							+ ". Element at top of stack is: "
							+ sampleStack2.peek() + ". Size of the stack is "
							+ +sampleStack2.stackSize() + " and has length "
							+ sampleStack2.stackLength());
				} catch (EmptyStackException e) {
					// Let user know the stack is empty and has 0 elements
					System.out.println("The stack has " + e.getInputNumber()
							+ " elements and is empty.");
					e.printStackTrace();
				}
			}
		}
		System.out.println("The stack now contains: " + sampleStack2);
		System.out.println("Testing the clear() function:");
		sampleStack2.clear();
		System.out.println("The stack now contains: " + sampleStack2);
		System.out.println("Its size is: " + sampleStack2.stackSize()
				+ " length is: " + sampleStack2.stackLength());
		System.out.println("Stack is empty: " + sampleStack2.isEmpty());
		// Testing pop, empty, size, length, and peek
		System.out.println("Stack is now being filled again.");
		for (int i = 0; i < 50; i++) {
			sampleStack2.push(i + 50);
		}

		System.out.println("Poping the items from the stack: ");
		for (int i = 0; i < 55; i++) {
			try {
				sampleStack2.pop();
				if (i % 5 == 0) {
					System.out.println("Element at top of stack after " + i
							+ " iteration is: " + sampleStack2.peek()
							+ " the stack size is " + sampleStack2.stackSize()
							+ " and length is " + sampleStack2.stackLength()
							+ ". The stack empty: " + sampleStack2.isEmpty());
				}

			} catch (EmptyStackException e) {
				System.out.println("Error! stack has total elements:"
						+ e.getInputNumber());
			}
		}

		System.out.println("After poping all elements its size is: "
				+ sampleStack2.stackSize() + " length is: "
				+ sampleStack2.stackLength());
		System.out.println("Stack is empty: " + sampleStack2.isEmpty());

		// Testing StaticArrayGenerics
		StackArrayGenerics_V3<Stuff> sampleStack3 = new StackArrayGenerics_V3<Stuff>();
		System.out.println();
		System.out.println("Testing stack with array of generics class");
		System.out.println("The new stack is empty: " + sampleStack3.isEmpty());
		System.out.println("Filling stack with 50 elements,"
				+ " testing peek(), stackSize(), stackLength(), isFull():");
		boolean thing3;
		for (int i = 0; i < 50; i++) {
			if (i % 2 == 0) {
				thing3 = true;
			} else {
				thing3 = false;
			}
			Stuff stuff = new Stuff(i, "thing2#" + i, thing3);
			sampleStack3.push(stuff);
			if (sampleStack3.isFull()) {
				System.out.println("Stack is full, now resizing at iteration: "
						+ i);
			}
			if (i % 5 == 0) {
				try {
					System.out.println("Element pushed at iteration " + i
							+ " is " + stuff + ". Element at top of stack is: "
							+ sampleStack3.peek() + ". Size of the stack is "
							+ +sampleStack3.stackSize() + " and has length "
							+ sampleStack3.stackLength());
				} catch (EmptyStackException e) {
					// Let user know the stack is empty and has 0 elements
					System.out.println("The stack has " + e.getInputNumber()
							+ " elements and is empty.");
					e.printStackTrace();
				}
			}
		}
		System.out.println("The stack now contains: " + sampleStack3);
		System.out.println("Testing the clear() function:");
		sampleStack3.clear();
		System.out.println("The stack now contains: " + sampleStack3);
		System.out.println("Its size is: " + sampleStack3.stackSize()
				+ " length is: " + sampleStack3.stackLength());
		System.out.println("Stack is empty: " + sampleStack3.isEmpty());
		// Testing pop, empty, size, length, and peek
		System.out.println("Stack is now being filled again.");
		for (int i = 0; i < 50; i++) {
			if (i % 2 == 0) {
				thing3 = true;
			} else {
				thing3 = false;
			}
			Stuff stuff = new Stuff(i, "thing2#" + i, thing3);
			sampleStack3.push(stuff);
		}

		System.out.println("Poping the items from the stack: ");
		for (int i = 0; i < 55; i++) {
			try {
				sampleStack3.pop();
				if (i % 5 == 0) {
					System.out.println("Element at top of stack after " + i
							+ " iteration is: " + sampleStack3.peek()
							+ " the stack size is " + sampleStack3.stackSize()
							+ " and length is " + sampleStack3.stackLength()
							+ ". The stack empty: " + sampleStack3.isEmpty());
				}

			} catch (EmptyStackException e) {
				System.out.println("Error! stack has total elements:"
						+ e.getInputNumber());
			}
		}

		System.out.println("After poping all elements its size is: "
				+ sampleStack3.stackSize() + " length is: "
				+ sampleStack3.stackLength());
		System.out.println("Stack is empty: " + sampleStack3.isEmpty());

	}

}

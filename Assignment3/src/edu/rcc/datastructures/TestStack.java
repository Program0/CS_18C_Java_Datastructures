/**
 * @author Marlo Zeroth
 * program to test the StackGenerics_V1 class
 */

package edu.rcc.datastructures;

public class TestStack {

	public static void main(String[] args) {
		// Testing the stack methods for GenericStack_V1
		StackGenerics_V1<Stuff> myStack = new StackGenerics_V1<Stuff>();

		System.out.println("Testing the StackGenerics_V1 class:");
		System.out.println("Stack is empty: " + myStack.isEmpty());
		System.out.println("Pushing stuff in the stack.");
		boolean thing3;
		System.out.println("Testing push.");
		for (Integer i = 0; i < 10; i++) {
			if (i % 2 == 0) {
				thing3 = true;
			} else {
				thing3 = false;
			}
			Stuff stuff = new Stuff(i, "thing2#" + i, thing3);
			System.out.println("Now pushing: " + stuff);
			myStack.push(stuff);
		}

		try {
			System.out.println("Stack is empty: " + myStack.isEmpty());
			System.out.println("Top element is " + myStack.peek());
			System.out.println("Stack size is: " + myStack.getSize());

			System.out.println("Testing pop.");
			for (Integer i = 0; i < 10; i++) {
				System.out.println("Now pushing: " + myStack.pop());
			}

			System.out.println("Stack is empty: " + myStack.isEmpty());
			System.out.println("Filling the stack again:");
			for (Integer i = 0; i < 10; i++) {
				if (i % 2 == 0) {
					thing3 = true;
				} else {
					thing3 = false;
				}
				Stuff stuff = new Stuff(i, "thing2#" + i, thing3);
				System.out.println("Now pushing: " + stuff);
				myStack.push(stuff);
			}
			System.out.println("Stack size is now: " + myStack.getSize());
			System.out.println("Testing clear:");
			myStack.clear();
			System.out.println("Stack is empty: " + myStack.isEmpty());
			System.out.println("Stack size is: " + myStack.getSize());
			System.out.println("Tring to empty the list when it is already empty");
			myStack.clear();

		} catch (MyEmptyStackException e) {
			System.out.println("Error! The list has " + e.getInputNumber()
					+ " total elements:");
		}
	}

}

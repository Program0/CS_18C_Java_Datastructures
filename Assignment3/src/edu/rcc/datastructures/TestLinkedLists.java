package edu.rcc.datastructures;

/**
 * 
 * @author Marlo Zeroth Program to test the LinkedListGenericsV4,
 *         LinkedListInt_V1, LinkedListInt_V2, LinkedListInteger,V3, and
 *         LinkedListGenerics_V4 classes. Uses the Stuff class to test the
 *         classes using generics.
 *
 */
public class TestLinkedLists {

	public static void main(String[] args) {
		/*
		 * // Testing the ListNode functions for LinkedListInt_V1
		 * LinkedListInt_V1 mylist = new LinkedListInt_V1(); // Testing
		 * isEmpty(),insert(entry), displayList()
		 * System.out.println("Is the list empty: " + mylist.isEmpty());
		 * System.out.println("Filling the list."); for (int i = 1; i <= 10;
		 * i++) { mylist.insert(i); } System.out.println("Is the list empty: " +
		 * mylist.isEmpty()); System.out.println("The list contains:");
		 * mylist.displayList(); // Testing remove(entry)
		 * System.out.println("Removing the middle four elements:"); for (int i
		 * = 3; i < 7; i++) { mylist.delete(i);
		 * System.out.println("The list contains:"); mylist.displayList(); }
		 * 
		 * System.out.println("Removing the first element:"); mylist.delete(1);
		 * mylist.displayList();
		 * System.out.println("Removing the last element"); mylist.delete(10);
		 * mylist.displayList(); // Testing clear() and isEmpty()
		 * System.out.println("Clearing the list"); mylist.clear();
		 * System.out.println("Is the list empty: " + mylist.isEmpty());
		 */

		/*
		 * // Testing the functions for LinkedListInt_V2 LinkedListInt_V2
		 * myList2 = new LinkedListInt_V2(); // Testing isEmpty(),insert(entry),
		 * displayList() System.out.println("Is the list empty: " +
		 * myList2.isEmpty()); System.out.println("Filling the list."); for (int
		 * i = 1; i <= 10; i++) { myList2.insert(i); }
		 * System.out.println("Is the list empty: " + myList2.isEmpty());
		 * System.out.println("The list contains:"); myList2.displayList(); //
		 * Testing remove(entry)
		 * System.out.println("Removing the middle four elements:"); for (int i
		 * = 3; i < 7; i++) { myList2.delete(i);
		 * System.out.println("The list contains:"); myList2.displayList(); }
		 * 
		 * System.out.println("Removing the first element:"); myList2.delete(1);
		 * myList2.displayList();
		 * System.out.println("Removing the last element"); myList2.delete(10);
		 * myList2.displayList(); // Testing clear() and isEmpty()
		 * System.out.println("Clearing the list"); myList2.clear();
		 * System.out.println("Is the list empty: " + myList2.isEmpty());
		 */

		/*
		 * System.out.println("Testing linked list using Integer wrapper class");
		 * // Testing the functions for LinkedListInteger_V3
		 * LinkedListInteger_V3 myList3 = new LinkedListInteger_V3(); // Testing
		 * isEmpty(),insert(entry), displayList()
		 * System.out.println("Is the list empty: " + myList3.isEmpty());
		 * System.out.println("Filling the list."); for (int i = 1; i <= 10;
		 * i++) { myList3.insert(i); } System.out.println("Is the list empty: "
		 * + myList3.isEmpty()); System.out.println("The list contains:");
		 * myList3.displayList(); // Testing remove(entry)
		 * System.out.println("Removing the middle four elements:"); for (int i
		 * = 3; i < 7; i++) { myList3.delete(i);
		 * System.out.println("The list contains:"); myList3.displayList(); }
		 * 
		 * System.out.println("Removing the first element:"); myList3.delete(1);
		 * myList3.displayList();
		 * System.out.println("Removing the last element"); myList3.delete(10);
		 * myList3.displayList(); // Testing clear() and isEmpty()
		 * System.out.println("Clearing the list"); myList3.clear();
		 * System.out.println("Is the list empty: " + myList3.isEmpty());
		 */

		System.out.println("Testing linked list using Generic class");
		// Testing the functions for LinkedListGenerics_V4
		LinkedListGenerics_V4<Stuff> myList4 = new LinkedListGenerics_V4<Stuff>();
		// Testing isEmpty(),insert(entry), displayList()
		System.out.println("Is the list empty: " + myList4.isEmpty());
		System.out.println("Filling the list with 10 elements.");
		boolean thing3;
		for (Integer i = 1; i <= 10; i++) {
			if (i % 2 == 0) {
				thing3 = true;
			} else {
				thing3 = false;
			}
			Stuff stuff = new Stuff(i, "thing2#" + i, thing3);
			myList4.insert(stuff);
		}
		System.out.println("Is the list empty: " + myList4.isEmpty());
		System.out.println("The list is size:" + myList4.getSize()
				+ " and contains:");
		myList4.displayList();
		// Testing remove(entry)
		System.out
				.println("Removing from the third element and beyond what list has:");
		try {
			for (Integer i = 3; i < 15; i++) {
				if (i % 2 == 0) {
					thing3 = true;
				} else {
					thing3 = false;
				}
				Stuff stuff = new Stuff(i, "thing2#" + i, thing3);
				myList4.delete(stuff);
				System.out.println("The list is size:" + myList4.getSize()
						+ " and contains:");
				myList4.displayList();
			}

			// Testing clear() and isEmpty()
			System.out.println("Clearing the list");
			myList4.clear();
			System.out.println("Is the list empty: " + myList4.isEmpty());
			System.out.println("Clearing the list when it is empty:");
			System.out.println("Is the list empty: " + myList4.isEmpty());
			myList4.clear();

		} catch (MyEmptyStackException e) {
			System.out.println("Error! The list has " + e.getInputNumber()
					+ " total elements:");
		}

	}

}

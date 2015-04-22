package edu.rcc.datastructures;

public class TestCicurlarlyLinkedList {

	public static void main(String[] args) {
		CircularlyLinkedList<Stuff> myList = new CircularlyLinkedList<Stuff>();
		Stuff stuff1 = new Stuff(1, "apple", false);
		Stuff stuff2 = new Stuff(2, "orange", true);
		Stuff stuff3 = new Stuff(3, "peach", true);
		Stuff stuff4 = new Stuff(4, "onion", false);
		Stuff stuff5 = new Stuff(5, "cheese", false);
		myList.addFront(stuff1);
		System.out.println("size is now: " + myList.getSize());
		myList.displayList();
		myList.addFront(stuff2);
		System.out.println("size is now: " + myList.getSize());
		myList.displayList();
		myList.addFront(stuff3);
		System.out.println("size is now: " + myList.getSize());
		myList.displayList();
		myList.addFront(stuff4);
		System.out.println("size is now: " + myList.getSize());
		myList.displayList();
		myList.addFront(stuff5);
		System.out.println("size is now: " + myList.getSize());
		myList.displayList();

		System.out.println("\n");
		System.out.println("Testing adding to the back:");
		CircularlyLinkedList<Stuff> myListBack = new CircularlyLinkedList<Stuff>();
		;
		myListBack.addFront(stuff1);
		System.out.println("size is now: " + myListBack.getSize());
		myListBack.displayList();
		myListBack.addFront(stuff2);
		System.out.println("size is now: " + myListBack.getSize());
		myListBack.displayList();
		myListBack.addFront(stuff3);
		System.out.println("size is now: " + myListBack.getSize());
		myListBack.displayList();
		myListBack.addFront(stuff4);
		System.out.println("size is now: " + myListBack.getSize());
		myListBack.displayList();
		myListBack.addFront(stuff5);
		System.out.println("size is now: " + myListBack.getSize());
		myListBack.displayList();

	}

}

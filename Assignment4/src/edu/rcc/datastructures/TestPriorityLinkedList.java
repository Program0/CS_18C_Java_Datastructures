package edu.rcc.datastructures;

public class TestPriorityLinkedList {

	public static void main(String[] args) {
		// Testing the DoublyLinkedList class
				PriorityLinkedList<Stuff> list1 = new PriorityLinkedList<Stuff>();
				System.out.println("Is list empty? " + list1.isEmpty());
				Stuff mystuff = new Stuff(1, "apples", false);
				Stuff mystuff2 = new Stuff(1, "oranges", true);
				Stuff mystuff3 = new Stuff(2, "melons", true);
				Stuff mystuff4 = new Stuff(-1, "carrot", false);
				System.out.println("Added one item.");
				list1.queue(mystuff);

				System.out.println("Is list empty? " + list1.isEmpty());

				System.out.println("After queueing the list has "
						+ list1.size() + " elements and now contains: ");
				list1.displayList();
				list1.queue(mystuff2);
				System.out.println("After queueing the list has "
						+ list1.size() + " elements and now contains: ");
				list1.displayList();
				list1.queue(mystuff3);
				System.out.println("After queueing the list has "
						+ list1.size() + " elements and now contains: ");
				list1.displayList();

				list1.queue(mystuff4);
				System.out.println("After queueing the list has "
						+ list1.size() + " elements and now contains: ");
				list1.displayList();
				System.out.println("Does the list contain 2, melons, false :" +list1.contains(mystuff3));
				System.out.println("List after contains executes:");
				list1.displayList();

	}

}

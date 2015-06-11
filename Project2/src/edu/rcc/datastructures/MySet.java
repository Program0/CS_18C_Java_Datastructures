/**
 * @File MySet.java
 * @author Marlo Zeroth
 * @date May 18, 2015
 * @course CSC18C DataStructures
 */

package edu.rcc.datastructures;

public class MySet<Key extends Comparable<? super Key>> {

	/************************************************************************
	 ***************************** Constructors *****************************
	 ************************************************************************/

	// Main Constructor
	@SuppressWarnings({ "unchecked", "rawtypes" })
	MySet() {
		keySet = new MyMap();
	}

	/***********************************************************************
	 *************************** Mutator Functions *************************
	 ***********************************************************************/

	/**
	 * Inserts a key into the set
	 * 
	 * @param key
	 */
	public void insert(Key key) {
		keySet.insert(key, null);
	}

	/**
	 * Adds a set to the existing set if the passed set is not already present
	 * in the existing set.
	 * 
	 * @param set
	 */
	public void addSet(MySet<Key> set) {
		MyLinkedList<Key> setsKeys = set.toList();
		for (Key key : setsKeys) {
			keySet.insert(key, null);
		}
	}

	/**
	 * Removes a key from the set
	 * 
	 * @param key
	 * @return
	 */
	public boolean delete(Key key) {
		return keySet.deleteKey(key);
	}

	/**
	 * Clears the set of all keys
	 */
	public void clear() {
		keySet.clear();
	}

	/***********************************************************************
	 *************************** Accessor Functions**************************
	 ***********************************************************************/

	/**
	 * Returns if a key is in the set
	 * 
	 * @param key
	 * @return
	 */
	public boolean contains(Key key) {
		return keySet.contains(key);
	}

	/**
	 * Returns the set that is intersection of this object's set and the passed
	 * set.
	 * 
	 * @param testSet
	 * @return
	 */
	public MySet<Key> retainAll(MySet<Key> testSet) {

		// Lists to iterate through the keys in the sets
		MyLinkedList<Key> setTest = testSet.toList();
		MyLinkedList<Key> thisSet = keySet.keysTolist();
		MySet<Key> intersect = new MySet<Key>();

		// Iterate through the keys in both sets and test if the keys are in the
		// passed set.
		for (Key key : setTest) {
			if (thisSet.contains(key))
				intersect.insert(key);
		}

		// Iterate through this object's set and test if its keys are in the
		// passed set
		for (Key key : thisSet) {
			if (setTest.contains(key))
				intersect.insert(key);
		}

		return intersect;
	}

	public MyLinkedList<Key> toList() {
		return keySet.keysTolist();
	}

	/**
	 * Provides a deep copy of the keys in the set
	 * 
	 * @return
	 */
	public String[] toArray() {
		String[] elements = new String[keySet.size()];
		String[] mapElements = keySet.keysToArray();
		for (int i = 0; i < keySet.size(); i++) {
			elements[i] = mapElements[i];
		}
		return elements;
	}

	/**
	 * Returns whether the set is empty
	 * 
	 * @return boolean
	 */
	public boolean isEmpty() {
		return keySet.isEmpty();
	}

	/**
	 * Returns the total number of keys in the set
	 * 
	 * @return int
	 */
	public int size() {
		return keySet.size();
	}

	/**
	 * Print the keys
	 */
	public void print() {
		String[] array = keySet.keysToArray();
		for (String s : array) {
			System.out.println(s + " ");
		}
	}

	/***************************************************************************
	 **************************** Member Variables *****************************
	 **************************************************************************/
	// Uses a PuzzleMap as the underlying data structures.
	// Similar to what the text book suggests
	MyMap<Key, ?> keySet;

	/***************************************************************************
	 ***************************** Nested Classes ******************************
	 **************************************************************************/

	public static void main(String[] args) {
		// Testing the insert function
		System.out.println("Testing the insert function");
		System.out.println("adding 1000 string entries of apple+i");
		MySet<String> searchTable = new MySet<String>();
		MySet<String> searchTable2 = new MySet<String>();
		String s;
		for (int i = 1; i < 5; i++) {
			s = "apple" + i;
			searchTable.insert(s);
		}

		for (int i = 3; i < 7; i++) {
			s = "apple" + i;
			searchTable2.insert(s);
		}

		MySet<String> intersection = searchTable.retainAll(searchTable2);
		

		System.out.println("Is searchTable empty: " + searchTable.isEmpty());
		System.out.println("Total entries: " + searchTable.size());

		searchTable.print();

		System.out.println();

		System.out.println("Is searchTable2 empty: " + searchTable2.isEmpty());
		System.out.println("Total entries: " + searchTable2.size());

		searchTable2.print();
		
		System.out.println("Is intersection empty: " + intersection.isEmpty());
		System.out.println("Total entries: " + intersection.size());

		intersection.print();

		
		

	}
}

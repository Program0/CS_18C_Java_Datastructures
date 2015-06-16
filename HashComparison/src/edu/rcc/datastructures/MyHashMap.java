package edu.rcc.datastructures;

import java.util.ArrayList;

//Used Key and Value instead of E or T for generic implementation. It is easier to follow.
public class MyHashMap<Key extends Comparable<? super Key>, Value extends Comparable<? super Value>> {
	// Minimum table size
	private static final int MINIMUM = 5;
	// Range of keys to create hash code by
	private static final int RANGE = 31;
	// Load factor to decide when to resize the hash table
	private static final double LOADFACTOR = 1.0;
	// Large prime numbers to be used as a table size
	private static final int[] PRIMES = { 7, 13, 31, 61, 127, 251, 509, 1021,
			2039, 4093, 8191, 16381, 32749, 65521, 131071, 262139, 524287,
			1048573, 2097143, 4194301, 8388593, 16777213, 33554393, 67108859,
			134217689, 268435399, 536870909, 1073741789, 2147483647 };

	// Array to store linked list of entries
	LinkedListHash<Key, Value>[] hashTable;
	private int tableSize;// Size of the hash table
	private int keyPairs;// Total number of key pairs in the table

	/**
	 * Constructors
	 */
	// Default constructor
	public MyHashMap() {
		// Call the main constructor
		this(MINIMUM);
	}

	// Main constructor
	@SuppressWarnings("unchecked")
	public MyHashMap(int size) {

		// Set the minimum size and allocate memory for hash table
		this.tableSize = size;
		// hashTable = (LinkedListHash<Key, Value>[]) new LinkedListHash[size];

		// Now create a bucked for each index
		// for (int i = 0; i < size; i++) {
		// Now add a linked list as the bucket
		// hashTable[i] = new LinkedListHash<Key, Value>();
		// }

		// Testing different method to allocate memory
		LinkedListHash<Key, Value>[] temp = (LinkedListHash<Key, Value>[]) new LinkedListHash[size];
		hashTable = temp;
	}

	/**
	 * Will return the value associated with the parameter key. Returns null if
	 * the value is not in the hash table.
	 * 
	 * @param key
	 * @return
	 */
	public Value getValue(Key key) {
		// Get the location of the key in the hashTable
		int index = hash(key);

		// Return the value pair of the key.
		// will return null if not in the hash table.
		return hashTable[index].get(key);
	}

	/**
	 * Deletes a key from the hash table. Returns the value of the deleted key
	 * or null if the key was not found in the hash table. It will resize the
	 * hash table if the load factor is less than 0.25 even if an entry is not
	 * found in the hash table
	 * 
	 * @param key
	 * @return value
	 */
	public Value delete(Key key) {
		Value keyValue = null;
		// Find the index of the new entry
		int index = hash(key);

		// If the key is in the hash table delete it
		if (hashTable[index].contains(key)) {
			keyValue = hashTable[index].delete(key);
			// Decrease the number of keys stored in hash table
			keyPairs--;

		}

		// Test if we need to resize.
		// Only resize if the load factor is less than 0.25
		double currentLoad = ((double) this.keyPairs / (double) this.hashTable.length);
		if (this.hashTable.length > MINIMUM && currentLoad < 0.25) {
			int newSize = PRIMES[PRIMES.length - 1];
			int i = PRIMES.length - 1;
			double newLoadFactor = this.keyPairs / (double) newSize;

			// Find the next biggest prime that will restore the
			// load factor back to less than 1.0 and greater than 0.75
			while (newLoadFactor < 0.75 && i > 0) {
				// the next size up (size being a prime number)
				if (newLoadFactor < LOADFACTOR) {
					newSize = PRIMES[i--];
				}
				// Update the load factor
				newLoadFactor = (double) this.keyPairs / newSize;
			}

			// Resize the hash table with the new size
			resize(newSize);
		}

		return keyValue;
	}

	/**
	 * Takes a key value pair to add to the hash table. It checks the current
	 * load factor and resizes the hash table if the current load factor is
	 * greater than 1.0
	 * 
	 * @param key
	 * @param value
	 */
	public void insert(Key key, Value value) {

		// Before adding we need to check if the current load factor is greater
		// than the preset load factor of 1.0
		double currentLoad = ((double) this.keyPairs / (double) this.hashTable.length);
		if (LOADFACTOR < currentLoad) {

			int newSize = PRIMES[0];
			int index = 1;
			double newLoadFactor = this.keyPairs / (double) newSize;

			// Find the next biggest prime that will restore the
			// load factor back to less than 1.0
			while (newLoadFactor > LOADFACTOR && index < PRIMES.length) {
				// the next size up (size being a prime number)
				if (newLoadFactor > LOADFACTOR) {
					newSize = PRIMES[index++];
				}
				// Update the load factor
				newLoadFactor = (double) this.keyPairs / newSize;
			}

			// Resize the hash table with the new size
			resize(newSize);
		}

		// Find the index of the new entry
		int index = hash(key);

		// Testing if this will affect the size
		if (hashTable[index] == null) {
			// System.out.println("linked list at this index is null");
			hashTable[index] = new LinkedListHash<Key, Value>();
			keyPairs++;
			hashTable[index].insert(key, value);
		}

		// If the key is not in the hash table insert it at the appropriate
		// index
		else if (!hashTable[index].contains(key)) {
			// System.out.println("linked list not null but does not contain key");
			keyPairs++;
			hashTable[index].insert(key, value);
		}
		// Otherwise just update the value for the key
		else {
			//
			// System.out.println("key is in the list updating value");
			hashTable[index].updateValue(key, value);
		}

	}

	/**
	 * Searches for a specified key in the hash table. Returns true if found,
	 * false otherwise.
	 * 
	 * @param key
	 * @return
	 */
	public boolean contains(Key key) {
		// Find the index of the new entry
		int index = hash(key);
		// Find if it is in the linked list at this index
		if (hashTable[index] != null) {
			return hashTable[index].contains(key);
		} else {
			return false;
		}

	}

	/**
	 * Returns an array of strings of the hash map keys
	 * 
	 * @return
	 */
	public String[] toArray() {
		String[] elements = new String[keyPairs];
		// Index to add elements to elements array
		int index = 0;
		// Iterate through the hash table to get the keys
		for (int i = 0; i < hashTable.length; i++) {
			// Only look at those buckets that are not empty
			// Do not exceed the number of key pairs in the hash map
			if (hashTable[i] != null && index < keyPairs) {

				// Iterate through the keys at this bucket
				for (Key key : hashTable[i]) {
					elements[index++] = key.toString();
				}
			}
		}
		return elements;
	}

	/**
	 * Clears the hash table of all key value pair entries. It does not resize
	 * the hash table
	 */
	public void clear() {
		// Go through each linked list and clear it
		for (int i = 0; i < hashTable.length; i++) {
			if (hashTable[i] != null)
				hashTable[i].clear();
		}
		// Set the number of key pairs to zero
		keyPairs = 0;
	}

	/**
	 * Returns true if the hash table is empty or false otherwise
	 * 
	 * @return boolean
	 */

	public boolean isEmpty() {
		// If the tableSize is 0, the table is empty
		return keyPairs == 0;
	}

	/**
	 * Returns the internal size of the hash table
	 * 
	 * @return int
	 */
	public int tableSize() {
		return tableSize;
	}

	/**
	 * Returns the total number of keys in the hash table
	 * 
	 * @return int
	 */
	public int totalKeys() {
		return keyPairs;
	}

	public void print() {
		for (int i = 0; i < tableSize; i++) {
			hashTable[i].displayList();
		}
	}

	/**
	 * For testing purposes. Returns the indexes used in the hash table
	 * 
	 * @return
	 */
	public ArrayList<Integer> occupiedIndex() {
		ArrayList<Integer> index = new ArrayList<Integer>();
		for (int i = 0; i < tableSize; i++) {
			if (!hashTable[i].isEmpty())
				index.add(i);

		}
		return index;
	}

	// Hash value between 0 and tableSize
	private int hash(Key key) {

		int hash = hashFunction(key, hashTable.length);
		hash = hash % hashTable.length;
		if (hash < 0)
			hash = hash + hashTable.length;
		// while (hash > hashTable.length) {
		// hash = hash / 10;
		// System.out.println("Dividing hash by 10 hash is now: " + hash);
		// }
		// System.out.println("Hash is:" +hash);
		return hash;
	}

	// Resize the table when the number of entries exceed the size
	private void resize(int newSize) {
		// Resize only if the new size is greater than the required minimum
		if (newSize > MINIMUM) {
			MyHashMap<Key, Value> tempTable = new MyHashMap<Key, Value>(
					newSize);
			for (int i = 0; i < hashTable.length; i++) {
				{
					// Only add those keys where the chains that are not empty
					if (hashTable[i] != null) {
						for (Key key : hashTable[i]) {
							// Put the key in the existing objects hash table
							// in the new hash table
							tempTable.insert(key, hashTable[i].get(key));
						}
					}
				}
			}

			// Update the size of the current object table size
			// and number of key pairs
			this.tableSize = tempTable.tableSize;
			this.keyPairs = tempTable.keyPairs;
			// Change the reference to point to the new hash table
			this.hashTable = tempTable.hashTable;
		}
	}

	// Returning a double only to process hash in hash table
	private int hashFunction(Key key, int size) {
		double temp = 0;
		String s = key.toString();
		for (int i = 0; i < s.length(); i++) {
			temp = RANGE * temp + s.charAt(i);		
		}
		// Code from textbook. We assign the bits from temp to
		// bits then shift the bits 32 bits to the right and cast to an int
		long bits = Double.doubleToLongBits(temp);
		int hash = (int) (bits ^ (bits >> 32));
		return hash;
	}

	public static void main(String[] args) {
		// Testing the insert function
		System.out.println("Testing the insert function");
		System.out.println("adding 1000 string entries of apple+i");
		MyHashMap<String, Integer> searchTable = new MyHashMap<String, Integer>();
		String s;
		for (int i = 0; i < 1000; i++) {
			s = "apple" + i;
			searchTable.insert(s, i);
		}

		System.out.println("Is searchTable empty: " + searchTable.isEmpty());
		System.out.println("Total entries: " + searchTable.totalKeys()
				+ " total table size: " + searchTable.tableSize());

		System.out.println();

		// Testing the contains function
		System.out.println("\nTesting contains() function:");
		System.out.println("hash table contains apple1: "
				+ searchTable.contains("apple1"));
		System.out.println("hash table contains peach: "
				+ searchTable.contains("apple1001"));

		// Testing the delete function
		System.out.println("\nTesting the delete() function for 250 entries"
				+ " from 0 to 249");
		for (int i = 0; i < 250; i++) {
			s = "apple" + i;
			searchTable.delete(s);
		}

		System.out
				.println("Deleting with string that was in hash table apple251 value: "
						+ searchTable.delete("apple251"));
		System.out
				.println("Deleting with string that was not in hash table peach: "
						+ searchTable.delete("peach"));

		System.out.println("Total entries: " + searchTable.totalKeys()
				+ " total table size: " + searchTable.tableSize());

		String[] keys = searchTable.toArray();
		System.out.println("Testing the toArray() function");
		for (String key : keys) {
			System.out.println(key);
		}
		System.out.println();
		// Testing the clear function
		searchTable.clear();
		System.out.println("\nTesting the clear() function");
		System.out.println("Is searchTable empty: " + searchTable.isEmpty());
		System.out.println("Total entries: " + searchTable.totalKeys()
				+ " total table size: " + searchTable.tableSize());

	}

}

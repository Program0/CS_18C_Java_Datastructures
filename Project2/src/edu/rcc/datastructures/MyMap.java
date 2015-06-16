/**
 * @File MyMap.java
 * @author Marlo Zeroth
 * @date May 18, 2015
 * @course CSC18C DataStructures
 */

package edu.rcc.datastructures;

//Used Key and Value instead of E or T for generic implementation. It is easier to follow.
public class MyMap<Key extends Comparable<? super Key>, Value extends Comparable<? super Value>> {
	/************************************************************************
	 ****************************** Constructors******************************
	 ************************************************************************/
	// Default constructor
	public MyMap() {
		// Call the main constructor
		this(MINIMUM);
	}

	// Main constructor
	@SuppressWarnings("unchecked")
	public MyMap(int size) {

		// Set the minimum size and allocate memory for hash table
		this.tableSize = size;
		hashTable = (LinkedListMap<Key, Value>[]) new LinkedListMap[size];

		// Now create a bucked for each index
		for (int i = 0; i < size; i++) {
			// Now add a linked list as the bucket
			hashTable[i] = new LinkedListMap<Key, Value>();
		}
	}

	/***********************************************************************
	 **************************** Mutator Functions**************************
	 ***********************************************************************/

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
		// If the key is not in the hash table insert it at the appropriate
		// index
		if (!hashTable[index].contains(key)) {
			keyPairs++;
			hashTable[index].insert(key, value);
		}
		// Otherwise just update the value for the key
		else {
			hashTable[index].updateValue(key, value);
		}

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

	public boolean deleteKey(Key key) {
		boolean deleted = false;
		// Find the index of the new entry
		int index = hash(key);

		// If the key is in the hash table delete it
		if (hashTable[index].contains(key)) {
			hashTable[index].delete(key);
			deleted = true;
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

		return deleted;
	}

	/***********************************************************************
	 ***************************** User Interface****************************
	 ***********************************************************************/

	/**
	 * Clears the hash table of all key value pair entries. It does not resize
	 * the hash table
	 */
	public void clear() {
		// Go through each linked list and clear it
		for (int i = 0; i < hashTable.length; i++) {
			hashTable[i].clear();
		}
		// Set the number of key pairs to zero
		keyPairs = 0;
	}

	/***********************************************************************
	 *************************** Accessor Functions**************************
	 ***********************************************************************/

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
		return (hashTable[index]!=null?hashTable[index].getValue(key):null);
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
		// If the linked list at this index is not empty.
		// Find if it is in the linked list at this index.
		return (hashTable[index] != null ? hashTable[index].contains(key)
				: false);
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
	public int size() {
		return keyPairs;
	}

	/**
	 * Returns an list of keys associated with a given value
	 * 
	 * @param value
	 * @return MyLinkedList<String>
	 */
	public MyLinkedList<Key> keysToList(Value value) {
		MyLinkedList<Key> elements = new MyLinkedList<Key>();
		// Index to add elements to elements array
		int index = 0;
		// Iterate through the hash table to get the keys
		for (int i = 0; i < hashTable.length; i++) {
			// Only look at those buckets that are not empty
			// Do not exceed the number of key pairs in the hash map
			if (hashTable[i] != null && index < keyPairs) {

				// Iterate through the keys at this bucket
				for (Key key : hashTable[i]) {
					if (getValue(key).equals(value))
						elements.insert(key);
					;
				}
			}
		}
		return elements;
	}

	/**
	 * Returns a list of keys in the dictionary
	 * 
	 * @return MyLinkedList<Key>
	 */
	public MyLinkedList<Key> keysTolist() {
		MyLinkedList<Key> elements = new MyLinkedList<Key>();
		// Index to add elements to elements array
		int index = 0;
		// Iterate through the hash table to get the keys
		for (int i = 0; i < hashTable.length; i++) {
			// Only look at those buckets that are not empty
			// Do not exceed the number of key pairs in the hash map
			if (hashTable[i] != null && index < keyPairs) {

				// Iterate through the keys at this bucket
				for (Key key : hashTable[i]) {
					elements.insert(key);
					;
				}
			}
		}
		return elements;
	}

	/**
	 * Returns a string array of the values in the hash table
	 * 
	 * @return
	 */
	// Work around until I figure out how to get the values
	// in an array for generics
	public String[] valuesToArray() {
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
					elements[index++] = getValue(key).toString();
				}
			}
		}
		return elements;
	}

	/**
	 * Returns an array of strings of the hash map keys
	 * 
	 * @return
	 */

	// Work around until I figure out how to get the values
	// in an array for generics
	public String[] keysToArray() {
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
	 * Returns a set of keys in the hash table
	 * 
	 * @return
	 */
	public MySet<Key> set() {
		MySet<Key> elements = new MySet<Key>();
		// Index to add elements to elements array
		int index = 0;
		// Iterate through the hash table to get the keys
		for (int i = 0; i < hashTable.length; i++) {
			// Only look at those buckets that are not empty
			// Do not exceed the number of key pairs in the hash map
			if (hashTable[i] != null && index < keyPairs) {

				// Iterate through the keys at this bucket
				for (Key key : hashTable[i]) {
					elements.insert(key);
				}
			}
		}
		return elements;
	}

	public void print() {
		for (int i = 0; i < tableSize; i++) {
			hashTable[i].displayList();
		}
	}

	/***************************************************************************
	 ********************************* Constants*********************************
	 **************************************************************************/
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

	/***************************************************************************
	 ****************************** Member Variables*****************************
	 **************************************************************************/

	private int keyPairs;// Total number of key pairs in the table

	private int tableSize;// Size of the hash table
	// Array to store linked list of entries
	LinkedListMap<Key, Value>[] hashTable;

	/***************************************************************************
	 **************************** Utility Functions*****************************
	 **************************************************************************/
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
			MyMap<Key, Value> tempTable = new MyMap<Key, Value>(newSize);
			for (int i = 0; i < hashTable.length; i++) {
				{
					// Only add those keys where the chains that are not empty
					if (!hashTable[i].isEmpty()) {
						for (Key key : hashTable[i]) {
							// Put the key in the existing objects hash table in
							// the
							// new
							// hash table
							tempTable.insert(key, hashTable[i].getValue(key));
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
			// System.out.println("charAt(" + i + ") is " + s.charAt(i));
			// System.out.println("Hascode is now: " + hash);

		}
		// Code from textbook. We assign the bits from temp to
		// bits then shift the bits 32 bits to the right and cast to an int
		long bits = Double.doubleToLongBits(temp);
		int hash = (int) (bits ^ (bits >> 32));
		return hash;
	}
}

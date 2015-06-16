package edu.rcc.datastructures;

import java.util.HashSet;
import java.util.Random;

public class TestSearch {

	/**
	 * 
	 * @param anArray
	 * @param searchItem
	 * @return
	 */
	public static boolean linearSearch(String[] anArray, String searchItem) {
		boolean found = false;
		int index = 0;
		// Loop through the array or until we find the searchItem
		while (!found && (index < anArray.length)) {
			// Check to see if current element is equal to our searchItem
			if (searchItem.equals(anArray[index])) {
				found = true;
			}
			// Go to the next element
			index++;
		}
		return found;
	}

	/**
	 * @param [] anArray, E searchItem An array to search and a search item to
	 *        search in the array
	 * @return boolean Returns true if the item was found, false otherwise
	 */
	// Assumes array has already been sorted
	public static boolean binarySearch(String[] anArray, String searchItem) {
		int low = 0;
		int high = anArray.length - 1;
		while (low <= high) {
			// Go to the middle of the range at each iteration
			int middle = (low + high) / 2;

			// Check to see if we found the target
			if (searchItem.equals(anArray[middle])) {
				return true;
			}
			// If we haven't found it, change the range
			else if (searchItem.compareTo(anArray[middle]) < 0) {
				high = middle - 1;
			} else {
				low = middle + 1;
			}
		}
		// We never found it.
		return false;
	}

	/**
	 * Receives an array and a hash table to be filled with random strings
	 * 
	 * @param anArray
	 * @param charactersToUse
	 * @param stringLength
	 * @param hashTable
	 */
	public static void fill(String[] anArray, String charactersToUse,
			int stringLength, MyHashMap<String, Integer> hashTable) {

		// Random number to select a random character
		Random randomNumber = new Random(System.currentTimeMillis());
		char[] tempString = new char[stringLength];
		// Iterate through the array and fill it
		for (int i = 0; i < anArray.length; i++) {
			// Get random characters and fill an array with it up to the
			// required string length
			for (int j = 0; j < stringLength; j++) {
				tempString[j] = charactersToUse.charAt(randomNumber
						.nextInt(charactersToUse.length()));
			}
			// Insert the new string in the array and hash table
			anArray[i] = new String(tempString);
			hashTable.insert(new String(tempString), i);			
		}
		tempString = null;
	}

	/**
	 * Fills an array only
	 * 
	 * @param anArray
	 * @param charactersToUse
	 * @param stringLength
	 */
	public static void fill(String[] anArray, String charactersToUse,
			int stringLength) {

		// Random number to select a random character
		Random randomNumber = new Random(System.currentTimeMillis());
		char[] tempString = new char[stringLength];
		// Iterate through the array and fill it
		for (int i = 0; i < anArray.length; i++) {
			// Get random characters and fill an array with it up to the
			// required string length
			for (int j = 0; j < stringLength; j++) {
				tempString[j] = charactersToUse.charAt(randomNumber
						.nextInt(charactersToUse.length()));
			}
			// Insert the new string in the array and hash table
			anArray[i] = new String(tempString);
		}
	}

	public static void printArray(String[] anArray) {
		for (String s : anArray) {
			System.out.println(s);
		}
	}

	// Sorts an array using mergeSort
	public static <E extends Comparable<? super E>> void mergeSort(E[] array,
			int first, int last) {
		@SuppressWarnings("unchecked")
		E[] tempArray = (E[]) new Comparable<?>[array.length];
		// To save memory pass a temporary array to the recursive merge sort
		// function.
		// System.out.println("Inside mergeSort Public | array.size: "
		// + array.length + " tempArray.size: " + tempArray.length
		// + " first: " + first + " last: " + last);
		mergeSort(array, tempArray, first, last);
	}

	// Need to make sure method accepts any object that can be compared
	// with another object of the same type so use generics
	private static <E extends Comparable<? super E>> void mergeSort(E[] array,
			E[] tempArray, int first, int last) {
		// Base case
		if (first < last) {
			int mid = first + (last - first) / 2;
			// Divide and conquer. Split into left and right subarrays
			// recursively
			// System.out.println("Inside mergeSort 1st part | array.size: "
			// + array.length + " tempArray.size: " + tempArray.length
			// + " first: " + first + " mid: " + mid + " last: " + last);
			mergeSort(array, tempArray, first, mid);
			// System.out.println("Inside mergeSort 2nd part | array.size: "
			// + array.length + " tempArray.size: " + tempArray.length
			// + " first: " + first + " mid: " + mid + " last: " + last);
			mergeSort(array, tempArray, mid + 1, last);
			// Combine both subarrays back into one array
			mergeArrays(array, tempArray, first, mid, last);

		}

	}

	private static <E extends Comparable<? super E>> void mergeArrays(
			E[] array, E[] tempArray, int first, int mid, int last) {
		// System.out.println("Inside mergeArrays| array.size: " + array.length
		// + " tempArray.size: " + tempArray.length + " first: " + first
		// + " mid: " + mid + " last: " + last);
		// To keep track of which indexes have been merged into the tempArray
		int beginHalf1 = first;// subarray1 starting index
		int beginHalf2 = mid + 1;// subarray2 starting index

		// Need to copy array into tempArray to actually sort it. Important!!
		// Book's algorithm did not work for me.
		for (int i = first; i <= last; i++) {
			tempArray[i] = array[i];
		}
		// To keep track of the current element added to tempArray
		int index = first;
		// Need to keep within range of both sub arrays
		while ((beginHalf1 <= mid) && (beginHalf2 <= last)) {
			// Only copy if 1st half element is less than or equal to element in
			// 2nd half
			if (tempArray[beginHalf1].compareTo(tempArray[beginHalf2]) <= 0) {
				array[index] = tempArray[beginHalf1];
				// go the next element in subarray1 to sort
				beginHalf1++;
			} else {
				array[index] = tempArray[beginHalf2];
				// go the next element in subarray 2 to sort
				beginHalf2++;

			}
			// Go to the next elements in both subarrays to be sorted
			index++;
		}

		// Now we need to copy any of the remaining elements in the first
		// subarray into array[]
		while (beginHalf1 <= mid) {
			array[index] = tempArray[beginHalf1];
			index++;
			beginHalf1++;
		}

	}

	public static void main(String[] args) {

		// Randomly generated string array and a random string to search
		int size = 3250000;
		int stringSize = 20;
		// Only allow these characters
		String charactersAllowed = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String[] searchArray = new String[size];

		// Variables to hold time
		long startTime = 0;
		long endTime = 10;

		MyHashMap<String, Integer> searchTable = new MyHashMap<String, Integer>();
		// Fill the array and the hash table with the allowed characters with
		// strings of predefined size

		// startTime = System.currentTimeMillis();
		fill(searchArray, charactersAllowed, stringSize, searchTable);
		// endTime = System.currentTimeMillis();
		// System.out.println("Filling took: " + (endTime - startTime) / 1000.0f
		// + " seconds");

		// Prepare a random string from those generated strings in searchArray
		Random stringNumber = new Random(System.currentTimeMillis());
		String randomString = "";
		// To determine whether the item was found.
		boolean found = false;

		// Sort the array first
		mergeSort(searchArray, 0, searchArray.length - 1);

		System.out.println("Size of array: " + size);

		for (int i = 0; i < 20; i++) {
			// Reset the timers and boolean
			startTime = 0;
			endTime = 0;
			found = false;
			// Get a random string or a string that is not in the array
			if ((stringNumber.nextInt(2) - 1) < 0) {
				randomString = searchArray[stringNumber.nextInt(size + 1)];
			} else {
				// Create a randome string
				char[] tempString = new char[stringSize];
				// Get random characters and fill an array with it up to the
				// required string length
				for (int j = 0; j < stringSize; j++) {
					tempString[j] = charactersAllowed.charAt(stringNumber
							.nextInt(charactersAllowed.length()));
				}
				// Insert the new string in the array and hash table
				randomString = new String(tempString);
			}

			System.out.println("String to search: " + randomString);
			// Testing the linear search
			// Get the start time
			startTime = System.currentTimeMillis();
			found = linearSearch(searchArray, randomString);
			// Get the stoppage time
			endTime = System.currentTimeMillis();
			// Output the actual time ran.
			System.out.println("Linear Search: Found? " + found + " Time: "
					+ (endTime - startTime) / 1000.0f + " seconds");

			// Testing binary search
			// Reset the timers and boolean
			found = false;
			// Get the start time
			startTime = System.currentTimeMillis();
			// Search for the item
			found = binarySearch(searchArray, randomString);
			// Get the stoppage time
			endTime = System.currentTimeMillis();
			// Output the actual time ran.
			System.out.println("Binary Search: Found? " + found + " Time: "
					+ (endTime - startTime) / 1000.0f + " seconds");

			// Testing the Hash Search
			// Reset the timers and the boolean
			found = false;
			// Begin the hash table search
			startTime = System.currentTimeMillis();
			found = searchTable.contains(randomString);
			endTime = System.currentTimeMillis();
			System.out.println("Hashing Search: Found? " + found + " Time: "
					+ (endTime - startTime) / 1000.0f + " seconds");
			System.out.println();

		}
	}
}

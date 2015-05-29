package edu.rcc.datastructures;

import java.util.Random;

public class RecursiveSort {

	public static <E extends Comparable<? super E>> void insertionSort(
			E[] array, int first, int last) {
		if (first < last) {
			System.out.println("Inside insertionSort last is now:" + last
					+ " and anEntry is: " + array[last]);
			insertionSort(array, first, last - 1);
			insertInOrder(array[last], array, first, last - 1);
		}
	}

	public static <E extends Comparable<? super E>> void insertInOrder(
			E anEntry, E[] array, int begin, int end) {

		
		if (anEntry.compareTo(array[end]) > 0) {
			array[end + 1] = anEntry;
		} else if (begin < end) {
			array[end + 1] = array[end];
			insertInOrder(anEntry, array, begin, end - 1);
		} else {
			array[end + 1] = array[end];
			array[end] = anEntry;
		}

	}

	//
	public static <E extends Comparable<? super E>> void mergeSort(E[] array,
			int first, int last) {
		@SuppressWarnings("unchecked")
		E[] tempArray = (E[]) new Comparable<?>[array.length];
		//To save memory pass a temporary array to the recursive merge sort function.
		mergeSort(array, tempArray, first, last);
	}

	// Need to make sure method accepts any object that can be compared
	// with another object of the same type so use generics
	private static <E extends Comparable<? super E>> void mergeSort(E[] array,
			E[] tempArray, int first, int last) {
		// Base case
		if (first < last) {
			int mid = first + (last - first) / 2;
			//Divide and conquer. Split into left and right subarrays recursively
			mergeSort(array, tempArray, first, mid);			
			mergeSort(array, tempArray, mid + 1, last);
			//Combine both subarrays back into one array
			mergeArrays(array, tempArray, first, mid, last);

		}

	}

	private static <E extends Comparable<? super E>> void mergeArrays(
			E[] array, E[] tempArray, int first, int mid, int last) {
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

		// Now we need to copy any of the remaining elements in the first subarray into array[]
		while (beginHalf1 <= mid) {
			array[index] = tempArray[beginHalf1];
			index++;
			beginHalf1++;
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Integer[] array = { 8, 2, 6, 4, 9, 7, 1 };
		System.out.println("Printing an unsorted array:");
		for (Integer i : array) {
			System.out.print(i + " ");
		}
		System.out.println("Printing the sorted array:");
		insertionSort(array, 0, 6);
		for (Integer i : array) {
			System.out.print(i + " ");
		}

		Random random = new Random(System.currentTimeMillis());
		Integer[] array2 = new Integer[10];
		for (int i = 0; i < 10; i++) {
			array2[i] = new Integer(random.nextInt(10));
		}
		System.out.println("Array2 before sorting:");
		for (Integer i : array2) {
			System.out.print(i + " ");
		}
		System.out.println("Sorting with merge sort");

		mergeSort(array2, 0, 9);
		System.out.println("After merge sorting.");
		for (Integer i : array2) {
			System.out.print(i + " ");
		}

	}

}

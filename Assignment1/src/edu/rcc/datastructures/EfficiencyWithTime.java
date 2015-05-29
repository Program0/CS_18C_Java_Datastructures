package edu.rcc.datastructures;

import java.util.Random;

public class EfficiencyWithTime {

	// Fill the array with 2 digit numbers
	public static int[] fillArray(int n) {
		int array[] = new int[n];
		Random rand = new Random();
		int max = n;
		int min = 10;
		for (int i = 0; i < array.length; i++) {
			array[i] = rand.nextInt(max - min + 1) + min;
		}
		return array;
	}

	// Print the array
	public static void print(int a[], int perLine) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
			if (i % perLine == (perLine - 1))
				System.out.println();
		}
	}

	// Find the max
	public static int max(int a[]) {
		int amax = a[0];
		for (int i = 1; i < a.length; i++) {
			if (amax < a[i])
				amax = a[i];
		}
		return amax;
	}

	// Find the min
	public static int min(int a[]) {
		int amin = a[0];
		for (int i = 1; i < a.length; i++) {
			if (amin > a[i])
				amin = a[i];
		}
		return amin;
	}

	/**
	 * Sort and Search functions
	 */
	// Assumes array has already been sorted
	public static boolean binarySearch(int target, int a[]) {
		int low = 0;
		int high = a.length;
		while (low <= high) {
			// Go to the middle of the range at each iteration
			int middle = (low + high) / 2;

			// Check to see if we found the target
			if (a[middle] == target) {				
				return true;
			}
			// If we haven't found it, change the range
			else if (a[middle] > target) {
				high = middle - 1;
			} else {
				low = middle + 1;
			}
		}
		// We never found it.
		return false;
	}

	public static int[] bubbleSort(int arr[]) {
		boolean swapped = true;
		int j = 0;
		int tmp;
		// Continue sorting until all of the array is sorted
		while (swapped) {
			swapped = false;
			j++;
			for (int i = 0; i < arr.length - j; i++) {

				if (arr[i] > arr[i + 1]) {
					tmp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = tmp;
					swapped = true;
				}
			}
		}
		return arr;
	}

	// Simplest Sort -> MarkSort
	public static int[] markSort(int a[]) {
		// Declare and use
		int temp;
		int nm1 = a.length - 1;
		int n = a.length;
		// Loop for each cap of lst

		for (int cap = 0; cap < nm1; cap++) {
			// Loop on every element below the cap
			for (int lst = cap + 1; lst < n; lst++) {
				if (a[cap] > a[lst]) {
					temp = a[cap];
					a[cap] = a[lst];
					a[lst] = temp;
				}
			}
		}
		return a;
	}

	public static void main(String[] args) {
		// Declare variables
		int size = 150000;
		int array[] = fillArray(size);
		long startTime;// Hold the start of the calculation
		long endTime;// Hold the end of the calculation

		// Output the efficiency using time
		System.out.println("Testing using markSort");
		System.out.println("N = " + array.length);
		// print(array,10);
		// Begin time for markSort()
		startTime = System.currentTimeMillis();
		// Sort the array
		markSort(array);
		endTime = System.currentTimeMillis();
		// Output the efficiency
		System.out.println("That took " + (endTime - startTime) / 1000.0f
				+ " seconds"+ " endtime: "+endTime +" start:" + startTime);

		// Use a different array and fill it randomly to sort with bubbleSort
		int array2[] = fillArray(size);
		// print(array2,10);

		System.out.println("Testing using bubbleSort:");
		System.out.println("N = " + array2.length);
		// Reset the times.
		startTime = 0;
		endTime = 0;
		// Begin time for bubbleSort()
		startTime = System.currentTimeMillis();
		// Testing bubbleSort() now
		bubbleSort(array2);
		endTime = System.currentTimeMillis();
		// Output the efficiency
		System.out.println("That took " + (endTime - startTime) / 1000.0f
				+ " seconds"+ " endtime: "+endTime +" start:" + startTime);
		// Test binary search. Need to test using a random number
		Random randomSearch = new Random();
		int max = size;
		int min = 10;
		int searchNumber = (randomSearch.nextInt(max - min + 1) + min);
		startTime = 0;
		endTime = 0;
		
		
		// Begin time for binarySearch()
		startTime = System.currentTimeMillis();
		boolean found = binarySearch(searchNumber, array2);
		endTime = System.currentTimeMillis();
		System.out.println("Binary search found the number " + searchNumber
				+ " in array: " + found);
		// Output the efficiency
				System.out.println("That took "+(endTime - startTime) / 1000.0f
						+ " seconds" + " endtime: "+endTime +" start:" + startTime);

	}

}

package edu.rcc.datastructures;

import java.util.Random;

public class Assignment1 {
	public static int logicOperation;
	public static int assignmentOperation;
	public static int arithmeticOperation;

	// Fill the array with 2 digit numbers
	public static int[] fillArray(int n) {
		int array[] = new int[n];
		Random rand = new Random();
		int max = 199;
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
		assignmentOperation += 2;
		logicOperation++;
		while (low <= high) {
			if (logicOperation > 1) {
				logicOperation++;
			}
			// Go to the middle of the range at each iteration
			int middle = (low + high) / 2;
			arithmeticOperation += 2;
			assignmentOperation++;
			// Check to see if we found the target
			logicOperation++;
			if (a[middle] == target) {
				System.out.println("I found it!");
				assignmentOperation++;
				return true;
			}
			// If we haven't found it, change the range
			else if (a[middle] < target) {
				logicOperation++;
				low = middle + 1;
				assignmentOperation++;
				arithmeticOperation++;
			} else {
				high = middle - 1;
				assignmentOperation++;
				arithmeticOperation++;
			}
		}
		// We never found it.
		return false;
	}

	public static int[] bubbleSort(int arr[]) {
		boolean swapped = true;
		int j = 0;
		int tmp;
		assignmentOperation += 3;
		logicOperation++;
		// Continue sorting until all of the array is sorted
		while (swapped) {
			if (j > 1) {
				logicOperation++;
			}
			swapped = false;
			j++;
			assignmentOperation += 2;
			arithmeticOperation++;
			for (int i = 0; i < arr.length - j; i++) {
				arithmeticOperation += 3;
				logicOperation += 2;
				if (arr[i] > arr[i + 1]) {
					tmp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = tmp;
					swapped = true;
					arithmeticOperation += 2;
					assignmentOperation += 4;
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
		assignmentOperation++;
		for (int cap = 0; cap < nm1; cap++) {
			logicOperation++;
			arithmeticOperation++;
			// Loop on every element below the cap
			assignmentOperation++;
			arithmeticOperation++;
			for (int lst = cap + 1; lst < n; lst++) {
				logicOperation++;
				arithmeticOperation += 2;
				if (a[cap] > a[lst]) {
					temp = a[cap];
					a[cap] = a[lst];
					a[lst] = temp;
					assignmentOperation += 3;
				}
			}
		}
		return a;
	}

	public static void main(String[] args) {
		// TODO code application logic here
		// Declare variables
		int size = 400;
		int array[] = fillArray(size);
		// Print the array and the min and max
		// print(array,10);
		System.out.println("The max = " + max(array));
		System.out.println("The min = " + min(array));
		// Sort the array
		print(markSort(array), 10);
		// Output the efficiency
		System.out.println("Using markSort");
		System.out.println("N = " + array.length);
		System.out.println("nEq = " + assignmentOperation);
		System.out.println("nAdd = " + arithmeticOperation);
		System.out.println("nLog = " + logicOperation);
		System.out.println("Total = "
				+ (assignmentOperation + arithmeticOperation + logicOperation));
		// Reset the count of operations
		assignmentOperation = 0;
		arithmeticOperation = 0;
		logicOperation = 0;
		// Use a different array and fill it randomly to sort with bubbleSort
		int array2[] = fillArray(size);
		// print(array2,10);

		System.out.println("The max = " + max(array2));
		System.out.println("The min = " + min(array2));
		// Sort the array
		
		print(bubbleSort(array2), 10);
		// Output the efficiency
		System.out.println("Using bubbleSort:");
		System.out.println("N = " + array2.length);
		System.out.println("nEq = " + assignmentOperation);
		System.out.println("nAdd = " + arithmeticOperation);
		System.out.println("nLog = " + logicOperation);
		System.out.println("Total = "
				+ (assignmentOperation + arithmeticOperation + logicOperation));

		// reset the operations
		assignmentOperation = 0;
		arithmeticOperation = 0;
		logicOperation = 0;
		Random randomSearch = new Random();
		int max = 199;
		int min = 10;
		int searchNumber = (randomSearch.nextInt(max - min + 1) + min);
		System.out.println("Binary search found the number "
				+ searchNumber + " in array: "
				+ binarySearch(searchNumber, array));
		System.out.println("N = " + array.length);
		System.out.println("nEq = " + assignmentOperation);
		System.out.println("nAdd = " + arithmeticOperation);
		System.out.println("nLog = " + logicOperation);
		System.out.println("Total = "
				+ (assignmentOperation + arithmeticOperation + logicOperation));
	}

}

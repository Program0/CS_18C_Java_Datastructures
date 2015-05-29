/**
 * @author Marlo Zeroth
 * @date April 9, 2015 9:45PM
 * @course CSC18C Data Structures with Java - CSC18C 42030
 * @school Riverside Community College
 * @homework Recursion assignment
 */

package edu.rcc.datastructures;

import java.math.BigDecimal;
import java.util.Scanner;

public class RecursiveFunctions {
	/**
	 * Takes an integer and recursively sums from 1 to that integer
	 * 
	 * @param Any
	 *            positive integer
	 * @return The sum from 1 to the integer
	 */
	public static int sumRecursive(int from, int to) {
		// Base case. Stop when we reach 1.
		if (to <=from) {
			System.out.println("In if, from:" + from + " to:" + to);
			return to;
		}
		System.out.println("Halving:");
		int half = (to + from) / 2;

		// Recursive cases

		System.out.println("before sum1="+ " sum(" + from + ", " + half
				+ ") to:" + to);

		int sum1 = sumRecursive(from, half);

		System.out.println("after sum1="+" sum(" + from + ", " + half
				+ ") sum1: " + sum1);

		System.out.println("before sum2="+" sum(" + half + ", " + to
				+ ") from:" + from);

		int sum2 = sumRecursive(half+1, to);

		System.out.println("after sum2="+ " sum(" + half + ", " + to
				+ ") sum2:"+sum2);

		System.out.println("Sum of: " +sum1+"+"+sum2+"="+ (sum1 + sum2));
		int sumTotal = sum1 + sum2;

		// int sum = to + sumRecursive(from, to - 1);
		// System.out.println("Returning sum = " + sum + " The sum of " + to
		// + " and " + sum);
		return sumTotal;
	}

	public static int max(int[] a, int beg, int end) {
		if (end - beg <= 1) {
			System.out.println("beg=" + beg + " a[beg]=" + a[beg]);
			return a[beg];
		}

		int half = (beg + end) / 2;
		System.out.println("before m1 recursive max(a," + beg + ", " + half
				+ ") end=" + end);
		int m1 = max(a, beg, half);
		System.out.println("after m1 recursive m1=" + m1 + " max(" + beg + ", "
				+ half + ") end=" + end);
		System.out.println("before m2 recursive max(a," + half + ", " + end
				+ ") beg=" + beg);
		int m2 = max(a, half, end);
		System.out.println("after m2 recursive m2=" + m2 + " max(" + half
				+ ", " + end + ") beg=" + beg);
		System.out
				.println("will return max this call = " + (m1 > m2 ? m1 : m2));

		return (m1 > m2 ? m1 : m2);
	}

	public static int sum(int from, int to) {
		if (to <= from) {
			return to;
		}
		System.out.println("recursive call from: " + from + " to: " + to);
		int total = to + sum(from, to - 1);
		System.out.println("after recursive call from: " + from + " to:" + to
				+ " total is:" + total);
		return total;
	}

	/**
	 * Takes an integer and iteratively sums from 1 to that integer
	 * 
	 * @param Any
	 *            positive integer
	 * @return The sum from 1 to the integer
	 */
	public static int sumIterative(int from, int to) {
		int sum = 0;
		for (int i = from; i <= to; i++) {
			sum += i;
		}
		return sum;
	}

	/**
	 * Calculates the final value given a principal, interest rate per year, and
	 * the number of years. Uses BigDecimal class.
	 * 
	 * @param presentValue
	 *            The principal or present value
	 * @param interestRate
	 *            The interest rate per year in the form of 0.xxx
	 * @param numberOfYears
	 *            The number of years to repeat the calculation
	 * @return finalValue Returns the total at the end of n years.
	 */
	public static BigDecimal savings(BigDecimal presentValue,
			BigDecimal interestRate, int numberOfYears) {
		if (numberOfYears < 1) {
			// System.out.println("Base Case present value is: "+presentValue);
			return presentValue;
		}
		presentValue = presentValue.add(interestRate.multiply(presentValue));
		// System.out.println("Recursive Case present value is: "+presentValue);
		return (savings(presentValue, interestRate, numberOfYears - 1));
	}

	/**
	 * Calculates the final value given a principal, interest rate per year, and
	 * the number of years. Uses primitive double type.
	 * 
	 * @param presentValue
	 *            The principal or present value
	 * @param interestRate
	 *            The interest rate per year in the form of 0.xxx
	 * @param numberOfYears
	 *            The number of years to repeat the calculation
	 * @return finalValue Returns the total at the end of n years.
	 */

	// Test recursive function before using the BigDecimal class.
	// Code for monetary values should NEVER use double or float
	// However this is what the Math.power() function returns.
	public static double savings(double presentValue, double rate, int years) {
		if (years < 1) {
			// System.out.println("Base Case - Present Value now is: "
			// + presentValue);
			return presentValue;
		}
		// System.out.println("Recursive Case - Present Value now is: "
		// + presentValue);
		presentValue += rate * presentValue;
		return (savings(presentValue, rate, years - 1));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// To use with sum() function
		int start = 1;
		int end = 10;
		// To use with the savings function
		double presentValueD = 0.0;
		int term = 0;
		double rate = 0.0;
		BigDecimal presentValue;
		BigDecimal interestRate;

		// Test out the max function
		int a1[] = { 5, 4, 3, 2, 1 };
		int a2[] = { 2, 3, 5, 4, 1 };
		int a3[] = { 1, 2, 3, 4, 5 };
		// System.out.println("\nMax value in the array = "+max(a1,0,a1.length));
		// System.out.println("\nMax value in the array = "+max(a2,0,a2.length));
		// System.out.println("\nMax value in the array = "+max(a3,0,a3.length));

		System.out.println("Testing regular recursive sum");
		int test = sum(start, end);
		System.out.println("test is: " + test);

		// Now calculate the sum using iterative and recursive methods
		System.out.println("Sum from" + start + " to " + end
				+ " using recursion: " + sumRecursive(start, end));

		System.out.println();
		System.out.println("Sum from" + start + " to " + end
				+ " using iteration: " + sumIterative(start, end));
		System.out.println();

		presentValue = BigDecimal.valueOf(1000.00);
		interestRate = BigDecimal.valueOf(0.1);
		System.out
				.println("Final balance with principal of $1,000 at 10% interest for 5 years");
		System.out.println("using recursive with BigDecimal parameter is: $"
				+ savings(presentValue, interestRate, term).setScale(2,
						BigDecimal.ROUND_CEILING));
		System.out.println();

		System.out
				.println("Final value with principal $1,000 for 10 years at 5% interest per year");
		System.out
				.println("using recurvive function and type double parameter is: $"
						+ savings(1000.00, 0.10, 5));

	}

}

/**
 * @author Marlo Zeroth
 * @date April 9, 2015 9:45PM
 * @course CSC18C Data Structures with Java - CSC18C 42030
 * @school Riverside Community College
 * @homework Recursion assignment
 */

package edu.rcc.datastructures;

import java.math.BigDecimal;

public class RecursiveFunctions {

	/**
	 * Takes an integer and recursively sums from 1 to that integer
	 * 
	 * @param Any
	 *            positive integer
	 * @return The sum from 1 to the integer
	 */
	public static int sumRecursive(int n) {
		// Base case. Stop when we reach 1.
		if (n < 2) {
			return 1;
		}
		// Recursive case
		return n + sumRecursive(n - 1);
	}

	/**
	 * Takes an integer and iteratively sums from 1 to that integer
	 * 
	 * @param Any
	 *            positive integer
	 * @return The sum from 1 to the integer
	 */
	public static int sumIterative(int n) {
		int sum = 0;
		for (int i = 1; i <= 10; i++) {
			sum += i;
		}
		return sum;
	}

	/**
	 * Calculates the final value given a principal, interest rate per year, and
	 * the number of years. Uses BigDecimal class.
	 * @param presentValue The principal or present value
	 * @param interestRate The interest rate per year in the form of 0.xxx
	 * @param numberOfYears The number of years to repeat the calculation
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
	 * @param presentValue The principal or present value
	 * @param interestRate The interest rate per year in the form of 0.xxx
	 * @param numberOfYears The number of years to repeat the calculation
	 * @return finalValue Returns the total at the end of n years.
	 */

	// Test recursive function before using the BigDecimal class.
	// Code for monetary values should NEVER use double or float
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

		System.out.println("Sum from 1 to 10 using recursive: "
				+ sumRecursive(10));

		System.out.println();
		System.out.println("Sum from 1 to 10 using iteration: "
				+ sumIterative(10));
		System.out.println();
		BigDecimal presentValue = BigDecimal.valueOf(1000.00);
		BigDecimal interestRate = BigDecimal.valueOf(0.1);
		int term = 5;
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

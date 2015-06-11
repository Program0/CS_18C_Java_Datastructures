/**
 * @File Guess.java
 * @author Marlo Zeroth
 * @date May 18, 2015
 * @course CSC18C DataStructures
 */

package edu.rcc.datastructures;

public class Guess <E>{

	// Default constructor
	Guess(){
		data = null;
		bulls = 0;
		cows = 0;
	}
	
	// Main constructor
	Guess(E data,int attempt, int cows, int bulls){
		this.data = data;
		this.attempt = attempt;
		this.cows = cows;
		this.bulls = bulls;
	}
	
	/**
	 * Return the number of cows associate with the guess
	 * @return
	 */
	public int getCows(){
		return cows;
	}
	
	/**
	 * return the number of bulls associated with this guess
	 * @return bulls
	 */
	public int getBulls(){
		return bulls;
	}
	
	/**
	 * Return the guess data 
	 * @return data
	 */
	// Return the guess data
	public E getGuess(){
		return data;
	}
	
	public String toString(){
		String s = "Attempt: " + attempt+" Guess: " + data.toString() + " Bulls: "+bulls + " Cows: "+cows;
		return s;
	}
	
	private E data;
	private int attempt;
	private int bulls;
	private int cows;
	
	
}

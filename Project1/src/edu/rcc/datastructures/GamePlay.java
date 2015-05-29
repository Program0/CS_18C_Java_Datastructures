/**
 * @File GamePlay.java
 * @author Marlo Zeroth
 * @date May 18, 2015
 * @course CSC18C DataStructures
 */

package edu.rcc.datastructures;

import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class GamePlay {

	public static final Scanner input = new Scanner(System.in);

	/**
	 * Returns the chosen difficulty for the game.
	 * 
	 * @return
	 */
	public static int getDifficulty() {
		// Print out a menu for the difficulty
		System.out.println("Choose your difficulty level: ");
		System.out.println("Press (e) for easy. ");
		System.out.println("Press (m) for medium. ");
		System.out.println("Press (h) for hard. ");
		System.out.println("Press anything else to exit. ");

		// Get the difficulty
		String choice = input.nextLine();
		choice = choice.toLowerCase();

		switch (choice) {
		case "e":
			return 3;
		case "m":
			return 4;
		case "h":
			return 6;
		default:
			System.out.println("You entered: " + choice + " exiting now.");
			return -1;
		}

	}

	/**
	 * Returns the users input for an option
	 * 
	 * @return option
	 */
	public static String getOption() {
		// Prompt for input
		System.out.println("Press (y) for yes. Anything else for no.");
		// Get the user's option
		String option = input.nextLine();
		// Change it to lower case
		option = option.toLowerCase();
		return option;
	}

	/**
	 * Gets user input recursively for a guess to solve the puzzle
	 * 
	 * @param puzzle
	 * @return guess
	 */
	public static String getGuess(String puzzle, String option) {

		String guess = "";
		boolean valid = false;
		// Keep getting user input until it is valid
		while (!valid) {
			guess = "";
			System.out.println("Valid guess are " + puzzle.length()
					+ " letter words." + " \nEnter your guess: ");
			guess = input.nextLine();
			valid = validGuess(guess, puzzle);
			guess = guess.toLowerCase();

		}
		System.out.println("You entered: " + guess + "\nIs this correct?");
		option = getOption();

		if (option.equalsIgnoreCase("y")) {
			return guess;
		} else {
			return getGuess(puzzle, option);
		}

	}

	/**
	 * Prints a history of the guess made
	 * 
	 * @param guesses
	 * @param bulls
	 */
	public static void printGuesses(MyQueue<Guess<String>> guesses) {

		// Iterate through each guess and print the guess, the bulls, and cows
		for (Guess<String> g : guesses) {
			System.out.println(g);
		}
	}

	public static void guessLoop(int tries, String puzzle,
			MyQueue<Guess<String>> guessQueue, boolean won) {

		String guess = "";
		String option = "";
		int guesses = 0;

		// String to use as a dummy until all guesses are used or puzzle solved
		String dummy = "";
		for (int i = 0; i < puzzle.length(); i++) {
			dummy = dummy + "?";
		}

		boolean solved = false;
		MyMap<String, Integer> bullcows = null;
		while (guesses < tries && !solved) {

			// Reset the bulls and cows
			bullcows = null;

			// Get the user's guess
			System.out.println("Attempt #" + (guesses + 1));

			// Get the users guess
			guess = getGuess(puzzle, option);

			// Get the number of bulls and cows for the guess
			bullcows = cows(guess, puzzle);

			// Create a new guess to put in the guessQueue
			Guess<String> newGuess = new Guess<String>(guess, guesses + 1,
					bullcows.getValue("cows"), bullcows.getValue("bulls"));

			guessQueue.enqueue(newGuess);

			// Print the guess history with bulls and cows for each guess
			System.out.println();
			System.out.println("Code word: " + dummy);
			printGuesses(guessQueue);
			System.out.println();

			if (guess.equals(puzzle)) {
				solved = true;
			}
			// If guess was wrong go to the next attempt.
			guesses++;
		}

		if (solved) {
			System.out.println("You win!!");
			System.out.println("Code word: " + puzzle);
			printGuesses(guessQueue);
			System.out.println();
		} else {
			System.out.println("Better luck next time.");
			System.out.println("Code word: " + puzzle);
			printGuesses(guessQueue);
			System.out.println();
		}
		// Set whether the game was won or not.
		won = solved;
	}

	/**
	 * Validates an user input for a guess. If input is valid returns true.
	 * 
	 * @param guess
	 * @param puzzle
	 * @return boolean
	 */
	public static boolean validGuess(String guess, String puzzle) {
		if (guess.length() < puzzle.length()
				|| guess.length() > puzzle.length()) {
			return false;
		}
		int i = 0;
		// Go through the guess and make sure it is all letters
		while (i < guess.length()) {
			// If any element is not a letter the guess is not valid
			if (!Character.isLetter(guess.charAt(i))) {
				return false;
			}
			i++;
		}
		// The guess is valid
		return true;
	}

	/**
	 * Returns the total bulls and cows in a guess from passed puzzle
	 * 
	 * @param guess
	 * @param puzzle
	 * @return MyMap<String, Integer>
	 */
	public static MyMap<String, Integer> cows(String guess, String puzzle) {

		// Map to return with bulls and cows
		MyMap<String, Integer> bullcow = new MyMap<String, Integer>();

		int cows = 0; // If letters are in the puzzle, but wrong spot
		int bulls = 0; // If letters are in the puzzle and right spot

		if (validGuess(guess, puzzle)) {
			// Maps for the guess and puzzle
			MyMap<Character, Integer> puzzleMap = new MyMap<Character, Integer>();
			MyMap<Character, Integer> guessMap = new MyMap<Character, Integer>();

			for (int i = 0; i < puzzle.length(); i++) {
				if (guess.charAt(i) == puzzle.charAt(i)) {
					bulls++;
				}

				// Fill both maps with the frequency value for each letter key

				if (puzzleMap.contains(puzzle.charAt(i))) {
					int count = puzzleMap.getValue(puzzle.charAt(i));
					puzzleMap.insert(puzzle.charAt(i), count + 1);
				} else {
					puzzleMap.insert(puzzle.charAt(i), 1);
				}

				if (guessMap.contains(guess.charAt(i))) {
					int count = guessMap.getValue(guess.charAt(i));
					guessMap.insert(guess.charAt(i), count + 1);
				} else {
					guessMap.insert(guess.charAt(i), 1);
				}
			}
			// Now get the intersection of the guess and puzzle sets
			MySet<Character> intersection = puzzleMap.set().retainAll(
					guessMap.set());
			// The cows are those keys that are not in the right position
			cows = intersection.size() - bulls;

		}
		bullcow.insert("bulls", bulls);
		bullcow.insert("cows", cows);
		return bullcow;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Text file with words from american-english dictionary
		// Found at http://www.andrew.cmu.edu/course/15-121/dictionary.txt
		File file = new File("american-english.txt");

		// Dictionaries with isograms ranging from easy to hard
		MyMap<String, Integer> easyDictionary = new MyMap<String, Integer>();
		MyMap<String, Integer> mediumDictionary = new MyMap<String, Integer>();
		MyMap<String, Integer> hardDictionary = new MyMap<String, Integer>();

		// Fill the three dictionaries
		MyFileReader.fillDictionary(file, easyDictionary, 3);
		MyFileReader.fillDictionary(file, mediumDictionary, 4);
		MyFileReader.fillDictionary(file, hardDictionary, 6);

		// Lists of puzzle words ranging from easy to hard
		// Easy each letter in a word appears only once
		MyLinkedList<String> easySetting = easyDictionary.keysTolist();
		// Medium setting: each letter can appear once or twice in the word
		MyLinkedList<String> mediumSetting = mediumDictionary.keysTolist();
		// This is only for first order isogram (each letter appears only once)
		MyLinkedList<String> hardSetting = hardDictionary.keysToList(1);

		// For user option to continue game and get difficulty
		String option = "";

		// For guesses and puzzle
		String puzzle = "";

		// For difficulty
		int difficult = 0;
		// Index to randomly choose a letter from a dictionary
		int index = 0;

		// Default number of tries. Could be adapted to difficulty later
		// More tries for more difficult puzzles?
		int tries = 8;
		int game = 0;

		// Stack to keep track of played games
		MyStack<Game<String>> playHistory = new MyStack<Game<String>>();
		boolean won = false;

		System.out.println("Welcome to bulls and cows,"
				+ " the code breaking game.\n");
		System.out
				.println("Code words are isograms."
						+ " The letters in the isogram word appear the same number of times.\n");
		System.out.println("Code words for easy are 3 letters,"
				+ " for medium 4 letters, and for hard 6 letters.");
		System.out
				.println("Code word example: apt 3 letters and each letter appears only once.");
		System.out
				.println("Code word example: deed. 4 letters and each letter appears twice.");
		System.out
				.println("Code word example: orgasm 6 letters and each letter appears only once.");
		System.out
				.println("Letters may show multiple times for medium and hard.");

		System.out.println("Available code words in the dictionaries");
		System.out.println("Easy Dictionary: " + easyDictionary.size());
		// gameDictionary.print();
		System.out.println("Medium Dictionary: " + mediumDictionary.size());
		System.out.println("Hard Dictionary: " + hardDictionary.size());
		System.out.println();
		Random randomNumber = new Random(System.currentTimeMillis());

		do {

			
			won = false;

			// Guess queue to be used later in the play history
			MyQueue<Guess<String>> guessQueue = new MyQueue<Guess<String>>();
			difficult = getDifficulty();

			switch (difficult) {
			case (3):
				// Increase the number of games
				game++;
				// Get the index for the puzzle to use
				index = randomNumber.nextInt((easySetting.size()) + 1);
				// Set the puzzle from the easy dictionary
				puzzle = easySetting.get(index).toLowerCase();
				// System.out.println("Easy difficulty: " + puzzle + " index: "
				// + index);
				// Begin the guess loop for this game
				guessLoop(tries, puzzle, guessQueue, won);
				break;
			case (4):
				// Increase the number of games
				game++;
				// Get the index for the puzzle to use
				index = randomNumber.nextInt((mediumSetting.size()) + 1);
				// Set the puzzle from the medium dictionary
				puzzle = mediumSetting.get(index).toLowerCase();
				// System.out.println("Medium difficulty: " + puzzle +
				// " index: "
				// + index);
				// Begin the guess loop for this game
				guessLoop(tries, puzzle, guessQueue, won);

				break;
			case (6):
				// Increase the number of games
				game++;
				// Get the index for the puzzle to use
				index = randomNumber.nextInt((hardSetting.size()) + 1);
				// Set the puzzle from the hard dictionary
				puzzle = hardSetting.get(index).toLowerCase();
				// System.out.println("Hard difficulty: " + puzzle + " index: "
				// + index);
				// Begin the guess loop for this game
				guessLoop(tries, puzzle, guessQueue, won);
				break;
			default:
				System.out.println("You didn't choose a valid option.");
				break;
			}

			Game<String> currentGame = new Game<String>(game, won, puzzle, guessQueue);
			playHistory.push(currentGame);

			System.out.println("\nWould you like to play again?");
			option = getOption();

		} while (option.equalsIgnoreCase("y"));

		System.out.println("Would you like to see your play history?");
		option = getOption();

		if (option.equalsIgnoreCase("y")) {
			System.out.println("Play history\n");
			while (!playHistory.isEmpty()) {				
				System.out.println(playHistory.pop().toString());
			}
		}

		input.close();
	}
}

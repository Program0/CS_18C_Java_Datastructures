/**
 * @File MyFileReader.java
 * @author Marlo Zeroth
 * @date May 18, 2015
 * @course CSC18C DataStructures
 * Class is for helper functions and for input output of files
 */

package edu.rcc.datastructures;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MyFileReader {

	/**
	 * Returns true if a word is an isogram. False otherwise. It looks at the
	 * frequency of each letter in the word to determine if it is an isogram
	 * 
	 * @param word
	 * @return boolean
	 */
	public static boolean isogram(String word) {

		MyMap<Character, Integer> isogramWord = new MyMap<Character, Integer>();
		// Get the frequencies of the letters in the words
		for (int i = 0; i < word.length(); i++) {
			if (isogramWord.contains(word.charAt(i))) {
				int count = isogramWord.getValue(word.charAt(i));
				isogramWord.insert(word.charAt(i), count + 1);
			} else {
				isogramWord.insert(word.charAt(i), 1);
			}
		}
		// Test to see if the frequencies for all the letters is equal
		String[] value = isogramWord.valuesToArray();
		for (int i = 0; i < value.length - 1; i++) {
			// If they differ we don't have an isogram
			if (!value[i].equals(value[i + 1])) {
				return false;
			}
		}
		// If we get this far we have an isogram
		return true;

	}

	/**
	 * Takes in a word returns the score based on the frequency of the letters
	 * in the word. If the frequency is not the same it returns -1.
	 * 
	 * @param word
	 * @return int
	 */
	public static int wordScore(String word) {
		if (isogram(word)) {
			MyMap<Character, Integer> isogramWord = new MyMap<Character, Integer>();
			// Get the frequencies of the letters in the words
			for (int i = 0; i < word.length(); i++) {
				if (isogramWord.contains(word.charAt(i))) {
					int count = isogramWord.getValue(word.charAt(i));
					isogramWord.insert(word.charAt(i), count + 1);
				} else {
					isogramWord.insert(word.charAt(i), 1);
				}
			}
			// Test to see if the frequencies for all the letters is equal
			String[] value = isogramWord.valuesToArray();
			return Integer.parseInt(value[0]);

		}
		return -1;
	}

	/**
	 * Takes a map fills it with words according the passed length of the word with
	 * isograms from a text file.
	 * 
	 * @param file
	 * @param map
	 * @param wordList
	 * @param wordLength
	 */
	public static void fillDictionary(File file, MyMap<String, Integer> map, int wordLength) {

		try {
			BufferedReader fromFile = new BufferedReader(new FileReader(file));

			String line = null;
			// Go through each line it the file and process it
			do {
				line = fromFile.readLine();
				// In case the first line is null
				if (line == null)
					break;
				// Fill the word dictionary to be used in the puzzles
				if (line.length() == wordLength
						&& line.charAt(wordLength - 2) != '\'' && isogram(line)) {
					map.insert(line, wordScore(line));					
				}

			} while (line != null);
			fromFile.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("PrintWriter error opening " + file);
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

/**
 * @File Game.java
 * @author Marlo Zeroth
 * @date May 18, 2015
 * @course CSC18C DataStructures
 */


package edu.rcc.datastructures;

public class Game<E> {


	/************************************************************************
	 ****************************** Constructors******************************
	 ************************************************************************/
	//Default constructor
	Game() {
		this(0, false, null, null);
	}

	// Main constructor
	Game(int game, boolean won, E puzzle, MyQueue<Guess<E>> guess) {
		this.game = game;
		this.won = won;
		this.puzzle = puzzle;
		if (guess != null) {
			this.guesses = new MyQueue<Guess<E>>();
			for (Guess<E> g : guess) {
				this.guesses.enqueue(g);
			}
		} else {
			this.guesses = guess;
		}
	}

	/***********************************************************************
	 *************************** Mutator Functions**************************
	 ***********************************************************************/

	/**
	 * Will set the guesses of a game
	 * 
	 * @param guess
	 */
	public void setGuesses(MyQueue<Guess<E>> guess) {

		if (guess != null) {
			// If the internal guesses are not empty
			if (!guesses.isEmpty()) {
				this.guesses = new MyQueue<Guess<E>>();
				for (Guess<E> g : guess) {
					this.guesses.enqueue(g);
				}
			}
			// If they are, clear them then add the new guesses
			else {
				this.guesses.clear();
				for (Guess<E> g : guess) {
					this.guesses.enqueue(g);
				}
			}
		}
		else{
			this.guesses = guess;
		}

	}

	// Default setters and getters
	public void setGameNumber(int game) {
		this.game = game;
	}

	public void setWon(boolean won) {
		this.won = won;
	}
	
	public void setPuzzle(E puzzle) {
		this.puzzle = puzzle;
	}
	
	/***********************************************************************
	 *************************** Accessor Functions ************************
	 ***********************************************************************/

	/**
	 * Returns a copy of the guesses in the game. Returns null if there are no guesses
	 * or the guess queue is empty.
	 * @return
	 */
	public MyQueue<Guess<E>> getGuesses(){
		if(!this.guesses.isEmpty()){
			MyQueue<Guess<E>> myGuesses = new MyQueue<Guess<E>>();
			for (Guess<E> g : this.guesses) {
				myGuesses.enqueue(g);
			}
			return myGuesses;
		}
		return null;
	}

	/* IDE created functions */

	public int getGameNumber() {
		return game;
	}

	public boolean isWon() {
		return won;
	}
	
	public E getPuzzle() {
		return puzzle;
	}

	/* IDE created functions */
	
	/**
	 * Returns a string with the play history of the saved game
	 */
	public String toString(){		
		StringBuilder sb = new StringBuilder();
		String s = "Game # "+game + " Puzzle: " + puzzle + " Won: "+ won + "\r\n";
		sb.append(s);
		
		for(Guess<E> g : this.guesses){
			sb.append(g.toString()+"\r\n");
		}
		
		return sb.toString();
	}
	
	/***************************************************************************
	 ****************************** Member Variables*****************************
	 **************************************************************************/

	// Puzzle that was issued for the game
	private E puzzle;
	// Save for mage number
	private int game;
	// Was the game won
	private boolean won;
	// History of the guesses made by the player
	private MyQueue<Guess<E>> guesses;
}

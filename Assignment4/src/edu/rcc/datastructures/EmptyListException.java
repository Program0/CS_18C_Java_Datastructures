package edu.rcc.datastructures;

@SuppressWarnings("serial")
public class EmptyListException extends Exception {

	/*constructor*/
	public EmptyListException(Integer inputNumber){
		this.inputNumber = inputNumber;
	}
	/*return original number generating the exception*/
	public Integer getInputNumber(){
		return this.inputNumber;
	}
	/*member variable*/
	private Integer inputNumber;
}

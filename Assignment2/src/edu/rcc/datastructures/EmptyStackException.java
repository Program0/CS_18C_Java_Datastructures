package edu.rcc.datastructures;

@SuppressWarnings("serial")
public class EmptyStackException extends Exception {
	private Integer inputNumber;
	public EmptyStackException(Integer inputNumber){
		this.inputNumber = inputNumber;
	}
	public Integer getInputNumber(){
		return inputNumber;
	}
}

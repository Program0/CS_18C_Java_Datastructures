package edu.rcc.datastructures;

@SuppressWarnings("serial")
public class MyEmptyStackException extends Exception {
	private Integer inputNumber;
	public MyEmptyStackException(Integer inputNumber){
		this.inputNumber = inputNumber;
	}
	public Integer getInputNumber(){
		return inputNumber;
	}
}

package edu.rcc.datastructures;

import java.util.Comparator;

public class Stuff implements Comparator<Stuff>, Comparable<Stuff> {
	private Integer numericValue;
	private String stringValue;
	private boolean booleanValue;

	public Stuff() {
		numericValue = 0;
		stringValue = "a";
		booleanValue = true;
	}

	public Stuff(int thing1, String thing2, boolean thing3) {
		this.numericValue = thing1;
		this.stringValue = thing2;
		this.booleanValue = thing3;
	}

	public Integer getNumerical() {
		return numericValue;
	}

	public String getStringValue() {
		return stringValue;
	}

	public boolean getBooleanValue() {
		return booleanValue;
	}

	public String toString() {
		String contents;
		contents = numericValue + " " + stringValue + " " + booleanValue;
		return contents;
	}

	//Overriding the compare method
	@Override
	public int compare(Stuff o1, Stuff o2) {
		//
		Stuff object1 = (Stuff) o1;
		Stuff object2 = (Stuff) o2;
		// If the contents are exactly the same return 0
		if (object2.getStringValue().equals((object2).getStringValue())
				&& object2.getNumerical() == object1.getNumerical()
				&& object1.getBooleanValue() && object2.getBooleanValue()) {
			return 0;

		}
		// If not check the numerical, string, and boolean values in that order
		else if (object1.getNumerical() == object2.getNumerical()) {
			// Check the string value equality
			if (object1.getStringValue().equals(object2.getStringValue())) {
				// check the boolean value equality
				return (object1.getBooleanValue() ? 1 : -1);
			}

			return (object1.getStringValue().compareTo(object2.getStringValue()));
		}
		//Return which ever numerical value is greater
		return ((object1.getNumerical() < object2.getNumerical() ? -1 : 1));
	}

	
	//Overriding the compareTo method
	@Override
	public int compareTo(Stuff stuff1){
		return (this.numericValue.compareTo(stuff1.getNumerical()));
	}
}

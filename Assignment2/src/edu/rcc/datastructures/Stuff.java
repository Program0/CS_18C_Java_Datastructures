package edu.rcc.datastructures;

public class Stuff {
	private int thing1;
	private String thing2;
	private boolean thing3;

	public Stuff() {
		thing1 = 0;
		thing2 = "a";
		thing3 = true;
	}

	public Stuff(int thing1, String thing2, boolean thing3) {
		this.thing1 = thing1;
		this.thing2 = thing2;
		this.thing3 = thing3;
	}

	public int getStuffThing1() {
		return thing1;
	}

	public String getStuffThing2() {
		return thing2;
	}

	public boolean getStuffThing3() {
		return thing3;
	}

	public String toString() {
		String contents;
		contents = thing1 + " " + thing2 + " " + thing3;
		return contents;
	}

}

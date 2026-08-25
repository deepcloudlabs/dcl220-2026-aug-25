package com.example.exercises;

abstract class Base extends Object {
	Base() {
		super();
		System.out.print(this.value() + " "); // Derived::value()
	}

	abstract int value();
}

// Concrete Class
class Derived extends Base {
	private int x = 7;

	@Override
	int value() {
		return x; 
	}

	Derived() {
		super();
		// x = 7
		System.out.print(this.value()); // Derived::value()
	}
}

public class Question2 {

	public static void main(String[] args) {
		new Derived();

	}

}

// A) 0
// B) 7
// C) Throws exception!
// D) 0 7

package com.example.exercises;

class A {
	// overloading
	String f(Number n) {
		return "A:Number";
	}

	String f(Integer n) {
		return "A:Integer";
	}
}

class B extends A {
	// overloading
	@Override
	String f(Number n) {
		return "B:Number";
	}

	String f(Double n) {
		return "B:Double";
	}
}

public class Question1 {

	public static void main(String[] args) {
		A x = new B(); // Object Header -> VT(B) -> B::f(Number), A::f(Integer n), ?
		System.out.println(x.f(Integer.valueOf(42))); // A::f(Integer)
		System.out.println(x.f(Double.valueOf(3.1415))); // B::f(Number) (overriding) 
	}

}

// A) B::Number
// B) A::Number
// C) 

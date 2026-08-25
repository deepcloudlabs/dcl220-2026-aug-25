package com.example.exercises;

interface Service {
	static String name() {
		// Error: this.toString();
		return "Service";
	}

	default String execute() {
		// this.toString();
		return name(); // does not use  VT
	}
}

class Implementation implements Service {
    static String name() {
    	// Error: this.toString();
        return "Implementation";
    }
}

public class Question9 {

	public static void main(String[] args) {
		System.out.println(Service.name());
		System.out.println(Implementation.name());
		Service s = new Implementation();
	
		System.out.println(s.execute());
	}

}

// A) Implementation
// B) Service
// C) ClassCastException
// D) RuntimeException
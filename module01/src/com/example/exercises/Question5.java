package com.example.exercises;

class AA {
    private String hook() {
        return "A";
    }
    String execute() {
        return this.hook();
    }
}

class BB extends AA {
    String hook() {
        return "B";
    }
}

public class Question5 {

	public static void main(String[] args) {
		AA x = new BB();
		System.out.println(x.execute()); // A
	}

}

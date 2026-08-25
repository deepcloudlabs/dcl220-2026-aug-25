package com.example.exercises;

interface AAA {
    default Number value() {
        return 1;
    }
}

interface BBB {
    default Integer value() {
        return 2;
    }
}

class C implements AAA, BBB {
    @Override
    public Integer value() {
        return BBB.super.value();
    }

}

public class Question6 {

	public static void main(String[] args) {
		AAA a = new C();
		BBB b = (BBB) a;
		System.out.println(a.value()+ ":" + b.value());
	}

}

// A) 1:2
// B) 1:1
// C) 2:1
// D) 2:2

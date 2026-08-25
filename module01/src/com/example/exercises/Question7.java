package com.example.exercises;

interface K {}
interface L {}
interface M {}
interface N extends K,L,M {}
class F implements K,L,M {}

sealed class P permits Q{}
// final, sealed,non-sealed
sealed class Q extends P permits R {}
final class R extends Q{}

public class Question7 {

	public static void main(String[] args) {
		// P p = (P) new R();
		// K k = (K) new R();
	}

}


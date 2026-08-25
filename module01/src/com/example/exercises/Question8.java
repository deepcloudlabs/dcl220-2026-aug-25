package com.example.exercises;

import java.util.List;

sealed class Employee permits Secretary, Engineer{}
final class Secretary extends Employee {}
sealed class Engineer extends Employee permits Developer{}
final class Developer extends Engineer {}

public class Question8 {
	public static void addEngineers(List<? extends Engineer> target) {}
	public static void main(String[] args) {
		addEngineers(List.of(new Engineer(),new Developer()));
		// addEngineers(List.of(new Engineer(),new Secretary()));

	}

}

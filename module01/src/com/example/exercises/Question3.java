package com.example.exercises;

import java.io.Serializable;

abstract interface Identity {
	public default String id() {
		return "default";
	}
}

interface Persistence extends Identity,Serializable {
	String id(); // overriding -> base:default -> derived:abstract
//	public default String id() {
//		return "persistence-default";
//	}
	
}

@SuppressWarnings("serial")
class Entity implements Persistence {

	@Override
	public String id() {
		return "entity-default";
	}

}

public class Question3 {
	public static void main(String[] args) {
			Identity entity = new Entity();
			System.out.println(entity.id());
	}
}

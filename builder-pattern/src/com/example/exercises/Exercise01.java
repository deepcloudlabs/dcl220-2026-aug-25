package com.example.exercises;

public class Exercise01 {

	public static void main(String[] args) {
        User user = new User.Builder("binnur")
                .email("binnur@example.com")
                .age(45)
                .phone("+90 555 123 4567")
                .address("Istanbul")
                .build();

        System.out.println(user);
	}

}

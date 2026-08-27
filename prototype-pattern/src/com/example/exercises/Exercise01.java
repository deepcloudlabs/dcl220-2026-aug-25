package com.example.exercises;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Exercise01 {

	public static void main(String[] args) throws NoSuchAlgorithmException, CloneNotSupportedException {
		var commonHeader = "header".getBytes();
		var partA = "part a".getBytes();
		var partB = "part b".getBytes();
		MessageDigest base = MessageDigest.getInstance("SHA-256");
		base.update(commonHeader);
		MessageDigest a = (MessageDigest) base.clone();
		a.update(partA); // digest of header + partA
		MessageDigest b = (MessageDigest) base.clone();
		b.update(partB); // digest of header + partB, prefix hashed only once
	}

}

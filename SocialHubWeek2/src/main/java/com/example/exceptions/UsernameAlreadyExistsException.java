package com.example.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public UsernameAlreadyExistsException() {
		super("A username that already exists was created");
	}
	
}

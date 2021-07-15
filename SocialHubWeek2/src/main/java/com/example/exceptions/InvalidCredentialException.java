package com.example.exceptions;

public class InvalidCredentialException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public InvalidCredentialException() {
		super("User Provided Invalid credentials");
	}
}

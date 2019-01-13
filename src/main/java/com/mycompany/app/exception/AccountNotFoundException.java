package com.mycompany.app.exception;

public class AccountNotFoundException extends Exception {
	public AccountNotFoundException(String message) {
		super(message);
	}
}

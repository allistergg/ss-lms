package com.smoothstack.borrower.exceptions;

public class InvalidCardNumberException extends Exception {
	public InvalidCardNumberException() {
		super();
	}

	public InvalidCardNumberException(String message) {
		super(message);
	}

	public InvalidCardNumberException(String message, Throwable exception) {
		super(message, exception);
	}

	public InvalidCardNumberException(Throwable exception) {
		super(exception);
	}

}

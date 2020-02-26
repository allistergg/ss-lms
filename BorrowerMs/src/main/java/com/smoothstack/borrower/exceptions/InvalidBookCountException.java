package com.smoothstack.borrower.exceptions;

public class InvalidBookCountException extends Exception {

	private static final long serialVersionUID = -7504286292741577973L;

	public InvalidBookCountException() {
		super();
	}

	public InvalidBookCountException(String message) {
		super(message);
	}

	public InvalidBookCountException(String message, Throwable exception) {
		super(message, exception);
	}

	public InvalidBookCountException(Throwable exception) {
		super(exception);
	}

}

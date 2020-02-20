package com.smoothstack.borrower.exceptions;

public class InvalidBookIdException extends Exception {

	
		
		public InvalidBookIdException() {
			super();
		}

		public InvalidBookIdException(String message) {
			super(message);
		}

		public InvalidBookIdException(String message, Throwable exception) {
			super(message, exception);
		}

		public InvalidBookIdException(Throwable exception) {
			super(exception);
		}

}

package com.smoothstack.borrower.exceptions;

public class InvalidBranchIdException extends Exception {
	
		public InvalidBranchIdException() {
			super();
		}

		public InvalidBranchIdException(String message) {
			super(message);
		}

		public InvalidBranchIdException(String message, Throwable exception) {
			super(message, exception);
		}

		public InvalidBranchIdException(Throwable exception) {
			super(exception);
		}

}

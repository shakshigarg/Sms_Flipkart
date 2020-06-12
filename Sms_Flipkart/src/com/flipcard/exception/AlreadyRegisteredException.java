package com.flipcard.exception;

public class AlreadyRegisteredException extends Exception {
		private String message;
		public AlreadyRegisteredException(String message)
		{
			this.message=message;
		} 
		public String getMessage() {
			return message;
		}
	}


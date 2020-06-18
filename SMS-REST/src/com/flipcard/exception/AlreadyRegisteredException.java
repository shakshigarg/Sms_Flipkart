package com.flipcard.exception;

/*
 * The Exception is thrown when the student is already registered for course
 */
public class AlreadyRegisteredException extends Exception {
		private String message;
		public AlreadyRegisteredException(String message)
		{
			this.message=message;
		} 
		
		// Give message
		public String getMessage() {
			return message;
		}
	}


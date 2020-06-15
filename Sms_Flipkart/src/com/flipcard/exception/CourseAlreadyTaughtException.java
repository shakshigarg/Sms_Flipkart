package com.flipcard.exception;

/*
 * Exception thrown when course is already taught by some other professor
 */
public class CourseAlreadyTaughtException extends Exception {
	private String message;
	public CourseAlreadyTaughtException(String message)
	{
		this.message=message;
	} 
	
	// Give message
	public String getMessage() {
		return message;
	}
}

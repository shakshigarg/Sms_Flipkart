package com.flipcard.exception;

/*
 * Exception thrown when the Course entered by Student or professor as choice is invalid
 */
public class InvalidCourseException extends Exception {
	private String message;
	public InvalidCourseException(String message)
	{
		this.message=message;
	} 
	public String getMessage() {
		return message;
	}
}

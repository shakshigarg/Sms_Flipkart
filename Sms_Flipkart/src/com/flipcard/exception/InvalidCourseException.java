package com.flipcard.exception;

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

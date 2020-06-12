package com.flipcard.exception;

public class NotRegisteredCourseException extends Exception {
	private String message;
	public NotRegisteredCourseException(String message)
	{
		this.message=message;
	} 
	public String getMessage() {
		return message;
	}
}

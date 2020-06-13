package com.flipcard.exception;

public class CourseAlreadyTaughtException extends Exception {
	private String message;
	public CourseAlreadyTaughtException(String message)
	{
		this.message=message;
	} 
	public String getMessage() {
		return message;
	}
}

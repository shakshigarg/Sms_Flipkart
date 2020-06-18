package com.flipcard.exception;


/*
 * If student is not registered for the course then excepption is thrown
 */
public class NotRegisteredCourseException extends Exception {
	private String message;
	public NotRegisteredCourseException(String message)
	{
		this.message=message;
	} 
	
	//Give message
	public String getMessage() {
		return message;
	}
}

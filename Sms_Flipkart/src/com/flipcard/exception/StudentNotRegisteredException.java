package com.flipcard.exception;


/*
 * Give exception if student is not registered for course and he want to drop the course
 */
public class StudentNotRegisteredException extends Exception {
	private String message;
	public StudentNotRegisteredException(String message)
	{
		this.message=message;
	} 
	public String getMessage() {
		return message;
	}
}

package com.flipcard.exception;

/*
 * The exception is thrown when Course already exist and admin try to create a course with same name
 */
public class CourseAlreadyExist extends Exception {
	private String message;
	public CourseAlreadyExist(String message)
	{
		this.message=message;
	} 
	
	// Give message
	public String getMessage() {
		return message;
	}
}

package com.flipcard.exception;

/*
 * Exception thrown if the course that admin want to update does not exist
 */
public class CourseNotExist extends Exception{
	private String message;
	public CourseNotExist(String message)
	{
		this.message=message;
	} 
	
	// Give message
	public String getMessage() {
		return message;
	}
}

package com.flipcard.exception;

public class CourseAlreadyExist extends Exception {
	private String message;
	public CourseAlreadyExist(String message)
	{
		this.message=message;
	} 
	public String getMessage() {
		return message;
	}
}

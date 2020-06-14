package com.flipcard.exception;

public class CourseNotExist extends Exception{
	private String message;
	public CourseNotExist(String message)
	{
		this.message=message;
	} 
	public String getMessage() {
		return message;
	}
}

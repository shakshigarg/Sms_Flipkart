package com.flipcard.exception;

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

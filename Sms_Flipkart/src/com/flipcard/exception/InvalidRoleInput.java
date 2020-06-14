package com.flipcard.exception;

public class InvalidRoleInput extends Exception {
	private String message;
	public InvalidRoleInput(String message)
	{
		this.message=message;
	} 
	public String getMessage() {
		return message;
	}
}

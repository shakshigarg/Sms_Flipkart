package com.flipcard.exception;

public class UserAlreadyExist extends Exception {
	private String message;
	public UserAlreadyExist(String message)
	{
		this.message=message;
	} 
	public String getMessage() {
		return message;
	}
}

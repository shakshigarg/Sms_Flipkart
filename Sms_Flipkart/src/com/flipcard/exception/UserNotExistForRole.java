package com.flipcard.exception;

public class UserNotExistForRole extends Exception {
	private String message;
	public UserNotExistForRole(String message)
	{
		this.message=message;
	} 
	public String getMessage() {
		return message;
	}
}

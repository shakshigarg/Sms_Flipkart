package com.flipcard.exception;


/*
 * If the role entered by Administrator while deleting is invalid then through exception
 */
public class InvalidRoleInput extends Exception {
	private String message;
	public InvalidRoleInput(String message)
	{
		this.message=message;
	} 
	
	// Give message
	public String getMessage() {
		return message;
	}
}

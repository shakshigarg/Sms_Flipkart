package com.flipcard.exception;


/*
 * Gives exception if user i.e. Student/Professor does not exist and admin want to delete the student/professor
 */
public class UserNotExistForRole extends Exception {
	private String message;
	public UserNotExistForRole(String message)
	{
		this.message=message;
	} 
	
	//Give message
	public String getMessage() {
		return message;
	}
}

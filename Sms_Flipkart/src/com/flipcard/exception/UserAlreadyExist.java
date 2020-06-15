package com.flipcard.exception;

/*
 * Give exception if student/professor/Administrator already exist and administrator try to create other
 */
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

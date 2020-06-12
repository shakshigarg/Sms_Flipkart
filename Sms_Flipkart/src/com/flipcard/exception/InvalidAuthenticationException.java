/**
 * 
 */
package com.flipcard.exception;

/**
 * @author Lenovo
 *
 */
public class InvalidAuthenticationException extends Exception {
	private String message;
	public InvalidAuthenticationException(String message)
	{
		this.message=message;
	} 
	public String getMessage() {
		return message;
	}
}

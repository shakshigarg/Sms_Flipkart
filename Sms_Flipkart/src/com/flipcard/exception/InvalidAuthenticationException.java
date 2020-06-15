/**
 * 
 */
package com.flipcard.exception;

/**
 * If wrong Credentials while login the this exception is thrown
 *
 */
public class InvalidAuthenticationException extends Exception {
	private String message;
	public InvalidAuthenticationException(String message)
	{
		this.message=message;
	} 
	
	// Give message
	public String getMessage() {
		return message;
	}
}

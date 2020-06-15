package com.flipcard.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Logger;

import com.flipcard.DAO.AuthCredentials;
import com.flipcard.exception.InvalidAuthenticationException;

/*
 * This service is used for login and logout
 * Checks if authentication credentials are valid or not
 */
public class AuthenticationService implements AuthenticationServiceInterface{
	
	private static AuthCredentials authDao=new AuthCredentials();
	
	
	/*
	 * Checks if credentials are valid or not.
	 */
	public static String checkIdentity(String username,String password) throws InvalidAuthenticationException {
		String role= authDao.checkIdentity(username, password);
		if(role==null) 
		{
			// If credentials are not valid
			throw new InvalidAuthenticationException("Invalid username or password");
		}
		else {
			return role;
		}
	
	}

	public static void logout() {
		// logout the user
		authDao.logout();
		
	}
	
	
}

package com.flipcard.service;

import java.util.logging.Logger;

import com.flipcard.DAO.AuthCredentials;
import com.flipcard.exception.InvalidAuthenticationException;

public class AuthenticationService implements AuthenticationServiceInterface{
	private static AuthCredentials authDao=new AuthCredentials();
	
	public static String checkIdentity(String username,String password) throws InvalidAuthenticationException {
		String role= authDao.checkIdentity(username, password);
		if(role==null) 
		{
			throw new InvalidAuthenticationException("Invalid username or password");
		}
		else {
			return role;
		}
	}
	
}

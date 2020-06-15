package com.flipcard.DAO;

/*
 * AuthCredentials class implements authcredentials interface
 */
public interface AuthCredentialsInterface {
	
	// Check if username and password is valid or not
	public String checkIdentity(String username,String password);
	
	// Logout
	public void logout();
}

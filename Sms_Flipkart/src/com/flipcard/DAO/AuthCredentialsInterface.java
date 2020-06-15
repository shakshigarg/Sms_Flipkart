package com.flipcard.DAO;

public interface AuthCredentialsInterface {
	public String checkIdentity(String username,String password);
	public void logout();
}

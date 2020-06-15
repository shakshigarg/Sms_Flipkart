package com.flipcard.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.log4j.Logger;

import com.flipcard.constants.SqlQueries;
import com.flipcard.utils.CloseConnectionUtils;
import com.flipcard.utils.DBUtils;

public class AuthCredentials implements AuthCredentialsInterface {
	
	// Create the logger object to log the messages
	private static Logger logger=Logger.getLogger(AuthCredentials.class);
	
	// Initialize the connection
	private Connection conn=null;
	private PreparedStatement stmt = null;
	
	
	/*
	 * Check if the credentials are valid or not
	 */
	public String checkIdentity(String username,String password) {
		// Establish connection 
		conn = DBUtils.getConnection();
		
		try {
			
			// Run the query that checks the credentials
			stmt = conn.prepareStatement(SqlQueries.CHECK_IDENTITY);
			 
			stmt.setString(1,username);
			stmt.setString(2,password);
			
			// Get the ResultSet from query
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				return rs.getString("role");
			}
			
			return null;
			}
			
		catch(Exception e){
			
			// Give error if the sql query throw an error
			logger.error("Error occured "+e.getMessage());
		}
		return null;
		
	}
	
	/*
	 * Logout User and close connection
	 */
	public void logout() {
		
		// Close the connection and logout 
		CloseConnectionUtils.closeConnection(DBUtils.getConnection());
		
	}
}

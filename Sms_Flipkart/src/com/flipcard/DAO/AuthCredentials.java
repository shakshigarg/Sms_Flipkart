package com.flipcard.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipcard.constants.SqlQueries;
import com.flipcard.utils.CloseConnectionUtils;
import com.flipcard.utils.DBUtils;

public class AuthCredentials implements AuthCredentialsInterface {
	
	private static Logger logger=Logger.getLogger(AuthCredentials.class);
	private Connection conn=null;
	private PreparedStatement stmt = null;
	
	public String checkIdentity(String username,String password) {
		// Establish connection 
		conn = DBUtils.getConnection();
		
		try {
			stmt = conn.prepareStatement(SqlQueries.CHECK_IDENTITY);
			 
			stmt.setString(1,username);
			stmt.setString(2,password);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				return rs.getString("role");
			}
			
			return null;
			}
			
		catch(Exception e){
			logger.error("Error occured "+e.getMessage());
		}
		return null;
		
	}

	public void logout() {
		CloseConnectionUtils.closeConnection(DBUtils.getConnection());
		
	}
}

package com.flipcard.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import org.apache.log4j.Logger;

import com.flipcard.constants.SqlQueries;
import com.flipcard.utils.DBUtils;

public class CourseUpdation implements CourseUpdationInterface {
	private static Logger logger=Logger.getLogger(AuthCredentials.class);
	private Connection conn=null;
	private PreparedStatement stmt = null;
	
	public boolean verifyCourse(String courseName) {
		
		// Establish connection 
				conn = DBUtils.getConnection();
				
				try {
					stmt = conn.prepareStatement(SqlQueries.VERIFY_COURSE);
					 
					stmt.setString(1,courseName);
					ResultSet rs = stmt.executeQuery();
					
					while(rs.next()) {
						return true;
					}
					
					return false;
					}
					
				catch(Exception e){
					logger.error("Error occured "+e.getMessage());
				}
			
				
		
		return false;
		
	}

	@Override
	public boolean addCourse(String username,String courseName) {
		conn = DBUtils.getConnection();
		
		try {
			stmt = conn.prepareStatement(SqlQueries.ADD_NEW_COURSE);
			 
			stmt.setString(1,username);
			stmt.setString(2, courseName);
			int rows = stmt.executeUpdate();
			
			return true;
		}
		catch(SQLIntegrityConstraintViolationException error) {
			return false;
		}
		catch(Exception e){
			logger.error("Error occured "+e.getMessage());
			
		}
		return false;
	}
	
}

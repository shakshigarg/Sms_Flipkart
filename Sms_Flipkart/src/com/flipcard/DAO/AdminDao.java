package com.flipcard.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import org.apache.log4j.Logger;

import com.flipcard.constants.SqlQueries;
import com.flipcard.model.Admin;
import com.flipcard.model.Professor;
import com.flipcard.model.Student;
import com.flipcard.utils.DBUtils;
import com.flipcard.utils.DateTimeDay;

public class AdminDao implements AdminDaoInterface {
	private Connection conn=null;
	private PreparedStatement stmt = null;
	private static Logger logger=Logger.getLogger(AuthCredentials.class);
	@Override
	public void createStudent(Student s,String password) {
		conn = DBUtils.getConnection();		

		try {
			
			stmt = conn.prepareStatement(SqlQueries.ADD_USER_CREDENTIALS);
			stmt.setString(1,s.getUserName());
			stmt.setString(2,password);
			stmt.setString(3,"student");
			stmt.executeUpdate();
			
			stmt = conn.prepareStatement(SqlQueries.ADD_STUDENT_INFO);
			stmt.setString(1,s.getUserName());
			stmt.setInt(2,s.getNumberOfCourses());
			stmt.setString(3,s.getPhoneNumber());
			stmt.setString(4,s.getAddress());
			stmt.setInt(5,s.getScholarshipId());
			stmt.setString(6,s.getGender());
			stmt.executeUpdate();
			
		}
		catch(Exception e){
			logger.error("Error occured "+e.getMessage());

		}
		
		
	}
	@Override
	public void createProfessor(Professor p, String password) {
		conn = DBUtils.getConnection();		

		try {
			
			stmt = conn.prepareStatement(SqlQueries.ADD_USER_CREDENTIALS);
			stmt.setString(1,p.getUserName());
			stmt.setString(2,password);
			stmt.setString(3,"professor");
			stmt.executeUpdate();
			
			stmt = conn.prepareStatement(SqlQueries.ADD_PROFESSOR_INFO);
			stmt.setString(1,p.getUserName());
			stmt.setString(2,p.getPhoneNumber());
			stmt.setString(3,p.getAddress());
			stmt.setInt(4,p.getNumberOfCourses());
			stmt.setString(5,p.getGender());
			stmt.executeUpdate();
			
		}
		catch(Exception e){
			logger.error("Error occured "+e.getMessage());

		}
		
		
	}
	@Override
	public void createAdmin(Admin a, String password) {
		conn = DBUtils.getConnection();		

		try {
			
			stmt = conn.prepareStatement(SqlQueries.ADD_USER_CREDENTIALS);
			stmt.setString(1,a.getUserName());
			stmt.setString(2,password);
			stmt.setString(3,"admin");
			stmt.executeUpdate();
			
			stmt = conn.prepareStatement(SqlQueries.ADD_ADMIN_INFO);
			stmt.setString(1,a.getUserName());
			stmt.setString(2,a.getPhoneNumber());
			stmt.setString(3,a.getAddress());
			stmt.setString(4,a.getGender());
			stmt.executeUpdate();
			
		}
		catch(Exception e){
			logger.error("Error occured "+e.getMessage());

		}
		
		
	}
	@Override
	public boolean checkUsername(String userName) {
		conn = DBUtils.getConnection();		

		try {
			
			stmt = conn.prepareStatement(SqlQueries.CHECK_USERNAME);
			stmt.setString(1,userName);
			ResultSet rs=stmt.executeQuery();
			if(rs.next())
			return true;
			else {
				return false;
			}
		}
		catch(Exception e){
			logger.error("Error occured "+e.getMessage());

		}
		
		return false;
	}

}

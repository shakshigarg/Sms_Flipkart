package com.flipcard.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipcard.constants.SqlQueries;
import com.flipcard.model.Admin;
import com.flipcard.model.Course;
import com.flipcard.model.Professor;
import com.flipcard.model.Student;
import com.flipcard.utils.DBUtils;

/*
 * AdminDao class implements AdminDaoInterface.
 * The class is accessed by AdminService class to perform database operations.
 */
public class AdminDao implements AdminDaoInterface {
	
	// Connection nitialised
	private Connection conn=null;
	private PreparedStatement stmt = null;
	
	// logger to log the information
	private static Logger logger=Logger.getLogger(AuthCredentials.class);
	
	
	@Override
	// Method to create a student 
	public void createStudent(Student s,String password) {
		
		// Obtain a connection
		conn = DBUtils.getConnection();		

		try {
			
			// Adds student credentials in AuthCredentials table
			stmt = conn.prepareStatement(SqlQueries.ADD_USER_CREDENTIALS);
			stmt.setString(1,s.getUserName());
			stmt.setString(2,password);
			stmt.setInt(3,2);
			stmt.executeUpdate();
			
			
			//Adds student information in student table
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
			// if the SQL queries give any error then it give the error message.
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
		}


	}
	
	
	@Override
	
	// This method create a new professor
	public void createProfessor(Professor p, String password) {
		
		// Get the database connection
		conn = DBUtils.getConnection();		

		try {
			
			// Add professor credentials in AuthCredentials table
			stmt = conn.prepareStatement(SqlQueries.ADD_USER_CREDENTIALS);
			stmt.setString(1,p.getUserName());
			stmt.setString(2,password);
			stmt.setInt(3,3);
			stmt.executeUpdate();

			
			// Add professor information in professor table
			stmt = conn.prepareStatement(SqlQueries.ADD_PROFESSOR_INFO);
			stmt.setString(1,p.getUserName());
			stmt.setString(2,p.getPhoneNumber());
			stmt.setString(3,p.getAddress());
			stmt.setInt(4,p.getNumberOfCourses());
			stmt.setString(5,p.getGender());
			stmt.executeUpdate();

		}
		catch(Exception e){
			// if the SQL queries give any error then it give the error message.
			logger.error("Error occured "+e.getMessage());

		}


	}
	
	
	@Override
	
	// The method create the Admin
	public void createAdmin(Admin a, String password) {
		conn = DBUtils.getConnection();		

		try {
			
			// Add Admin credentials to AuthCredentials table
			stmt = conn.prepareStatement(SqlQueries.ADD_USER_CREDENTIALS);
			stmt.setString(1,a.getUserName());
			stmt.setString(2,password);
			stmt.setInt(3,1);
			stmt.executeUpdate();
			
			// Add Admin information in Admin table
			stmt = conn.prepareStatement(SqlQueries.ADD_ADMIN_INFO);
			stmt.setString(1,a.getUserName());
			stmt.setString(2,a.getPhoneNumber());
			stmt.setString(3,a.getAddress());
			stmt.setString(4,a.getGender());
			stmt.executeUpdate();

		}
		catch(Exception e){
			// if the SQL queries give any error then it give the error message.
			logger.error("Error occured "+e.getMessage());

		}


	}
	
	
	@Override
	
	// Checks the UserName, return true if UserName, already exists else return false
	public boolean checkUsername(String userName) {
		
		// Get connection
		conn = DBUtils.getConnection();		

		try {
			
			// Checks the Username
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
			// if the SQL queries give any error then it give the error message.
			logger.error("Error occured "+e.getMessage());

		}
		return false;
	}
	
	
	@Override
	
	// Fetch all the users i.e. student professor and Admin
	public List<String> getUsersWithRole(String role) {
		// Establish connection 
		conn = DBUtils.getConnection();

		try {
			
			// Get users with role
			stmt = conn.prepareStatement(SqlQueries.GET_USERS_WITH_ROLE);
			int roleId=0;
			if(role.equalsIgnoreCase("student")) {
				roleId=2;
			}
			if(role.equalsIgnoreCase("professor")) {
				roleId=3;
			}
			stmt.setInt(1, roleId);
			ResultSet rs = stmt.executeQuery();

			List<String> userNames = new ArrayList<String>();

			// Extract data from result set
			while(rs.next()){
				
				//Retrieve by column name
				userNames.add(rs.getString("userName"));
			}
			return userNames;

		}
		catch(Exception e){
			// if the SQL queries give any error then it give the error message.
			logger.error("Error occured "+e.getMessage());

		}
		return null;
	}
	
	
	@Override
	
	// Delete the user with role student,professor
	public boolean deleteUser(String username, String role) {
		conn = DBUtils.getConnection();		

		try {
			int roleId=0;
			if(role.equalsIgnoreCase("student")) {
				roleId=2;
			}
			if(role.equalsIgnoreCase("professor")) {
				roleId=3;
			}
			// Delete student or professor
			stmt = conn.prepareStatement(SqlQueries.DELETE_USER_WITH_ROLE);
			stmt.setString(1,username);
			stmt.setInt(2,roleId);
			int rows=stmt.executeUpdate();
			if(rows==0) {
				return false;
			}
			
			// If role is student the delete it from student table as well
			if(roleId==2)
			{
				stmt = conn.prepareStatement(SqlQueries.DELETE_STUDENT_INFO);
				stmt.setString(1,username);
				stmt.executeUpdate();
				
				// Delete the student from student courses table
				stmt = conn.prepareStatement(SqlQueries.DELETE_STUDENT_COURSES);
				stmt.setString(1,username);
				stmt.executeUpdate();
				
				stmt = conn.prepareStatement(SqlQueries.DELETE_STUDENT_GRADES);
				stmt.setString(1,username);
				stmt.executeUpdate();
			}
			else {
				
				// If professor the delete from professor table
				stmt = conn.prepareStatement(SqlQueries.DELETE_PROFESSOR_INFO);
				stmt.setString(1,username);
				stmt.executeUpdate();
				
				// Reset the courses taught by professor by null
				stmt = conn.prepareStatement(SqlQueries.RESET_PROFESSOR_COURSES);
				stmt.setString(1,username);
				stmt.executeUpdate();
			}
			return true;

		}
		catch(Exception e){
			// if the SQL queries give any error then it give the error message.
			logger.error("Error occured "+e.getMessage());

		}
		return false;
	}
	
	
	@Override
	
	// Delete the self account of Administrator
	public void deleteSelfAccount(String username) {
		conn = DBUtils.getConnection();		
		try {
			
			// Delete Administrator from AuthCredential table
			stmt = conn.prepareStatement(SqlQueries.DELETE_USER_WITH_ROLE);
			stmt.setString(1,username);
			stmt.setInt(2,1);
			stmt.executeUpdate();
			
			// Delete Administrator information from Administrator table
			stmt = conn.prepareStatement(SqlQueries.DELETE_ADMIN_INFO);
			stmt.setString(1,username);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// if the SQL queries give any error then it give the error message.
			logger.error("Error occured "+e.getMessage());
		}
	
	}
	
	
	@Override
	
	// Check if the course name is valid or not
	public boolean checkCourseName(String courseName) {
		conn = DBUtils.getConnection();		

		try {

			stmt = conn.prepareStatement(SqlQueries.CHECK_COURSENAME);
			stmt.setString(1,courseName);
			ResultSet rs=stmt.executeQuery();
			
			// Return true if course already exist else return false
			if(rs.next())
				return true;
			else {
				return false;
			}
		}
		catch(Exception e){
			// if the SQL queries give any error then it give the error message.
			logger.error("Error occured "+e.getMessage());

		}

		return false;
	}
	
	// Create new course
	@Override
	public void createCourse(Course c) {
		
		// Establish the connection
		conn = DBUtils.getConnection();		

		try {
			
			// run query to create course 
			stmt = conn.prepareStatement(SqlQueries.CREATE_COURSE);
			stmt.setString(1,c.getCourseName());
			stmt.setString(2,c.getProfessorName());
			stmt.setString(3,c.getSubject());
			stmt.setInt(4,c.getFee());
			stmt.executeUpdate();

		}
		catch(Exception e){
			// if the SQL queries give any error then it give the error message.
			logger.error("Error occured "+e.getMessage());

		}
		
	}
	
	
	@Override
	
	// Update an exusting course
	public void updateCourse(Course c,String coursename) {
		
		// Establish the connection
		conn = DBUtils.getConnection();		

		try {
			
			//Run query to update course
			stmt = conn.prepareStatement(SqlQueries.UPDATE_COURSE);
			stmt.setString(1,c.getCourseName());
			stmt.setString(2,c.getSubject());
			stmt.setInt(3,c.getFee());
			stmt.setString(4,coursename);
			stmt.executeUpdate();
		}
		catch(Exception e){
			// if the SQL queries give any error then it give the error message.
			logger.error("Error occured "+e.getMessage());
		}
		
	}

}

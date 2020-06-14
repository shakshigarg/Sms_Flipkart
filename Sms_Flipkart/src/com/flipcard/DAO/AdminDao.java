package com.flipcard.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipcard.constants.SqlQueries;
import com.flipcard.model.Admin;
import com.flipcard.model.Course;
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
	@Override
	public List<String> getUsersWithRole(String role) {
		// Establish connection 
		conn = DBUtils.getConnection();

		try {
			stmt = conn.prepareStatement(SqlQueries.GET_USERS_WITH_ROLE);
			stmt.setString(1, role);
			ResultSet rs = stmt.executeQuery();

			List<String> userNames = new ArrayList<String>();

			//STEP 5: Extract data from result set
			while(rs.next()){
				//Retrieve by column name

				userNames.add(rs.getString("userName"));
			}
			return userNames;

		}
		catch(Exception e){
			logger.error("Error occured "+e.getMessage());

		}
		return null;
	}
	@Override
	public boolean deleteUser(String username, String role) {
		conn = DBUtils.getConnection();		

		try {

			stmt = conn.prepareStatement(SqlQueries.DELETE_USER_WITH_ROLE);
			stmt.setString(1,username);
			stmt.setString(2,role);
			int rows=stmt.executeUpdate();
			if(rows==0) {
				return false;
			}
			if(role.contentEquals("Student")||role.contentEquals("student"))
			{
				stmt = conn.prepareStatement(SqlQueries.DELETE_STUDENT_INFO);
				stmt.setString(1,username);
				stmt.executeUpdate();
				stmt = conn.prepareStatement(SqlQueries.DELETE_STUDENT_COURSES);
				stmt.setString(1,username);
				stmt.executeUpdate();
			}
			else {
				stmt = conn.prepareStatement(SqlQueries.DELETE_PROFESSOR_INFO);
				stmt.setString(1,username);
				stmt.executeUpdate();
				stmt = conn.prepareStatement(SqlQueries.RESET_PROFESSOR_COURSES);
				stmt.setString(1,username);
				stmt.executeUpdate();
			}
			return true;

		}
		catch(Exception e){
			logger.error("Error occured "+e.getMessage());

		}
		return false;
	}
	@Override
	public void deleteSelfAccount(String username) {
		conn = DBUtils.getConnection();		
		try {
			stmt = conn.prepareStatement(SqlQueries.DELETE_USER_WITH_ROLE);
			stmt.setString(1,username);
			stmt.setString(2,"admin");
			stmt.executeUpdate();
			stmt = conn.prepareStatement(SqlQueries.DELETE_ADMIN_INFO);
			stmt.setString(1,username);
			stmt.executeUpdate();
		} catch (SQLException e) {
			logger.error("Error occured "+e.getMessage());
		}
	
	}
	@Override
	public boolean checkCourseName(String courseName) {
		conn = DBUtils.getConnection();		

		try {

			stmt = conn.prepareStatement(SqlQueries.CHECK_COURSENAME);
			stmt.setString(1,courseName);
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
	@Override
	public void createCourse(Course c) {
		conn = DBUtils.getConnection();		

		try {
			stmt = conn.prepareStatement(SqlQueries.CREATE_COURSE);
			stmt.setString(1,c.getCourseName());
			stmt.setInt(2,c.getNumberOfStudents());
			stmt.setString(3,c.getProfessorName());
			stmt.setString(4,c.getSubject());
			stmt.setInt(5,c.getFee());
			stmt.executeUpdate();

		}
		catch(Exception e){
			logger.error("Error occured "+e.getMessage());

		}
		
	}
	@Override
	public void updateCourse(Course c,String coursename) {
		conn = DBUtils.getConnection();		

		try {
			stmt = conn.prepareStatement(SqlQueries.UPDATE_COURSE);
			stmt.setString(1,c.getCourseName());
			stmt.setString(2,c.getSubject());
			stmt.setInt(3,c.getFee());
			stmt.setString(4,coursename);
			stmt.executeUpdate();
		}
		catch(Exception e){
			System.out.println("here");
			logger.error("Error occured "+e.getMessage());
		}
		
	}

}

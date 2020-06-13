package com.flipcard.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipcard.constants.SqlQueries;
import com.flipcard.model.Student;
import com.flipcard.utils.DBUtils;
import com.flipcard.utils.DateTimeDay;

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
			stmt.setString(3,DateTimeDay.getDateTime());
			stmt.setString(4,"");
			int rows = stmt.executeUpdate();
			
			return true;
		}
		catch(SQLIntegrityConstraintViolationException error) {
			return false;
		}
		catch(Exception e){
			logger.error("Error occured "+e.getMessage());
			e.printStackTrace();
			
		}
		return false;
	}

	@Override
	public List<String> fetchRegisteredCourses(String username) {
			// Establish connection 
			conn = DBUtils.getConnection();

			try {
				stmt = conn.prepareStatement(SqlQueries.GET_REGISTERED_COURSE_NAMES);
				stmt.setString(1,username);
				
				ResultSet rs = stmt.executeQuery();

				List<String> courseNames = new ArrayList<String>();

				//STEP 5: Extract data from result set
				while(rs.next()){
					//Retrieve by column name
					
					courseNames.add(rs.getString("courseName"));
				}
				return courseNames;
				
			}
			catch(Exception e){
				logger.error("Error occured "+e.getMessage());
				
			}
			return null;
	}

	@Override
	public boolean dropCourse(String username, String courseName) {
		conn = DBUtils.getConnection();	
		
		try {
			stmt = conn.prepareStatement(SqlQueries.DROP_COURSE);
			 
			stmt.setString(1,username);
			stmt.setString(2, courseName);
			int rows = stmt.executeUpdate();
			if(rows==0)
			return false;
			else
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

	@Override
	public String addCourseToTeach(String username, String courseName) {
		conn = DBUtils.getConnection();
		
		try {
			stmt = conn.prepareStatement(SqlQueries.ADD_COURSE_TO_TEACH);
			 
			stmt.setString(1,username);
			stmt.setString(2, courseName);
			stmt.setString(3,"");
			int rows = stmt.executeUpdate();	
			if(rows!=0)
			{
				System.out.println("added actually");
				return "added";
			}
			else {
				return "Course already being taught";
			}
		}
		catch(Exception e){
			logger.error("Error occured "+e.getMessage());
			
		}
		return null;
	}

	@Override
	public List<String> fetchTaughtCoursesName(String username) {
		// Establish connection 
				conn = DBUtils.getConnection();

				try {
					stmt = conn.prepareStatement(SqlQueries.GET_TAUGHT_COURSE_NAMES);
					stmt.setString(1,username);
					ResultSet rs = stmt.executeQuery();

					List<String> courseNames = new ArrayList<String>();

					//STEP 5: Extract data from result set
					while(rs.next()){
						//Retrieve by column name
						
						courseNames.add(rs.getString("courseName"));
					}
					return courseNames;
					
				}
				catch(Exception e){
					logger.error("Error occured "+e.getMessage());
					
				}
				return null;
	}

	@Override
	public List<String> fetchStudentsName(String courseName) {
		// Establish connection 
		conn = DBUtils.getConnection();

		try {
			stmt = conn.prepareStatement(SqlQueries.GET_STUDENTS_NAME);
			stmt.setString(1,courseName);
			ResultSet rs = stmt.executeQuery();

			List<String> studentNames = new ArrayList<String>();

			//STEP 5: Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				
				studentNames.add(rs.getString("username"));
			}
			return studentNames;
			
		}
		catch(Exception e){
			logger.error("Error occured "+e.getMessage());
			
		}
		return null;
	}

	@Override
	public List<Student> getAllStudents() {
		// Establish connection 
				conn = DBUtils.getConnection();

				try {
					stmt = conn.prepareStatement(SqlQueries.GET_ALL_STUDENTS);
					ResultSet rs = stmt.executeQuery();

					List<Student> students = new ArrayList<Student>();

					//STEP 5: Extract data from result set
					while(rs.next()){
						//Retrieve by column name
						Student s=new Student();
						s.setUserName(rs.getString("username"));
						s.setAddress(rs.getString("address"));
						s.setGender(rs.getString("gender"));
						s.setNumberOfCourses(rs.getInt("numberofcourses"));
						s.setPhoneNumber(rs.getString("phonenumber"));
						s.setScholarshipId(rs.getInt("scholarshipid"));
						students.add(s);
					}
					return students;
					
				}
				catch(Exception e){
					logger.error("Error occured "+e.getMessage());
					
				}
				return null;
		
	}
	
}

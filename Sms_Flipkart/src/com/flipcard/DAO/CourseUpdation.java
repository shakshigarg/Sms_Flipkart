package com.flipcard.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipcard.constants.SqlQueries;
import com.flipcard.model.Course;
import com.flipcard.model.Student;
import com.flipcard.utils.DBUtils;
import com.flipcard.utils.DateTimeDay;

public class CourseUpdation implements CourseUpdationInterface {
	private static Logger logger=Logger.getLogger(AuthCredentials.class);
	private Connection conn=null;
	private PreparedStatement stmt = null;
	
	@Override
	/*
	 * Verify if the course exist or not.
	 */
	public boolean verifyCourse(String courseName) {

		// Establish connection 
		conn = DBUtils.getConnection();

		try {

			// Run SQL query 
			stmt = conn.prepareStatement(SqlQueries.VERIFY_COURSE);					 
			stmt.setString(1,courseName);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				return true;
			}
			// Return false if course does not exist
			return false;
		}

		catch(Exception e){
			// Give error message if SQL query generate error
			logger.error("Error occured "+e.getMessage());
		}				

		return false;

	}

	
	@Override
	/*
	 * Student register for the course 
	 * Increase count in number of students field of catalog
	 * Increase the count of number of courses registered
	 */
	public boolean addCourse(String username,String courseName) {
		conn = DBUtils.getConnection();		

		try {

			// Run SQL query to register for new course
			stmt = conn.prepareStatement(SqlQueries.ADD_NEW_COURSE);

			stmt.setString(1,username);
			stmt.setString(2, courseName);
			stmt.setString(3,DateTimeDay.getDateTime());
			stmt.executeUpdate();

			stmt = conn.prepareStatement(SqlQueries.INCREASE_COUNT_OF_COURSES);
			stmt.setString(1, username);
			stmt.executeUpdate();
			
			stmt = conn.prepareStatement(SqlQueries.ADD_TO_GRADES);
			stmt.setString(1,username);
			stmt.setString(2, courseName);
			stmt.setString(3, "");
			stmt.executeUpdate();
			return true;
		}
		catch(SQLIntegrityConstraintViolationException error) {
			// Give message if SQl query give an error
			logger.error("Error occured "+error.getMessage());	
			return false;
		}
		catch(Exception e){
			logger.error("Error occured "+e.getMessage());			
		}
		return false;
	}
	
	@Override
	/*
	 * fetch the courses in which student is registered 
	 */
	public List<String> fetchRegisteredCourses(String username) {
		// Establish connection 
		conn = DBUtils.getConnection();

		try {
			stmt = conn.prepareStatement(SqlQueries.GET_REGISTERED_COURSE_NAMES);
			stmt.setString(1,username);

			ResultSet rs = stmt.executeQuery();

			List<String> courseNames = new ArrayList<String>();

			// Extract data from result set
			while(rs.next()){
				//Retrieve by column name					
				courseNames.add(rs.getString("courseName"));
			}
			return courseNames;

		}
		catch(Exception e){
			// Give error message if SQL query generate error
			logger.error("Error occured "+e.getMessage());				
		}
		return null;
	}
	
	
	@Override
	/*
	 * Student drop the course
	 * Decrease count of students registered in course
	 * Decrease count of number of registered courses of student
	 */
	public boolean dropCourse(String username, String courseName) {
		conn = DBUtils.getConnection();	

		try {
			stmt = conn.prepareStatement(SqlQueries.DROP_COURSE);

			stmt.setString(1,username);
			stmt.setString(2, courseName);
			int rows = stmt.executeUpdate();
			if(rows==0)
				// If student not registered for course
				return false;
			else
			{
				stmt = conn.prepareStatement(SqlQueries.DECREASE_COUNT_OF_COURSES);
				stmt.setString(1, username);
				stmt.executeUpdate();
				
				stmt = conn.prepareStatement(SqlQueries.DROP_FROM_GRADES);
				stmt.setString(1,username);
				stmt.setString(2, courseName);
				stmt.executeUpdate();
				// Return true if drop course is successful
				return true;
			}
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
	/*
	 * Professor add course to teach
	 * Increase the count of courses taught by professor
	 */
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
				stmt = conn.prepareStatement(SqlQueries.INCREASE_COUNT_OF_PROFESSOR_COURSES);
				stmt.setString(1, username);
				stmt.executeUpdate();
				return "added";
			}
			else {
				return "Course already being taught";
			}
		}
		catch(Exception e){
			// Give error message if SQL query generate error
			logger.error("Error occured "+e.getMessage());			
		}
		return null;
	}
	
	@Override
	/*
	 * Fetch the courses taught by Professor
	 */
	public List<String> fetchTaughtCoursesName(String username) {
		// Establish connection 
		conn = DBUtils.getConnection();

		try {
			stmt = conn.prepareStatement(SqlQueries.GET_TAUGHT_COURSE_NAMES);
			stmt.setString(1,username);
			ResultSet rs = stmt.executeQuery();

			List<String> courseNames = new ArrayList<String>();

			// Extract data from result set
			while(rs.next()){
				//Retrieve by column name						
				courseNames.add(rs.getString("courseName"));
			}
			return courseNames;

		}
		catch(Exception e){
			// Give error message if SQL query generate error
			logger.error("Error occured "+e.getMessage());

		}
		return null;
	}

	
	@Override
	/*
	 * Fetch the students name registered for a course
	 */
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
			// Give error message if SQL query generate error
			logger.error("Error occured "+e.getMessage());			
		}
		return null;
	}

	@Override
	/*
	 * Get all the students logged in the system
	 */
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
			// Give error message if SQL query generate error
			logger.error("Error occured "+e.getMessage());

		}
		return null;

	}


	@Override
	/*
	 * Professor record grades of the student
	 */
	public String recordGrades(String courseName, String username, String grades) {
		conn = DBUtils.getConnection();

		try {
			stmt = conn.prepareStatement(SqlQueries.RECORD_GRADES);
			stmt.setString(1,grades);
			stmt.setString(2,courseName);
			stmt.setString(3,username);
			int rows = stmt.executeUpdate();	
			if(rows!=0)
			{
				return "added";
			}
			else {
				return "Student not registered for course.";
			}
		}
		catch(Exception e){
			// Give error message if SQL query generate error
			logger.error("Error occured "+e.getMessage());
		}
		return null;
	}
	
	
	@Override
	/*
	 * Fetch report card for the student
	 * Includes the grades of student in courses he/she registered
	 */
	public HashMap<String, String> fetchReportCard(String username) {
		// Establish connection 
		conn = DBUtils.getConnection();

		try {

			stmt = conn.prepareStatement(SqlQueries.GET_REPORT_CARD);
			stmt.setString(1,username);
			ResultSet rs = stmt.executeQuery();

			HashMap<String,String> report_card=new HashMap<String,String>();	

			// Extract data from result set
			while(rs.next()){
				//Retrieve by column name				
				report_card.put(rs.getString("course"),rs.getString("grades"));
			}
			return report_card;

		}
		catch(Exception e){
			// Give error message if SQL query generate error
			logger.error("Error occured "+e.getMessage());			
		}
		return null;
	}

}

package com.flipcard.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipcard.constants.SqlQueries;
import com.flipcard.model.Course;
import com.flipcard.utils.DBUtils;

/*
 *  The class Course Catalog interacts with the catalog table in Database
 */
public class CourseCatalog {
	
	// Create logger object to log the messages
	private static Logger logger=Logger.getLogger(CourseCatalog.class);
	
	// Initialize the connection with null
	private Connection conn=null;
	private PreparedStatement stmt = null;
	
	public List<Course> fetchCourses() {
				// Establish connection 
				conn = DBUtils.getConnection();

				try {
					 
					// Fetch course details from catalog
					stmt = conn.prepareStatement(SqlQueries.GET_COURSES);
					ResultSet rs = stmt.executeQuery();
					
					List<Course> courses = new ArrayList<Course>();

					// Extract data from result set
					while(rs.next()){
						
						//Retrieve by column name
						Course c=new Course();
						c.setCourseName( rs.getString("courseName"));
						c.setProfessorName(rs.getString("professorName"));
						c.setSubject(rs.getString("subject"));
						c.setFee(rs.getInt("fee"));
						courses.add(c);
					}
					return courses;
					
				}
				catch(Exception e){
					// Give error message if SQL query generate error
					logger.error("Error occured "+e.getMessage());
					
				}
				return null;
		
		
	}
	
	
	/*
	 * Fetch the course names 
	 */
	public List<String> fetchCoursesName() {
		// Establish connection 
		conn = DBUtils.getConnection();

		try {
			stmt = conn.prepareStatement(SqlQueries.GET_COURSE_NAMES);
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
			// Give error message if SQL query generate erro
			logger.error("Error occured "+e.getMessage());
			
		}
		return null;
	}
}

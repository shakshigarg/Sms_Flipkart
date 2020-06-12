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


public class CourseCatalog {
	
	private static Logger logger=Logger.getLogger(CourseCatalog.class);
	private Connection conn=null;
	private PreparedStatement stmt = null;
	
	public List<Course> fetchCourses() {
		// Establish connection 
				conn = DBUtils.getConnection();

				try {
					
					stmt = conn.prepareStatement(SqlQueries.GET_COURSES);
					ResultSet rs = stmt.executeQuery();

					List<Course> courses = new ArrayList<Course>();

					//STEP 5: Extract data from result set
					while(rs.next()){
						//Retrieve by column name
						Course c=new Course();
						c.setCourseName( rs.getString("courseName"));
						c.setNumberOfStudents(rs.getInt("numberOfStudents"));
						c.setProfessorName(rs.getString("professorName"));
						c.setSubject(rs.getString("subject"));
						c.setFee(rs.getInt("fee"));
						courses.add(c);
					}
					return courses;
					
				}
				catch(Exception e){
					logger.error("Error occured "+e.getMessage());
					
				}
				return null;
		
		
	}

	public List<String> fetchCoursesName() {
		// Establish connection 
		conn = DBUtils.getConnection();

		try {
			stmt = conn.prepareStatement(SqlQueries.GET_COURSE_NAMES);
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
}

package com.flipcard.service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.flipcard.DAO.CourseCatalog;
import com.flipcard.DAO.CourseUpdation;
import com.flipcard.DAO.CourseUpdationInterface;
import com.flipcard.client.Application;
import com.flipcard.exception.AlreadyRegisteredException;
import com.flipcard.exception.InvalidCourseException;
import com.flipcard.exception.NotRegisteredCourseException;
import com.flipcard.model.Course;
import com.flipcard.utils.DateTimeDay;

/*
 * This service is used to perform student functionalities
 */
public class StudentService implements StudentServiceInterface {
	
	private String username;
	
	// Get the catalog object to interact with database
	CourseCatalog catalog=new CourseCatalog();
	private static Logger logger = Logger.getLogger(Application.class);
	CourseUpdationInterface courseUpdateObject=new CourseUpdation();
	
	
	public StudentService(String username) {
		this.username=username;
	}
	
	@Override
	/*
	 * Fetch all the courses with details
	 */
	public void fetchCourses() {
		List<Course> courses = new ArrayList<Course>();
		courses=catalog.fetchCourses();
		// displays the courses
		courses.forEach(course->System.out.println("\n Course Name: "+course.getCourseName()+"\n Professor Name: "+course.getProfessorName()+"\n Fee "+course.getFee()+"\n Subject "+course.getSubject()+"\n\n"));
		
	}
	
	@Override
	/*
	 * Fetch all the course names from catalog
	 */
	public void fetchCourseNames() {
		List<String> coursesNames = new ArrayList<String>();
		coursesNames=catalog.fetchCoursesName();
		
		coursesNames.forEach(System.out::println);
		
	}

	
	@Override
	/*
	 * Register student for course
	 * throws error if course name is not valid and if the student registered already
	 */
	public void addCourse(String courseName,int paymentMode) throws InvalidCourseException, AlreadyRegisteredException {
			
		boolean valid=courseUpdateObject.verifyCourse(courseName);
		if(valid) {
			boolean added=courseUpdateObject.addCourse(username,courseName,paymentMode);
			if(added) {
				logger.info("Course Added Successfully on "+DateTimeDay.getDateTime());
				return;
			}
			else {
				// Give error if student already registered
				throw new AlreadyRegisteredException("You have already registered for this course");
			}
		}
		else {
			// Give error if course not available to register
			throw new InvalidCourseException("Course Not Available");
		}
		
	}


	@Override
	/*
	 * Fetech all the courses student is registered in
	 */
	public void fetchRegisteredCourses() {
		List<String> coursesNames = new ArrayList<String>();
		coursesNames=courseUpdateObject.fetchRegisteredCourses(username);
		// Displays course name
		coursesNames.forEach(System.out::println);
		
	}


	@Override
	/*
	 * Drop the course 
	 * throws error if student is not registered in course 
	 * throws error if course is invalid
	 */
	public void dropCourse(String courseName) throws NotRegisteredCourseException, InvalidCourseException {
		boolean valid=courseUpdateObject.verifyCourse(courseName);
		if(valid) {
			boolean dropped=courseUpdateObject.dropCourse(username,courseName);
			if(dropped) {
				logger.info("Course dropped Successfully on "+DateTimeDay.getDateTime());
				return;
			}
			else {
				
				// if student is not registered for course the course cannot be dropped
				throw new NotRegisteredCourseException("You have not registered for this course");
			}
		}
		else {
			// if course is not available then throw error
			throw new InvalidCourseException("Course Not Available");
		}
		
	}



	@Override
	/*
	 * Get report card of student
	 * if grades are not entered by professor then say Grades not recored by professor
	 */
	public void getReportCard() {
		HashMap<String,String> report_card=new HashMap<String,String>();
		report_card=courseUpdateObject.fetchReportCard(username);		
		int entries=report_card.size();
		if(entries==0) {
			logger.info("You are not registered for any course");
		}
		
		report_card.forEach((course,grade)->{
			 if(!grade.equalsIgnoreCase("")) 
				 System.out.println(course+"\t\t"+grade);  
			 else 
				 System.out.println(course+"\t\t"+"Grades not recorded by Professor");  		
		 });
			
	}


}

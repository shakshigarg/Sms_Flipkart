package com.flipcard.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipcard.DAO.CourseCatalog;
import com.flipcard.DAO.CourseUpdation;
import com.flipcard.DAO.CourseUpdationInterface;
import com.flipcard.client.Application;
import com.flipcard.exception.AlreadyRegisteredException;
import com.flipcard.exception.InvalidAuthenticationException;
import com.flipcard.exception.InvalidCourseException;
import com.flipcard.model.Course;

public class StudentService implements StudentServiceInterface {
	
	private String username;
	
	CourseCatalog catalog=new CourseCatalog();
	private static Logger logger = Logger.getLogger(Application.class);
	CourseUpdationInterface courseUpdateObject=new CourseUpdation();
	
	
	public StudentService(String username) {
		this.username=username;
	}


	public void fetchCourses() {
		List<Course> courses = new ArrayList<Course>();
		courses=catalog.fetchCourses();
		
		for(Course c:courses) {
			logger.info("\n Course Name: "+c.getCourseName()+"\n Number of students: "+c.getNumberOfStudents()+"\n Professor Name: "+c.getProfessorName()+"\n Fee "+c.getFee()+"\n Subject "+c.getSubject()+"\n\n");
		}
	}
	
	
	public void fetchCourseNames() {
		List<String> coursesNames = new ArrayList<String>();
		coursesNames=catalog.fetchCoursesName();
		
		for(String c:coursesNames) {
			logger.info("\n"+c);
		}
	}


	@Override
	public void addCourse(String courseName) throws InvalidCourseException, AlreadyRegisteredException {
		boolean valid=courseUpdateObject.verifyCourse(courseName);
		if(valid) {
//			boolean added=true;
			boolean added=courseUpdateObject.addCourse(username,courseName);
			if(added) {
				return;
			}
			else {
				throw new AlreadyRegisteredException("You have already registered for this course");
			}
		}
		else {
			throw new InvalidCourseException("Course Not Available");
		}
		
	}


}

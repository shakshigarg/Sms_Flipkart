package com.flipcard.service;

import java.time.LocalDate;
import java.time.LocalTime;
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
import com.flipcard.exception.NotRegisteredCourseException;
import com.flipcard.model.Course;
import com.flipcard.utils.DateTimeDay;

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
		
		courses.forEach(course->System.out.println("\n Course Name: "+course.getCourseName()+"\n Number of students: "+course.getNumberOfStudents()+"\n Professor Name: "+course.getProfessorName()+"\n Fee "+course.getFee()+"\n Subject "+course.getSubject()+"\n\n"));
		
	}
	
	
	public void fetchCourseNames() {
		List<String> coursesNames = new ArrayList<String>();
		coursesNames=catalog.fetchCoursesName();
		
		coursesNames.forEach(System.out::println);
		
	}


	@Override
	public void addCourse(String courseName) throws InvalidCourseException, AlreadyRegisteredException {
			
		boolean valid=courseUpdateObject.verifyCourse(courseName);
		if(valid) {
//			boolean added=true;
			boolean added=courseUpdateObject.addCourse(username,courseName);
			if(added) {
				logger.info("Course Added Successfully on "+DateTimeDay.getDateTime());
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


	@Override
	public void fetchRegisteredCourses() {
		List<String> coursesNames = new ArrayList<String>();
		coursesNames=courseUpdateObject.fetchRegisteredCourses(username);
		
		coursesNames.forEach(System.out::println);
		
	}


	@Override
	public void dropCourse(String courseName) throws NotRegisteredCourseException, InvalidCourseException {
		boolean valid=courseUpdateObject.verifyCourse(courseName);
		if(valid) {
//			boolean added=true;
			boolean dropped=courseUpdateObject.dropCourse(username,courseName);
			if(dropped) {
				logger.info("Course dropped Successfully on "+DateTimeDay.getDateTime());
				return;
			}
			else {
				throw new NotRegisteredCourseException("You have not registered for this course");
			}
		}
		else {
			throw new InvalidCourseException("Course Not Available");
		}
		
	}


}

package com.flipcard.service;

import com.flipcard.exception.AlreadyRegisteredException;
import com.flipcard.exception.InvalidCourseException;
import com.flipcard.exception.NotRegisteredCourseException;
/*
 * Student service interface class , Student service class implements this
 */
public interface StudentServiceInterface {
	
	// Fetch courses of student
	public void fetchCourses();
	
	// Fetch course names of student
	public void fetchCourseNames();
	
	// register student for a course
	void addCourse(String courseName) throws InvalidCourseException, AlreadyRegisteredException;
	
	// Fetch all registered courses by student
	public void fetchRegisteredCourses();
	
	// Drop  the course 
	public void dropCourse(String courseName) throws InvalidCourseException, NotRegisteredCourseException;
	
	// fetch report card
	public void getReportCard();
}

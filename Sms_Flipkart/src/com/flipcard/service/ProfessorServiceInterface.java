package com.flipcard.service;

import com.flipcard.exception.CourseAlreadyTaughtException;
import com.flipcard.exception.InvalidCourseException;
import com.flipcard.exception.StudentNotRegisteredException;

/*
 * Professor service class implements the professor service interface class
 */
public interface ProfessorServiceInterface {
	
	// Record grades of student
	public void recordGrades(String courseName, String studentName, String grades) throws StudentNotRegisteredException;

	// Fetch all course names from catalog
	public void fetchCourseNames();
	
	// Add course to teach
	public void addCourseToTeach(String courseName) throws CourseAlreadyTaughtException, InvalidCourseException;

	// Fetch the course names taught by professor
	public void fetchTaughtCourseNames();
	
	// Get student info that are registered in course
	public void getStudentInfo(String courseName) throws InvalidCourseException;
	
	// Get all students
	public void getAllStudents();

}

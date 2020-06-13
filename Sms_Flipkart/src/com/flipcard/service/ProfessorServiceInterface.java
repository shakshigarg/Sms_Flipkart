package com.flipcard.service;

import com.flipcard.exception.CourseAlreadyTaughtException;
import com.flipcard.exception.InvalidCourseException;

public interface ProfessorServiceInterface {

	public void recordGrades();

	public void fetchCourseNames();

	public void addCourseToTeach(String courseName) throws CourseAlreadyTaughtException, InvalidCourseException;

	public void fetchTaughtCourseNames();

	public void getStudentInfo(String courseName) throws InvalidCourseException;

	public void getAllStudents();

}

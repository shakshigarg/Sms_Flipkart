package com.flipcard.service;

import com.flipcard.exception.AlreadyRegisteredException;
import com.flipcard.exception.InvalidCourseException;
import com.flipcard.exception.NotRegisteredCourseException;

public interface StudentServiceInterface {
	public void fetchCourses();
	public void fetchCourseNames();
	void addCourse(String courseName) throws InvalidCourseException, AlreadyRegisteredException;
	public void fetchRegisteredCourses();
	public void dropCourse(String courseName) throws InvalidCourseException, NotRegisteredCourseException;
	public void getGrades(String courseName);
	public void getReportCard();
}

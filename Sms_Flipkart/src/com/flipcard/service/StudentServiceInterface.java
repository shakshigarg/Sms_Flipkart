package com.flipcard.service;

import com.flipcard.exception.AlreadyRegisteredException;
import com.flipcard.exception.InvalidCourseException;

public interface StudentServiceInterface {
	public void fetchCourses();
	public void fetchCourseNames();
	void addCourse(String courseName) throws InvalidCourseException, AlreadyRegisteredException;
}

package com.flipcard.DAO;

import java.util.List;

public interface CourseUpdationInterface {
	public boolean verifyCourse(String courseName);

	public boolean addCourse(String username, String courseName);

	public List<String> fetchRegisteredCourses(String username);

	public boolean dropCourse(String username, String courseName);
}

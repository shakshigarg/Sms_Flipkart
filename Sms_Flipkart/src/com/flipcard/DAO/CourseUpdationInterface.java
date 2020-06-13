package com.flipcard.DAO;

import java.util.List;

import com.flipcard.model.Student;

public interface CourseUpdationInterface {
	public boolean verifyCourse(String courseName);

	public boolean addCourse(String username, String courseName);

	public List<String> fetchRegisteredCourses(String username);

	public boolean dropCourse(String username, String courseName);

	public String addCourseToTeach(String username, String courseName);

	public List<String> fetchTaughtCoursesName(String username);

	public List<String> fetchStudentsName(String courseName);

	public List<Student> getAllStudents();
}

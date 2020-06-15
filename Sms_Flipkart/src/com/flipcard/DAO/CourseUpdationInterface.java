package com.flipcard.DAO;

import java.util.HashMap;
import java.util.List;

import com.flipcard.model.Student;

/*
 * The course updation interface is implemented by course updation class
 */
public interface CourseUpdationInterface {
	
	// Verify the course
	public boolean verifyCourse(String courseName);
	
	// Register student in course
	public boolean addCourse(String username, String courseName);

	// Fetch registered courses of student
	public List<String> fetchRegisteredCourses(String username);

	// Drop student course
	public boolean dropCourse(String username, String courseName);

	// Add courses to teach for professor
	public String addCourseToTeach(String username, String courseName);

	// Fetch courses taught by professor
	public List<String> fetchTaughtCoursesName(String username);

	// Fetch student names registered in course
	public List<String> fetchStudentsName(String courseName);

	// Get all the students 
	public List<Student> getAllStudents();

	// Record grades for student
	public String recordGrades(String courseName, String username, String grades);

	// Fetch report card
	public HashMap<String, String> fetchReportCard(String username);
}

package com.flipcard.service;

import com.flipcard.exception.CourseAlreadyExist;
import com.flipcard.exception.CourseNotExist;
import com.flipcard.exception.InvalidRoleInput;
import com.flipcard.exception.UserAlreadyExist;
import com.flipcard.exception.UserNotExistForRole;
import com.flipcard.model.Admin;
import com.flipcard.model.Course;
import com.flipcard.model.Professor;
import com.flipcard.model.Student;

/*
 * Admin Service class implements admin service interface
 */
public interface AdminServiceInterface {

	// Create new student account
	void createStudent(Student s,String password);

	// Create new professor account
	void createProfessor(Professor p, String password);
	
	// Create new admin account
	void createAdmin(Admin a, String password);
	
	// Check if new username entered is valid or not
	void checkUsername(String userName) throws UserAlreadyExist;
	
	// Get student / professor
	void getUsersWithRole(String string) throws InvalidRoleInput;

	// delete user with specific role
	void deleteUser(String username, String role) throws UserNotExistForRole;

	// Delete admin self account
	void deleteSelfAccount();

	// Check if new course name entered is valid or not
	void checkCourseName(String courseName) throws CourseAlreadyExist;

	// Create new course
	void createCourse(Course c);

	// Update existing course
	void UpdateCourse(Course c, String coursename);

	// Check if course name entered for update exist or not
	void checkCourseNameForUpdate(String courseName) throws CourseNotExist;

	// fetch all courses
	void fetchCourse();

}

package com.flipcard.DAO;

import java.util.List;

import com.flipcard.model.Admin;
import com.flipcard.model.Course;
import com.flipcard.model.Professor;
import com.flipcard.model.Student;

/*
 * AdminDao class imlements admindao Interface
 */
public interface AdminDaoInterface {
	
	// Create new student
	void createStudent(Student s,String password);

	// Create new professor
	void createProfessor(Professor p, String password);

	// Create new admin
	void createAdmin(Admin a, String password);

	// Check if username entered is valid
	boolean checkUsername(String userName);

	// Get users with specific role
	List<String> getUsersWithRole(String role);

	// Delete the user
	boolean deleteUser(String username, String role);

	// Delete self account
	void deleteSelfAccount(String username);

	// Check if new course name is valid
	boolean checkCourseName(String courseName);

	// Create course
	void createCourse(Course c);

	// Update existing course
	void updateCourse(Course c, String coursename);

}

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

public interface AdminServiceInterface {


	void createStudent(Student s,String password);

	void createProfessor(Professor p, String password);
	
	void createAdmin(Admin a, String password);

	void checkUsername(String userName) throws UserAlreadyExist;

	void getUsersWithRole(String string) throws InvalidRoleInput;

	void deleteUser(String username, String role) throws UserNotExistForRole;

	void deleteSelfAccount();

	void checkCourseName(String courseName) throws CourseAlreadyExist;

	void createCourse(Course c);

	void UpdateCourse(Course c, String coursename);

	void checkCourseNameForUpdate(String courseName) throws CourseNotExist;

	void fetchCourse();

}

package com.flipcard.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipcard.DAO.AdminDao;
import com.flipcard.DAO.AdminDaoInterface;
import com.flipcard.DAO.CourseCatalog;
import com.flipcard.DAO.CourseUpdation;
import com.flipcard.DAO.CourseUpdationInterface;
import com.flipcard.client.UserController;
import com.flipcard.exception.AlreadyRegisteredException;
import com.flipcard.exception.CourseAlreadyExist;
import com.flipcard.exception.CourseNotExist;
import com.flipcard.exception.InvalidCourseException;
import com.flipcard.exception.InvalidRoleInput;
import com.flipcard.exception.UserAlreadyExist;
import com.flipcard.exception.UserNotExistForRole;
import com.flipcard.model.Admin;
import com.flipcard.model.Course;
import com.flipcard.model.Professor;
import com.flipcard.model.Student;
import com.flipcard.utils.DateTimeDay;

public class AdminService implements AdminServiceInterface {

	// The username is set when admin log in
	private String username;

	// Create logger object to log the messages
	private static Logger logger = Logger.getLogger(UserController.class);

	// Create the catalog of course catalog class that interact with catalog
	// tables
	CourseCatalog catalog = new CourseCatalog();

	// Create the Administrator object to interact with database
	AdminDaoInterface adminDaoObject = new AdminDao();

	public AdminService(String username) {
		this.username = username;
	}

	public AdminService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	/*
	 * Create student and specify the date, time and day when student is created
	 */
	public void createStudent(Student s, String password) {
		adminDaoObject.createStudent(s, password);
		logger.info("Student created Successfully on " + DateTimeDay.getDateTime());
	}

	@Override
	/*
	 * Create professor and specify the date, time and day when student is
	 * created
	 */
	public void createProfessor(Professor p, String password) {

		adminDaoObject.createProfessor(p, password);
		logger.info("Professor created Successfully on " + DateTimeDay.getDateTime());

	}

	@Override
	/*
	 * Create administrator and specify the date, time and day when student is
	 * created
	 */
	public void createAdmin(Admin a, String password) {
		adminDaoObject.createAdmin(a, password);
		logger.info("Admin created Successfully on " + DateTimeDay.getDateTime());
	}

	@Override
	/*
	 * Check if user name exist or not
	 */
	public void checkUsername(String userName) throws UserAlreadyExist {
		boolean exist = adminDaoObject.checkUsername(userName);

		// if username already exist then throw error
		if (exist) {
			throw new UserAlreadyExist("Username already exists!");
		} else {
			return;
		}

	}

	@Override
	/*
	 * Get all users with specified role
	 */
	public void getUsersWithRole(String role) throws InvalidRoleInput {
		if (role.equalsIgnoreCase("admin")) {
			throw new InvalidRoleInput("Invalid role input!");
		}

		// Get the user names as list
		List<String> userNames = new ArrayList<String>();
		userNames = adminDaoObject.getUsersWithRole(role);

		// if the list is empty then no user exist for this role
		if (userNames.isEmpty()) {
			logger.info("No " + role + " exists!");
			return;
		}
		logger.info("Below are the all " + role + "s !");

		// Used lambda expression to show list
		// userNames.forEach(System.out::println);

	}

	@Override
	/*
	 * Delete user with specific username and role
	 */
	public void deleteUser(String username, String role) throws UserNotExistForRole {
		boolean deleted = adminDaoObject.deleteUser(username, role);

		// if deleted successfully the give message with date time and day else
		// throw user not exist exception
		if (deleted) {
			logger.info(role + " deleted Successfully on " + DateTimeDay.getDateTime());
			return;
		} else {
			throw new UserNotExistForRole(role + " does not exist!");
		}

	}

	@Override
	/*
	 * The method deletes the self account of admin
	 */
	public void deleteSelfAccount() {
		adminDaoObject.deleteSelfAccount(username);
		logger.info("Account deleted successfully!");

	}

	@Override
	/*
	 * Checks if new course name entered is valid or not.
	 */
	public void checkCourseName(String courseName) throws CourseAlreadyExist {
		boolean exist = adminDaoObject.checkCourseName(courseName);
		if (exist) {
			throw new CourseAlreadyExist("Course already exists!");
		} else {
			return;
		}

	}

	@Override
	/*
	 * Create new course in catalog
	 */
	public void createCourse(Course c) {
		adminDaoObject.createCourse(c);
		logger.info("Course created Successfully on " + DateTimeDay.getDateTime());

	}

	@Override
	/*
	 * Checks if the course name entered by admin to update exists or not
	 */
	public void checkCourseNameForUpdate(String courseName) throws CourseNotExist {
		boolean exist = adminDaoObject.checkCourseName(courseName);
		if (!exist) {

			// if course does not exist then give error course not exist
			throw new CourseNotExist("Course not exists!");
		} else {
			return;
		}

	}

	@Override
	/*
	 * Undates the course information
	 */
	public void UpdateCourse(Course c, String coursename) {
		adminDaoObject.updateCourse(c, coursename);
		logger.info("Course updated Successfully on " + DateTimeDay.getDateTime());

	}

	@Override
	/*
	 * Fetch all courses from catalog
	 */
	public void fetchCourse() {
		List<Course> courses = new ArrayList<Course>();
		courses = catalog.fetchCourses();

	}

}

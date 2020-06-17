package com.flipcard.constants;

public class SqlQueries {
	
	// Select all courses from catalog
	public static String GET_COURSES="SELECT * FROM CATALOG";
	
	// select Course names from catalog
	public static String GET_COURSE_NAMES="SELECT COURSENAME FROM CATALOG";
	
	// Fetch the coursenames in which student is registered
	public static String GET_REGISTERED_COURSE_NAMES="SELECT COURSENAME FROM REGISTRATION WHERE USERNAME=?";
	
	// Check if the username and password is valid
	public static String CHECK_IDENTITY="SELECT ROLEID FROM USERS WHERE USERNAME=? AND PASSWORD=?";
	
	// Check if course added is valid or not
	public static String VERIFY_COURSE="SELECT * FROM CATALOG WHERE COURSENAME=?";
	
	// Add new course to student courses
	public static String ADD_NEW_COURSE="INSERT INTO REGISTRATION VALUES (?,?,?,?)";
	
	// Drop registered course
	public static String DROP_COURSE="DELETE FROM REGISTRATION WHERE USERNAME=? AND COURSENAME=?";
	
	// Add course to teach for professor
	public static String ADD_COURSE_TO_TEACH="UPDATE CATALOG SET PROFESSORNAME=? WHERE COURSENAME=? AND PROFESSORNAME=?";
	
	// Fetch all the courses taught by professor
	public static String GET_TAUGHT_COURSE_NAMES="SELECT COURSENAME FROM CATALOG WHERE PROFESSORNAME=?";
	
	// Get Students registered for a course
	public static String GET_STUDENTS_NAME="SELECT USERNAME FROM REGISTRATION WHERE COURSENAME=?";
	
	// Get all the students 
	public static String GET_ALL_STUDENTS="SELECT * FROM STUDENT";
	
	// Record grades of student
	public static String RECORD_GRADES="UPDATE GRADES SET GRADES=? WHERE COURSE=? AND USERNAME=?";
	
	// Fetch report card
	public static String GET_REPORT_CARD="SELECT COURSE,GRADES FROM GRADES WHERE USERNAME=?";
	
	// Add student, professor, admin credentials
	public static String ADD_USER_CREDENTIALS="INSERT INTO USERS VALUES (?,?,?)";
	
	// Add student information
	public static String ADD_STUDENT_INFO="INSERT INTO STUDENT VALUES (?,?,?,?,?,?)";
	
	// Add Professor information
	public static String ADD_PROFESSOR_INFO="INSERT INTO PROFESSOR VALUES (?,?,?,?,?)";
	
	// Add Admin information
	public static String ADD_ADMIN_INFO="INSERT INTO ADMIN VALUES (?,?,?,?)";
	
	// Check if the username created is valid or not
	public static String CHECK_USERNAME="SELECT * FROM USERS WHERE USERNAME=?";
	
	// Get Students or professors or admin
	public static String GET_USERS_WITH_ROLE="SELECT USERNAME FROM USERS WHERE ROLEID=?";
	
	// Delete student/professor/admin
	public static String DELETE_USER_WITH_ROLE="DELETE FROM USERS WHERE USERNAME=? and ROLEID=?";
	
	// Delete student information
	public static String DELETE_STUDENT_INFO="DELETE FROM STUDENT WHERE USERNAME=?";
	
	// Delete professor information
	public static String DELETE_PROFESSOR_INFO="DELETE FROM PROFESSOR WHERE USERNAME=?";
	
	// Delete student from student courses
	public static String DELETE_STUDENT_COURSES="DELETE FROM REGISTRATION WHERE USERNAME=?";
	
	// Delete student from grades
		public static String DELETE_STUDENT_GRADES="DELETE FROM GRADES WHERE USERNAME=?";
	
	// Delete professor from catalog
	public static String RESET_PROFESSOR_COURSES="UPDATE CATALOG SET PROFESSORNAME='' WHERE PROFESSORNAME=?";
	
	// Delete admin from admin info
	public static String DELETE_ADMIN_INFO="DELETE FROM ADMIN WHERE USERNAME=?";
		
	// Check if course name exist
	public static String CHECK_COURSENAME="SELECT * FROM CATALOG WHERE COURSENAME=?";
	
	// Create new course
	public static String CREATE_COURSE="INSERT INTO CATALOG VALUES (?,?,?,?)";
	
	// Update already existing course
	public static String UPDATE_COURSE="UPDATE CATALOG SET COURSENAME=?,SUBJECT=?,FEE=? WHERE COURSENAME=?";
	
	// Increase number of courses count for student
	public static String INCREASE_COUNT_OF_COURSES="UPDATE STUDENT SET NUMBEROFCOURSES=NUMBEROFCOURSES+1 WHERE USERNAME=?";
	
	// Decrease number of courses count from student
	public static String DECREASE_COUNT_OF_COURSES="UPDATE STUDENT SET NUMBEROFCOURSES=NUMBEROFCOURSES-1 WHERE USERNAME=?";
	
	// Increase no of courses count for professor
	public static String INCREASE_COUNT_OF_PROFESSOR_COURSES="UPDATE PROFESSOR SET NUMBEROFCOURSES=NUMBEROFCOURSES+1 WHERE USERNAME=?";

	//Get role of user
	public static String GET_ROLE="SELECT ROLENAME FROM ROLE WHERE ROLEID=?";
	
	// Add course to grades
	public static String ADD_TO_GRADES="INSERT INTO GRADES VALUES(?,?,?)";
	// Drop course from grades
	public static String DROP_FROM_GRADES="DELETE FROM GRADES WHERE USERNAME=? AND COURSE=?";
	
	// Add in professor courses table
	public static String ADD_TO_PROFESSOR_COURSES="INSERT INTO PROFESSORCOURSES VALUES(?,?,?)";
	
	// Check if student is already registered for course
	public static String CHECK_IF_REGISTERED="SELECT * FROM REGISTRATION WHERE USERNAME=? AND COURSENAME=?";
	
	// Store the payment status
	public static String PAY="INSERT INTO PAYMENT(PAYMENTMODE,DESCRIPTION) VALUES(?,?)";
	
	// Get the payment Id
	public static String GET_PAYMENT_ID="SELECT paymentId FROM PAYMENT WHERE PAYMENTMODE=? AND DESCRIPTION=?";
}

package com.flipcard.constants;

public class SqlQueries {
	public static String GET_COURSES="SELECT * FROM CATALOG";
	public static String GET_COURSE_NAMES="SELECT COURSENAME FROM CATALOG";
	public static String CHECK_IDENTITY="SELECT ROLE FROM AUTHCREDENTIALS WHERE USERNAME=? AND PASSWORD=?";
	public static String VERIFY_COURSE="SELECT * FROM CATALOG WHERE COURSENAME=?";
	public static String ADD_NEW_COURSE="INSERT INTO STUDENTCOURSES VALUES (?,?)";
	
}

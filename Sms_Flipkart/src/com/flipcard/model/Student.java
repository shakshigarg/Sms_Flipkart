package com.flipcard.model;

public class Student {
	private String userName;
	private int numberOfCourses;
	private String phoneNumber;
	private String address;
	private int scholarshipId;
	private String gender;
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the numberOfCourses
	 */
	public int getNumberOfCourses() {
		return numberOfCourses;
	}
	/**
	 * @param numberOfCourses the numberOfCourses to set
	 */
	public void setNumberOfCourses(int numberOfCourses) {
		this.numberOfCourses = numberOfCourses;
	}
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the scholarshipId
	 */
	public int getScholarshipId() {
		return scholarshipId;
	}
	/**
	 * @param scholarshipId the scholarshipId to set
	 */
	public void setScholarshipId(int scholarshipId) {
		this.scholarshipId = scholarshipId;
	}
	
}

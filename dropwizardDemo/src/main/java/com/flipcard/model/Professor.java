package com.flipcard.model;

/*
 * Blueprint of Professor
 * contains getters and setters for each private data field
 */
public class Professor {
	private String userName;
	private String phoneNumber;
	private String address;
	private int numberOfCourses;
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
	public int getNumberOfCourses() {
		return numberOfCourses;
	}
	public void setNumberOfCourses(int i) {
		this.numberOfCourses = i;
	}
	
}

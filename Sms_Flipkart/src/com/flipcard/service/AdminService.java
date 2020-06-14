package com.flipcard.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipcard.DAO.AdminDao;
import com.flipcard.DAO.AdminDaoInterface;
import com.flipcard.DAO.CourseCatalog;
import com.flipcard.DAO.CourseUpdation;
import com.flipcard.DAO.CourseUpdationInterface;
import com.flipcard.client.Application;
import com.flipcard.exception.AlreadyRegisteredException;
import com.flipcard.exception.InvalidCourseException;
import com.flipcard.exception.InvalidRoleInput;
import com.flipcard.exception.UserAlreadyExist;
import com.flipcard.exception.UserNotExistForRole;
import com.flipcard.model.Admin;
import com.flipcard.model.Professor;
import com.flipcard.model.Student;
import com.flipcard.utils.DateTimeDay;

public class AdminService implements AdminServiceInterface {

	private String username;
	private static Logger logger = Logger.getLogger(Application.class);	
	AdminDaoInterface adminDaoObject=new AdminDao();
	public AdminService(String username) {
		this.username=username;
	}

	@Override
	public void createStudent(Student s,String password){
			adminDaoObject.createStudent(s,password);			
			logger.info("Student created Successfully on "+DateTimeDay.getDateTime());
	}

	@Override
	public void createProfessor(Professor p, String password){
		
		adminDaoObject.createProfessor(p,password);			
		logger.info("Professor created Successfully on "+DateTimeDay.getDateTime());
		
	}

	@Override
	public void createAdmin(Admin a, String password){
		adminDaoObject.createAdmin(a,password);			
		logger.info("Admin created Successfully on "+DateTimeDay.getDateTime());
	}

	@Override
	public void checkUsername(String userName) throws UserAlreadyExist {
		boolean exist=adminDaoObject.checkUsername(userName);
		if(exist) {
			throw new UserAlreadyExist("Username already exists!");
		}
		else {
			return;
		}
		
	}

	@Override
	public void getUsersWithRole(String role) throws InvalidRoleInput {
		if(role.contentEquals("Admin")||role.contentEquals("admin")) {
			throw new InvalidRoleInput("Invalid role input!");
		}
		List<String> userNames = new ArrayList<String>();
		userNames=adminDaoObject.getUsersWithRole(role);
		if(userNames.isEmpty()) {
			logger.info("No "+role+" exists!");
			return;
		}
		logger.info("Below are the all "+role+"s !");
		userNames.forEach(System.out::println);
		
	}

	@Override
	public void deleteUser(String username, String role) throws UserNotExistForRole {
			boolean deleted=adminDaoObject.deleteUser(username,role);
			if(deleted) {
				logger.info(role+" deleted Successfully on "+DateTimeDay.getDateTime());
				return;
			}
			else {
				throw new UserNotExistForRole(role+" does not exist!");
			}
		
		
	}

	@Override
	public void deleteSelfAccount() {
		adminDaoObject.deleteSelfAccount(username);
		logger.info("Account deleted successfully!");
		
	}

}

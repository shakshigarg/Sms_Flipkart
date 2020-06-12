package com.flipcard.client;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.flipcard.DAO.AuthCredentials;
import com.flipcard.exception.AlreadyRegisteredException;
import com.flipcard.exception.InvalidAuthenticationException;
import com.flipcard.exception.InvalidCourseException;
import com.flipcard.model.Professor;
import com.flipcard.model.Student;
import com.flipcard.service.AuthenticationService;
import com.flipcard.service.AuthenticationServiceInterface;
import com.flipcard.service.StudentService;
import com.flipcard.service.StudentServiceInterface;

public class Application {
	private static Scanner sc= new Scanner(System.in);
//	private static AuthenticationServiceInterface authServiceObject=new AuthenticationService();
	
	private static Logger logger = Logger.getLogger(Application.class);
	
	
	public static void main(String[] args) throws AlreadyRegisteredException {
		String role=null;
		String username=null;
		String password=null;
		while(role==null) {
			try {
				logger.info("enter username and password");
				username=sc.next();
				password=sc.next();
				role=AuthenticationService.checkIdentity(username, password);
			}catch(InvalidAuthenticationException e) {
				logger.info(e.getMessage());
			}
		}

		logger.info("Login Success");
		logger.info("welcome "+username);
		
		if(role.contentEquals("student")) {
			
			StudentServiceInterface studentOperation=new StudentService(username);
			int val;
			String courseName=null;
			while(true) {
				logger.info("\n1. Fetch Catalog Courses\n2. Add Course\n3. Drop Course\n4. Get Report Card\n5. Change Schedule\n6. exit");
				val=sc.nextInt();
				switch(val) {
				case 1:
					studentOperation.fetchCourses();
					continue;

				case 2:
					logger.info("\nBelow are the available courses\n");
					studentOperation.fetchCourseNames();
					logger.info("Enter the course Name you want to add");
					courseName=sc.next();
					try {
						studentOperation.addCourse(courseName);
					} catch (InvalidCourseException e) {
						// TODO Auto-generated catch block
						logger.info(e.getMessage());
					} catch(AlreadyRegisteredException e) {
						logger.info(e.getMessage());
					}
					continue;
					
				case 3:
					logger.info("\nBelow are the courses in which you have registered\n");
//					studentOperation.fetchRegisteredCourses();
					logger.info("Enter the course Name you want to add");
					courseName=sc.next();
					try {
						studentOperation.addCourse(courseName);
					} catch (InvalidCourseException e) {
						// TODO Auto-generated catch block
						logger.info(e.getMessage());
					} catch(AlreadyRegisteredException e) {
						logger.info(e.getMessage());
					}
					continue;
					
					
				case 4:
					
					
				case 5:
					
					
				case 6:
					
				

			}
			
		}
		}
		if(role.contentEquals("Professor")) {

//			ProfessorServiceInterface professorOperation=new ProfessorService();

			logger.info("\n1. Record Grades\n2. Get Student Info For a course\n3. Add Course to teach\n4. exit");

		}
		
		if(role.contentEquals("Admin")) {

			

			logger.info("\n1. Create Student\n2. Drop Student\n3. Create Professor\n4. Drop Professor\n5. Add Admin 6. Drop Admin 7.exit ");

		}
		
		
		
		


}
}

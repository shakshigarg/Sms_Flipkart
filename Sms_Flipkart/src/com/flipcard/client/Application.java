package com.flipcard.client;


import java.util.Scanner;

import org.apache.log4j.Logger;


import com.flipcard.exception.AlreadyRegisteredException;
import com.flipcard.exception.CourseAlreadyTaughtException;
import com.flipcard.exception.InvalidAuthenticationException;
import com.flipcard.exception.InvalidCourseException;
import com.flipcard.exception.InvalidRoleInput;
import com.flipcard.exception.NotRegisteredCourseException;
import com.flipcard.exception.UserAlreadyExist;
import com.flipcard.exception.UserNotExistForRole;
import com.flipcard.exception.StudentNotRegisteredException;
import com.flipcard.model.Admin;
import com.flipcard.model.Professor;
import com.flipcard.model.Student;
import com.flipcard.service.AdminService;
import com.flipcard.service.AdminServiceInterface;
import com.flipcard.service.AuthenticationService;
import com.flipcard.service.ProfessorService;
import com.flipcard.service.ProfessorServiceInterface;
import com.flipcard.service.StudentService;
import com.flipcard.service.StudentServiceInterface;
import com.flipcard.utils.DateTimeDay;

public class Application {
	private static Scanner sc= new Scanner(System.in);
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
		logger.info(DateTimeDay.getDateTimeDay()+"  Welcome "+username);

		if(role.contentEquals("student")) {

			StudentServiceInterface studentOperation=new StudentService(username);
			int val;
			String courseName=null;
			while(true) {
				logger.info("-----------Menu----------");
				logger.info("1. Fetch Catalog Courses");
				logger.info("2. Add Course");
				logger.info("3. Drop Course");
				logger.info("4. Get Report Card");
				logger.info("5. Logout");
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
					studentOperation.fetchRegisteredCourses();
					logger.info("Enter the course Name you want to Drop");
					courseName=sc.next();
					try {
						studentOperation.dropCourse(courseName);
					} catch (InvalidCourseException e) {
						// TODO Auto-generated catch block
						logger.info(e.getMessage());
					} catch (NotRegisteredCourseException e) {
						// TODO Auto-generated catch block
						logger.info(e.getMessage());
					}
					continue;			
				case 4:	
					studentOperation.getReportCard();					
					continue;
				case 5:
					AuthenticationService.logout();
					logger.info("Logout Successfull! GOODBYE!");
					return;


				}

			}
		}
		if(role.contentEquals("professor")) {


			ProfessorServiceInterface professorOperation=new ProfessorService(username);
			int val;
			String courseName=null;
			while(true) {
				logger.info("-----------Menu----------");
				logger.info("1. Record Grades");
				logger.info("2. Get All Students");
				logger.info("3. Get Students enrolled in a course");
				logger.info("4. Add course to teach");
				logger.info("5. Logout");
				val=sc.nextInt();
				switch(val) {
				case 1:
					logger.info("\nBelow are courses that you teach\n");
					professorOperation.fetchTaughtCourseNames();
					logger.info("Enter the course Name for which you want to add student grades");
					courseName=sc.next();
					logger.info("Below are the students registered for this course");
					try {
						professorOperation.getStudentInfo(courseName);
					} catch (InvalidCourseException e) {
						logger.info(e.getMessage());
						continue;
					}
					logger.info("Enter the student Name and grades");
					String studentName=sc.next();
					String grades=sc.next();
					try {
						professorOperation.recordGrades(courseName,studentName,grades);
					} catch (StudentNotRegisteredException e) {
						logger.info(e.getMessage());
					}
					continue;
				case 2:
					professorOperation.getAllStudents();
					continue;
				case 3:
					logger.info("\nBelow are courses that you teach\n");
					professorOperation.fetchTaughtCourseNames();
					logger.info("Enter the course Name for which you want students info");
					courseName=sc.next();
					try {
						professorOperation.getStudentInfo(courseName);

					} catch (InvalidCourseException e) {
						// TODO Auto-generated catch block
						logger.info(e.getMessage());
					} 
					continue;

				case 4:
					logger.info("\nChoose course you want to teach\n");
					professorOperation.fetchCourseNames();
					logger.info("Enter the course Name you want to teach");
					courseName=sc.next();
					try {
						professorOperation.addCourseToTeach(courseName);

					} catch (InvalidCourseException e) {
						// TODO Auto-generated catch block
						logger.info(e.getMessage());
					} catch (CourseAlreadyTaughtException e) {
						logger.info(e.getMessage());
					}
					continue;			
				case 5:
					AuthenticationService.logout();
					logger.info("Logout Successfull! GOODBYE!");
					return;


				}

			}






		}

		if(role.contentEquals("admin")) {	
			AdminServiceInterface adminOperation=new AdminService(username);
			int val;
			while(true) {
				logger.info("-----------Menu----------");
				logger.info("1. Create Student");
				logger.info("2. Create professor");
				logger.info("3. Create Admin");
				logger.info("4. Delete Student/Professor");
				logger.info("5. Delete Self Account");
				logger.info("6. Add a course");
				logger.info("7. Update a course");
				logger.info("6. Logout");
				val=sc.nextInt();
				switch(val) {
				case 1:
					logger.info("Enter details to create student");
					Student s=new Student();	
					try {
						logger.info("Create username: ");
						s.setUserName(sc.next());
						adminOperation.checkUsername(s.getUserName());
						logger.info("Create password: ");
						password=sc.next();
						logger.info("Enter the Gender: ");
						s.setGender(sc.next());
						logger.info("Enter the Address ");
						s.setAddress(sc.next());
						logger.info("Enter the phone number ");
						s.setPhoneNumber(sc.next());
						logger.info("Enter the Shcolarship Id ,if not available enter -1");
						s.setScholarshipId(sc.nextInt());
						s.setNumberOfCourses(0);
						adminOperation.createStudent(s,password);
					} catch (UserAlreadyExist e) {
						logger.info(e.getMessage());
					}
					continue;
				case 2:
					logger.info("Enter details to create Professor");
					Professor p=new Professor();
					try {
						logger.info("Create username: ");
						p.setUserName(sc.next());
						adminOperation.checkUsername(p.getUserName());
						logger.info("Create password: ");
						password=sc.next();
						logger.info("Enter the Gender: ");
						p.setGender(sc.next());
						logger.info("Enter the Address ");
						p.setAddress(sc.next());
						logger.info("Enter the phone number ");
						p.setPhoneNumber(sc.next());
						p.setNumberOfCourses(0);
						adminOperation.createProfessor(p,password);
					}catch (UserAlreadyExist e) {
						logger.info(e.getMessage());
					}
					continue;			
				case 3:
					logger.info("Enter details to create Admin");
					Admin a=new Admin();
					try {
						logger.info("Create username: ");
						a.setUserName(sc.next());
						adminOperation.checkUsername(a.getUserName());
						logger.info("Create password: ");
						password=sc.next();
						logger.info("Enter the Gender: ");
						a.setGender(sc.next());
						logger.info("Enter the Address ");
						a.setAddress(sc.next());
						logger.info("Enter the phone number ");
						a.setPhoneNumber(sc.next());
						adminOperation.createAdmin(a,password);
					}
					catch (UserAlreadyExist e) {
						logger.info(e.getMessage());
					}
					continue;
				case 4:
					try {
					logger.info("Delete Student/Professor?");
					role=sc.next();
					adminOperation.getUsersWithRole(role);
					logger.info("Enter the username of "+role+" you want to delete");
					username=sc.next();
					adminOperation.deleteUser(username,role);
					} catch (UserNotExistForRole e) {
						logger.info(e.getMessage());
					} catch (InvalidRoleInput e) {
						logger.info(e.getMessage());
					} 
					continue;
				case 5:
						logger.info("You surely want to delete your account? Enter yes/no");
						String ans=sc.next();
						if(ans.contentEquals("yes")||ans.contentEquals("Yes")) {
							adminOperation.deleteSelfAccount();
							logger.info("GOODBYE!");
							return;
						}
						else {
							logger.info("Cannot delete account without your consent!");
							continue;
						}					
				case 6:
					AuthenticationService.logout();
					logger.info("Logout Successfull! GOODBYE!");
					return;	


				}

			}

		}






	}
}

package com.flipcard.client;
import java.util.Scanner;
import org.apache.log4j.Logger;
import com.flipcard.exception.AlreadyRegisteredException;
import com.flipcard.exception.CourseAlreadyExist;
import com.flipcard.exception.CourseAlreadyTaughtException;
import com.flipcard.exception.CourseNotExist;
import com.flipcard.exception.InvalidAuthenticationException;
import com.flipcard.exception.InvalidCourseException;
import com.flipcard.exception.InvalidRoleInput;
import com.flipcard.exception.NotRegisteredCourseException;
import com.flipcard.exception.UserAlreadyExist;
import com.flipcard.exception.UserNotExistForRole;
import com.flipcard.exception.StudentNotRegisteredException;
import com.flipcard.model.Admin;
import com.flipcard.model.Course;
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


//The Client Application 
public class Application {

	//Creating Scanner object for input output
	private static Scanner sc= new Scanner(System.in);

	// Creating logger object for logging the details
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

				// Check whether the username and password is valid or not
				role=AuthenticationService.checkIdentity(username, password);
			}catch(InvalidAuthenticationException e) {
				// Exception thrown if invalid username and password
				logger.error(e.getMessage());
			}
		}

		logger.info("Login Success");
		logger.info(DateTimeDay.getDateTimeDay()+"  Welcome "+username);

		if(role.equalsIgnoreCase("student")) {

			//Creating the student service object
			StudentServiceInterface studentOperation=new StudentService(username);
			int val;
			String courseName=null;
			while(true) {

				// Displays all the functionality for Student
				logger.info("-----------Menu----------");
				logger.info("1. Fetch Catalog Courses");
				logger.info("2. Register for Course");
				logger.info("3. Drop Course");
				logger.info("4. Get Report Card");
				logger.info("5. Logout");
				val=sc.nextInt();
				switch(val) {
				case 1:
					// Fetch all courses with details
					studentOperation.fetchCourses();
					continue;

				case 2:
					// Shows the all available course names so that student can add easily
					logger.info("\nBelow are the available courses\n");
					studentOperation.fetchCourseNames();
					logger.info("Enter the course Name you want to add");
					courseName=sc.next();		
					logger.info("Enter the payment mode\n");
					logger.info("1. Debit card");
					logger.info("2. Net banking");
					logger.info("3. UPI");
					int paymentMode=sc.nextInt();
					try {
						// Check if course is valid or not if valid then register the student for it
						studentOperation.addCourse(courseName,paymentMode);

					} catch (InvalidCourseException e) {

						// If course invalid then give message
						logger.error(e.getMessage());

					} catch(AlreadyRegisteredException e) {

						// If student is already registered in course then give message
						logger.error(e.getMessage());
					}
					continue;

				case 3:
					// Give all the courses student is registered in
					logger.info("\nBelow are the courses in which you have registered\n");
					studentOperation.fetchRegisteredCourses();
					logger.info("Enter the course Name you want to Drop");
					courseName=sc.next();
					try {
						// Drop the course if it is valid and student is registered for it
						studentOperation.dropCourse(courseName);
					} catch (InvalidCourseException e) {
						// If invalid course then give message
						logger.error(e.getMessage());
					} catch (NotRegisteredCourseException e) {
						// If student not registered for course then give message
						logger.error(e.getMessage());
					}
					continue;			
				case 4:	
					// Fetch all grades of Student
					studentOperation.getReportCard();					
					continue;
				case 5:
					// Close connection with database and logout Student
					AuthenticationService.logout();
					logger.info("Logout Successfull on "+DateTimeDay.getDateTimeDay());
					logger.info("GOODBYE!");
					return;


				}

			}
		}
		if(role.equalsIgnoreCase("professor")) {

			// Create object for professor service
			ProfessorServiceInterface professorOperation=new ProfessorService(username);
			int val;
			String courseName=null;
			while(true) {

				// Displays all functionalities of professor
				logger.info("-----------Menu----------");
				logger.info("1. Record Grades");
				logger.info("2. Get All Students");
				logger.info("3. Get Students enrolled in a course");
				logger.info("4. Add course to teach");
				logger.info("5. Logout");
				val=sc.nextInt();
				switch(val) {
				case 1:

					// Displays all the courses professor teach
					logger.info("\nBelow are courses that you teach\n");
					professorOperation.fetchTaughtCourseNames();
					logger.info("Enter the course Name for which you want to add student grades");
					courseName=sc.next();
					logger.info("Below are the students registered for this course");
					try {

						// Fetch students who are registered for the course
						professorOperation.getStudentInfo(courseName);
					} catch (InvalidCourseException e) {

						// Give message if the course added is invalid
						logger.error(e.getMessage());
						continue;
					}
					logger.info("Enter the student Name and grades");
					String studentName=sc.next();
					String grades=sc.next();
					try {

						// Record Students grade
						professorOperation.recordGrades(courseName,studentName,grades);
					} catch (StudentNotRegisteredException e) {
						logger.error(e.getMessage());
					}
					continue;
				case 2:

					// Fetch all students with Mr/Ms before their name
					professorOperation.getAllStudents();
					continue;
				case 3:

					// Fetch all courses taught by professor
					logger.info("\nBelow are courses that you teach\n");
					professorOperation.fetchTaughtCourseNames();
					logger.info("Enter the course Name for which you want students info");
					courseName=sc.next();
					try {
						// Fetch student info who are registered in course
						professorOperation.getStudentInfo(courseName);

					} catch (InvalidCourseException e) {
						// If course entered is invalid then give message
						logger.error(e.getMessage());
					} 
					continue;

				case 4:

					// Fetch all the course name
					logger.info("\nChoose course you want to teach\n");
					professorOperation.fetchCourseNames();
					logger.info("Enter the course Name you want to teach");
					courseName=sc.next();
					try {

						// Add the course to be taught by professor
						professorOperation.addCourseToTeach(courseName);

					} catch (InvalidCourseException e) {
						// Give message if course name is invalid
						logger.error(e.getMessage());
					} catch (CourseAlreadyTaughtException e) {
						logger.error(e.getMessage());
					}
					continue;			
				case 5:
					// Logout and close connection with database
					AuthenticationService.logout();
					logger.info("Logout Successfull on "+DateTimeDay.getDateTimeDay());
					logger.info("GOODBYE!");
					return;


				}

			}






		}

		if(role.equalsIgnoreCase("admin")) {	

			// Create admin service object
			AdminServiceInterface adminOperation=new AdminService(username);
			int val;

			// Display functionality for admin
			while(true) {
				logger.info("-----------Menu----------");
				logger.info("1. Create Student");
				logger.info("2. Create professor");
				logger.info("3. Create Admin");
				logger.info("4. Delete Student/Professor");
				logger.info("5. Delete Self Account");
				logger.info("6. Create a course");
				logger.info("7. Update a course");
				logger.info("8. Logout");
				val=sc.nextInt();
				switch(val) {
				case 1:
					// Create the Student
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

						// Give message if Student already exist
						logger.error(e.getMessage());
					}
					continue;
				case 2:

					// Create Professor
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

						// Give error if Professor already exist
						logger.error(e.getMessage());
					}
					continue;			
				case 3:

					// Create new admin
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

						// Give error if admin alrady exist
						logger.error(e.getMessage());
					}
					continue;
				case 4:
					try {

						// Delete Student or professor
						logger.info("Delete Student/Professor?");
						role=sc.next();
						adminOperation.getUsersWithRole(role);
						logger.info("Enter the username of "+role+" you want to delete");
						username=sc.next();
						adminOperation.deleteUser(username,role);
					} catch (UserNotExistForRole e) {

						// If student or professor does not exist give error
						logger.error(e.getMessage());
					} catch (InvalidRoleInput e) {

						// If role entered is invalid then give message
						logger.error(e.getMessage());
					} 
					continue;
				case 5:

					// Take consent if admin surely want to delete the account
					logger.info("You surely want to delete your account? Enter yes/no");
					String ans=sc.next();
					if(ans.contentEquals("yes")||ans.contentEquals("Yes")) {
						adminOperation.deleteSelfAccount();
						logger.info("GOODBYE!");
						return;
					}
					else {
						logger.error("Cannot delete account without your consent!");
						continue;
					}	
				case 6:

					// Create a new course
					try {
						Course c=new Course();
						logger.info("Enter the name of course you want to add");
						c.setCourseName(sc.next());
						adminOperation.checkCourseName(c.getCourseName());
						logger.info("Enter the subject name:");
						c.setSubject(sc.next());
						logger.info("Enter the Fee: ");
						c.setFee(sc.nextInt());

						// Does not set professor name as professor himself will choose course
						c.setProfessorName("");
						adminOperation.createCourse(c);
					}
					catch(CourseAlreadyExist e) {

						// if course already exist the give message
						logger.error(e.getMessage());
					}
					continue;
				case 7:

					// Updates the Course
					try {
						logger.info("\nBelow are the all courses in catalog\n");
						adminOperation.fetchCourse();
						Course c=new Course();
						logger.info("Enter the name of course you want to update");
						String coursename=sc.next();
						adminOperation.checkCourseNameForUpdate(coursename);
						logger.info("Enter the updated name of course");
						c.setCourseName(sc.next());
						if(!coursename.equalsIgnoreCase(c.getCourseName()))
							adminOperation.checkCourseName(c.getCourseName());
						logger.info("Enter the updated subject name:");
						c.setSubject(sc.next());
						logger.info("Enter the updated Fee: ");
						c.setFee(sc.nextInt());
						adminOperation.UpdateCourse(c,coursename);
					}
					catch (CourseNotExist e) {

						// If course not exist the give error
						logger.error(e.getMessage());
					}catch(CourseAlreadyExist e) {

						// if updated course name already exist the give error
						logger.error(e.getMessage());
					}
					continue;

				case 8:

					// Logout and close connection
					AuthenticationService.logout();
					logger.info("Logout Successfull on "+DateTimeDay.getDateTimeDay());
					logger.info("GOODBYE!");
					return;	


				}

			}

		}






	}
}

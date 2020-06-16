package com.flipcard.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipcard.DAO.CourseCatalog;
import com.flipcard.DAO.CourseUpdation;
import com.flipcard.DAO.CourseUpdationInterface;
import com.flipcard.client.Application;
import com.flipcard.exception.CourseAlreadyTaughtException;
import com.flipcard.exception.InvalidCourseException;
import com.flipcard.exception.StudentNotRegisteredException;
import com.flipcard.model.Student;
import com.flipcard.utils.DateTimeDay;


/*
 *  This service is used for the functionalities of professor.
 */
public class ProfessorService implements ProfessorServiceInterface {
	private String username;
	private static Logger logger = Logger.getLogger(Application.class);
	CourseCatalog catalog=new CourseCatalog();
	CourseUpdationInterface courseUpdateObject=new CourseUpdation();
	
	
	public ProfessorService(String username) {
		this.username=username;
	}


	@Override
	/*
	 * Record grades of professor
	 */
	public void recordGrades(String courseName,String studentName,String grades) throws StudentNotRegisteredException {
		
		// Ask dao object to record grades
		String message=courseUpdateObject.recordGrades(courseName,studentName,grades);
		if(message.equalsIgnoreCase("added")) {
			logger.info("Grades Added Successfully on "+DateTimeDay.getDateTime());
			return;
		}
		else {
			
			// If student not registered for the course the cannot record grades
			throw new StudentNotRegisteredException(message);
		}
		
	}


	@Override
	/*
	 * Fetch all course names 
	 */
	public void fetchCourseNames() {
		List<String> coursesNames = new ArrayList<String>();
		
		// Returns list of name
		coursesNames=catalog.fetchCoursesName();
		coursesNames.forEach(System.out::println);
		
	}


	@Override
	/*
	 * Professor add the course to teach
	 */
	public void addCourseToTeach(String courseName) throws CourseAlreadyTaughtException, InvalidCourseException {
		boolean valid=courseUpdateObject.verifyCourse(courseName);
		if(valid) {
			
			// If course is valid then give course adde successfully
			String message=courseUpdateObject.addCourseToTeach(username,courseName);
			if(message.equalsIgnoreCase("added")) {
				logger.info("Course Added Successfully on "+DateTimeDay.getDateTime());
				return;
			}
			else {
				
				// If course already being taught by other professor then raise an error
				throw new CourseAlreadyTaughtException(message);
			}
		}
		else {
			
			// If course is not available to add then give error course not available
			throw new InvalidCourseException("Course Not Available");
		}
		
		
	}

	
	/*
	 * Fetch the course names to be displayed when professor want to select course to teach
	 */
	@Override
	public void fetchTaughtCourseNames() {
		List<String> coursesNames = new ArrayList<String>();
		
		// Get the list of courses names
		coursesNames=courseUpdateObject.fetchTaughtCoursesName(username);
		coursesNames.forEach(System.out::println);
		
	}

	
	/*
	 * Get student information registered for a course
	 */
	@Override
	public void getStudentInfo(String courseName) throws InvalidCourseException {
		
		boolean valid=courseUpdateObject.verifyCourse(courseName);
		if(valid) {
			List<String> studentNames = new ArrayList<String>();
			studentNames=courseUpdateObject.fetchStudentsName(courseName);
			studentNames.forEach(System.out::println);
		}
		else {
			// If the course entered for which professor want information is invalid then raise error
			throw new InvalidCourseException("Course Not Available");
		}
		
		
	}


	@Override
	/*
	 * Print all the student information having Mr/Ms based on gender
	 */
	public void getAllStudents() {
		List<Student> students = new ArrayList<Student>();
		students=courseUpdateObject.getAllStudents();
		
		
		// Stream API used to filter female students 		
		students
		 .stream()
		 .forEach(student->{
			 if(student.getGender().equalsIgnoreCase("Female")) 
			 System.out.println("Ms. "+student.getUserName());
			 else 
			 System.out.println("Mr. "+student.getUserName());			
		 });
		
	
		
	}
	
	
}

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
import com.flipcard.model.Student;
import com.flipcard.utils.DateTimeDay;

public class ProfessorService implements ProfessorServiceInterface {
	private String username;
	private static Logger logger = Logger.getLogger(Application.class);
	CourseCatalog catalog=new CourseCatalog();
	CourseUpdationInterface courseUpdateObject=new CourseUpdation();
	
	
	public ProfessorService(String username) {
		this.username=username;
	}


	@Override
	public void recordGrades() {
		
		
	}


	@Override
	public void fetchCourseNames() {
		List<String> coursesNames = new ArrayList<String>();
		coursesNames=catalog.fetchCoursesName();
		coursesNames.forEach(System.out::println);
		
	}


	@Override
	public void addCourseToTeach(String courseName) throws CourseAlreadyTaughtException, InvalidCourseException {
		boolean valid=courseUpdateObject.verifyCourse(courseName);
		if(valid) {
			String message=courseUpdateObject.addCourseToTeach(username,courseName);
			if(message.contentEquals("added")) {
				logger.info("Course Added Successfully on "+DateTimeDay.getDateTime());
				return;
			}
			else {
				throw new CourseAlreadyTaughtException(message);
			}
		}
		else {
			throw new InvalidCourseException("Course Not Available");
		}
		
		
	}


	@Override
	public void fetchTaughtCourseNames() {
		List<String> coursesNames = new ArrayList<String>();
		coursesNames=courseUpdateObject.fetchTaughtCoursesName(username);
		coursesNames.forEach(System.out::println);
		
	}


	@Override
	public void getStudentInfo(String courseName) throws InvalidCourseException {
		
		boolean valid=courseUpdateObject.verifyCourse(courseName);
		if(valid) {
			List<String> studentNames = new ArrayList<String>();
			studentNames=courseUpdateObject.fetchStudentsName(courseName);
			studentNames.forEach(System.out::println);
		}
		else {
			throw new InvalidCourseException("Course Not Available");
		}
		
		
	}


	@Override
	public void getAllStudents() {
		List<Student> students = new ArrayList<Student>();
		students=courseUpdateObject.getAllStudents();
		students
		 .stream()
		 .filter(student -> student.getGender().contentEquals("Female"))
		 .forEach(student->System.out.println("Ms. "+student.getUserName()));
		
		students
		 .stream()
		 .filter(student -> student.getGender().contentEquals("Male"))
		 .forEach(student->System.out.println("Mr. "+student.getUserName()));
		
		
		//courses.forEach(course->System.out.println("\n Course Name: "+course.getCourseName()+"\n Number of students: "+course.getNumberOfStudents()+"\n Professor Name: "+course.getProfessorName()+"\n Fee "+course.getFee()+"\n Subject "+course.getSubject()+"\n\n"));
		
	}
	
	
}

package com.flipcard.client;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.flipcard.exception.CourseAlreadyTaughtException;
import com.flipcard.exception.InvalidCourseException;
import com.flipcard.model.Course;

import com.flipcard.model.Student;

import com.flipcard.service.ProfessorService;
import com.flipcard.service.ProfessorServiceInterface;
import com.flipcard.service.StudentService;
import com.flipcard.service.StudentServiceInterface;

@Path("/professor")

public class professorController {

	// Creating logger object to log the messages
	private static Logger logger = Logger.getLogger(UserController.class);

	/*
	 * the method is used to get student details It is using the @get annotation
	 */
	@GET
	@Path("/studentDetails")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudentDetails() {

		// Creating professor service object
		ProfessorServiceInterface professorServiceObject = new ProfessorService();
		List<Student> students = professorServiceObject.getAllStudentsDetails();
		return students;
	}

	/*
	 * the method is used to get course details It is using the @get annotation
	 */

	@GET
	@Path("/courseDetails")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getAllCoursesDetails() {

		// Creating student service object
		StudentServiceInterface studentServiceObject = new StudentService();
		List<Course> courses = studentServiceObject.getAllcoursesDetails();
		return courses;
	}

	/*
	 * the method is used by professor to add course to teach It is using
	 * the @post annotation
	 */

	@POST
	@Path("/add-course-to-teach/{userName}/{courseName}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response add_course_to_teach(@PathParam("userName") String userName,
			@PathParam("courseName") String courseName) {
		String result = null;

		// Creating professor service object
		ProfessorServiceInterface professorOperation = new ProfessorService(userName);
		try {

			// Add the course to be taught by professor
			professorOperation.addCourseToTeach(courseName);
			result = "added";

		} catch (InvalidCourseException e) {
			// Give message if course name is invalid
			logger.error(e.getMessage());
			result = e.getMessage();
		} catch (CourseAlreadyTaughtException e) {
			logger.error(e.getMessage());
			result = e.getMessage();
		}
		return Response.status(201).entity(result).build();
	}

}

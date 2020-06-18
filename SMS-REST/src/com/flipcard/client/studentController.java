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

import com.flipcard.exception.AlreadyRegisteredException;
import com.flipcard.exception.InvalidCourseException;
import com.flipcard.model.Course;
import com.flipcard.service.StudentService;
import com.flipcard.service.StudentServiceInterface;

@Path("/student")

public class studentController {

	// Creating logger object to log the messages
	private static Logger logger = Logger.getLogger(UserController.class);

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
	 * the method is used by authenticated student to register for a course It
	 * is using the @post annotation
	 */

	@POST
	@Path("/register-for-course/{userName}/{courseName}/{paymentMode}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response add_course_to_teach(@PathParam("userName") String userName,
			@PathParam("courseName") String courseName, @PathParam("paymentMode") int paymentMode) {

		String result = null;

		// Creating student service object
		StudentServiceInterface studentOperation = new StudentService(userName);
		try {
			// Check if course is valid or not if valid then register the
			// student for it
			studentOperation.addCourse(courseName, paymentMode);
			result = "registered for course";

		} catch (InvalidCourseException e) {

			// If course invalid then give message
			logger.error(e.getMessage());
			result = e.getMessage();

		} catch (AlreadyRegisteredException e) {

			// If student is already registered in course then give message
			logger.error(e.getMessage());
			result = e.getMessage();
		}
		return Response.status(201).entity(result).build();
	}

}

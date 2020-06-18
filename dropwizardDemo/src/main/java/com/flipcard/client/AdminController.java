package com.flipcard.client;

import java.util.List;

import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.crypto.URIReferenceException;

import org.apache.log4j.Logger;

import com.flipcard.exception.UserNotExistForRole;
import com.flipcard.model.Course;
import com.flipcard.model.Professor;
import com.flipcard.model.Student;
import com.flipcard.service.AdminService;
import com.flipcard.service.AdminServiceInterface;
import com.flipcard.service.ProfessorService;
import com.flipcard.service.ProfessorServiceInterface;
import com.flipcard.service.StudentService;
import com.flipcard.service.StudentServiceInterface;

@Path("/admin")

public class AdminController {

	// Creating logger object to log the messages
	private static Logger logger = Logger.getLogger(UserController.class);

	private final Validator validator;

	public AdminController(Validator validator) {
		this.validator = validator;
	}

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
	 * the method is used to get professor details It is using the @get
	 * annotation
	 */
	@GET
	@Path("/allProfessorsDetails")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Professor> getprofessorDetails() {

		// Creating student service object
		StudentServiceInterface studentServiceObject = new StudentService();
		List<Professor> professors = studentServiceObject.getAllProfessorsDetails();
		return professors;
	}

	/*
	 * the method is used by authenticated administrator to delete student or
	 * professor It is using the @delete annotation
	 */

	@DELETE
	@Path("/delete/{username}/{role}")
	public Response deleteCustomer(@PathParam("username") String username, @PathParam("role") String role)
			throws URIReferenceException {
		String result = null;

		// Creating administrator service object
		AdminServiceInterface adminOperation = new AdminService(username);

		try {
			adminOperation.deleteUser(username, role);
			result = "deleted";
		} catch (UserNotExistForRole e) {
			// If student or professor does not exist give error
			logger.error(e.getMessage());
			result = e.getMessage();
		}

		return Response.status(201).entity(result).build();
	}

	/*
	 * the method is used by authenticated admin to update a course It is using
	 * the @put annotation
	 */

	@PUT
	@Path("/updateCourse/{coursename}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCustomer(Course course, @PathParam("coursename") String coursename) {

		// creating administrator service object
		AdminServiceInterface adminOperation = new AdminService();

		adminOperation.UpdateCourse(course, coursename);

		return Response.status(201).entity("updated").build();
	}

}

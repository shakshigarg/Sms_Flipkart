package com.flipcard.client;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.flipcard.exception.InvalidAuthenticationException;
import com.flipcard.service.AuthenticationService;

@Path("/user")

public class UserController {

	/*
	 * the method is used to check where the user name and password is valid or
	 * not It is using the @post annotation
	 */

	@POST
	@Path("/login/{username}/{password}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@PathParam("username") String username, @PathParam("password") String password) {
		String role = null;
		String result = null;
		try {
			// Check identity if user is authenticated
			role = AuthenticationService.checkIdentity(username, password);
			result = role;
		} catch (InvalidAuthenticationException e) {
			// if invalid user the give invalid username and password
			result = e.getMessage();
		}
		return Response.status(201).entity(result).build();

	}

}

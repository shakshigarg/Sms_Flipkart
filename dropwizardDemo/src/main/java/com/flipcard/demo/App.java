package com.flipcard.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flipcard.client.UserController;
import com.flipcard.client.AdminController;
import com.flipcard.client.ProfessorController;
import com.flipcard.client.StudentController;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Hello world!
 *
 */
public class App extends Application<Configuration> {
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
	 
    @Override
    public void initialize(Bootstrap<Configuration> b) {
    }
 
    @Override
    public void run(Configuration c, Environment e) throws Exception {
        LOGGER.info("Registering REST resources");
        e.jersey().register(new UserController(e.getValidator()));
        e.jersey().register(new StudentController(e.getValidator()));
        e.jersey().register(new ProfessorController(e.getValidator()));
        e.jersey().register(new AdminController(e.getValidator()));
    }
 
    public static void main(String[] args) throws Exception {
        new App().run(args);
    }
}

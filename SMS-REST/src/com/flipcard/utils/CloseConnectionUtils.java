package com.flipcard.utils;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.flipcard.client.UserController;
/*
 * Utils class designed to close the connection
 */
public class CloseConnectionUtils {
	
	// Get logger object to log the messages
	private static Logger logger = Logger.getLogger(UserController.class);	
	public static void closeConnection(Connection conn) {
		try {
			// Close the connection
			conn.close();
		} catch (SQLException e) {
			// Give error if connection cannot be closed
			logger.error("Cannot close connection,error occured "+e.getMessage());
		}
	}
}

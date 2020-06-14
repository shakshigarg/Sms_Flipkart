package com.flipcard.utils;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.flipcard.client.Application;

public class CloseConnectionUtils {
	private static Logger logger = Logger.getLogger(Application.class);	
	public static void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			logger.info("Cannot close connection,error occured "+e.getMessage());
		}
	}
}

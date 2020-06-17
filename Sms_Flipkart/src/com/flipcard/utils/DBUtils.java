package com.flipcard.utils;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

/*
 * The utils class is used create connection with database
 * Have the static method getConnection which returns connection with database
 */
public class DBUtils {
	
	// Initialize connection variable with null
	private static Connection connection = null;
	
	// logger object to log the messages
	private static Logger logger=Logger.getLogger(DBUtils.class);
	
	
	/*
	 * establish the connection
	 */
	public static Connection getConnection() {
		
        if (connection != null)
            return connection;
        else {
            try {
            	Properties prop = new Properties();
            	
            	// Take properties from config file
                InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("configure.properties");
                prop.load(inputStream);
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");
                
                Class.forName(driver);
                
                //Establishing the connection
                connection = DriverManager.getConnection(url, user, password);
                
                logger.debug("Connection established!");
                
            } catch (ClassNotFoundException e) {
            	logger.error("Error occured "+e.getMessage());
            } catch (SQLException e) {
            	// If some error in query the give error
            	logger.error("Error occured "+e.getMessage());
            } 
            catch (FileNotFoundException e) {
            	// Give error if file not found
            	logger.error("Error occured "+e.getMessage());
            } catch (IOException e) {
            	logger.error("Error occured "+e.getMessage());
            }
            return connection;
        }

    }


}



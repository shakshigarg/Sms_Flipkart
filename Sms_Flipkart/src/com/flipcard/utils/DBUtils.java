package com.flipcard.utils;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;


public class DBUtils {
	
	private static Connection connection = null;
	private static Logger logger=Logger.getLogger(DBUtils.class);
	
	public static Connection getConnection() {
		
        if (connection != null)
            return connection;
        else {
            try {
            	Properties prop = new Properties();
            	
            	// Take properties from config file
                InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("config.properties");
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
            	logger.error("Error occured");
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } 
            catch (FileNotFoundException e) {
            	logger.error("Error occured");
                e.printStackTrace();
            } catch (IOException e) {
            	logger.error("Error occured");
                e.printStackTrace();
            }
            return connection;
        }

    }


}



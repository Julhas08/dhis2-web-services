/**
 * @author Julhas Sujan, MSH
 * @Version: LMIS-V1.1
 */
package com.jdbcconnection.controller;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
/**
 * This class is using for Database connection and global table definations
 */
public class CreateDbConnection {
	
 // Global table initialization
 // Logger Set
	final static Logger logger = Logger.getLogger(LoggerTest.class);
	public Connection getConnection() {
		
		Connection c = null;
	      try {
	    	  
 // Properties file load from resources folder	
	  		InputStream inputStream;
	  		Properties prop = new Properties();
	  		String propFileName = "config.properties";
	  		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

	  		if (inputStream != null) {
	  			prop.load(inputStream);
	  		} else {
	  			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
	  		}

 // Get the property value
 	  		String url = prop.getProperty("url");
	  		String driver = prop.getProperty("driver");
	  		String user = prop.getProperty("user");
	  	    String pass = prop.getProperty("pass");
	  	    

 // Set db property data   
	         Class.forName(driver);
	         c = DriverManager.getConnection("jdbc:postgresql:"+url+"", user,pass);
	         
	      }catch (SQLException se) {
	    	  logger.error("Sorry, database connections error! Please check the following message: "+se.getClass().getName()+": "+se.getMessage(), se);	         
	      }catch(Exception e){
	    	  logger.error("Sorry, database connections error! Please check the following message: ", e);
	    	  System.err.println(e.getClass().getName()+": "+e.getMessage());
	      }
		return c;   
	}

}

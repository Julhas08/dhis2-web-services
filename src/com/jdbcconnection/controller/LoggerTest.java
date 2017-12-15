package com.jdbcconnection.controller;

import org.apache.log4j.Logger;

public class LoggerTest {

	final static Logger logger = Logger.getLogger(LoggerTest.class);

	public static void main(String[] args) {
		
		LoggerTest obj = new LoggerTest();
		
		try{
			if(logger.isDebugEnabled()){
				obj.divide();
			}
			obj.divide();
			
		}catch(ArithmeticException ex){
			logger.error("Sorry, something wrong!", ex);
		}
		
		
	}
	
	private void divide(){

	}

}

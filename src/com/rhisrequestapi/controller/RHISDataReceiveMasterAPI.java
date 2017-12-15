package com.rhisrequestapi.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.dhis2datasend.controller.DHIS2DataSendMasterApi;
import com.jdbcconnection.controller.CreateDbConnection;

/**
 * @author Julhas Sujan(julhaspustcse@gmail.com)
 * @version V1.0
 * @since 2017
 *
 */
public class RHISDataReceiveMasterAPI{		
	 // RHIS APi Root key
	private final String RHIS_API_ROOT = "http://mchdrhis.icddrb.org:8080/eMISAPI/api/ReportsView/";
    public int receiveRHISAPIData(String moduleName, String districtId,String upazilaId,String unionId,String year,String month) throws IOException, ParseException {
    	// DHIS2 Period Making
    	String period=year.concat(month);
    	Connection connDb = null;
        DHIS2DataSendMasterApi masterApi=new DHIS2DataSendMasterApi();
		URL url = new URL(""+RHIS_API_ROOT+"HAreportQP?districtId="+districtId+"&upazilaId="+upazilaId+"&unionId="+unionId+"&year="+year+"&month="+month+"");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
		String line;
	    StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
            sb.append(line);
        }
		
		JSONParser parser = new JSONParser();
		String jsonString=sb.toString();
 		Object obj = parser.parse(jsonString);
 		JSONObject jsonObject = (JSONObject) obj; 		
        JSONArray data = (JSONArray)jsonObject.get("HAReport");
        // Get DB Connection		
     		/*try{
     			CreateDbConnection o = new CreateDbConnection();
     			connDb = o.getConnection();
     			connDb.setAutoCommit(false);
     		}catch (Exception  e) {
     	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
             }
     		String getElementSQL = "SELECT dhis2_element from \"dhis2_haElementsMap\"";
			String dhis2_element=null;
			try {
				Statement getRhisDataStmt = connDb.createStatement();
			  	ResultSet rhisMapElementIdResult = getRhisDataStmt.executeQuery(getElementSQL);
					
				while (rhisMapElementIdResult.next()){
					
					
				}*/
        String orgUnit="Wvelc2qAo7m";
		int responseCode=masterApi.sendDataToDHIS2(data,period,orgUnit);
		return responseCode; 
	}
}    

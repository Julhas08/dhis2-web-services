/**
 * 
 */
package com.dhis2datasend.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.dhis2mapping.model.HaElementsMap;
import com.jdbcconnection.controller.CreateDbConnection;

/**
 * @author Julhas Sujan(julhaspustcse@gmail.com)
 * @version V1.0
 * @since 2017
 *
 */
public class DHIS2DataSendMasterApi {

	private final String USER_AGENT = "Mozilla/5.0";

	@SuppressWarnings("restriction")
	public int sendDataToDHIS2(JSONArray arrayRHISHaData,String period,String orgUnit) throws IOException, ParseException{		
	/**
	 * Database connections
	 */		
	Connection connDb = null;
	int responseCode=0;
	// Get DB Connection		
		try{
			CreateDbConnection o = new CreateDbConnection();
			connDb = o.getConnection();
			connDb.setAutoCommit(false);
		}catch (Exception  e) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
		
	/**
	 * DHIS2 Data Send Parameter
	 * 
	 * */
		
	String username = "test";
	String password = "test";
	String userPassword = username + ":" + password;
	@SuppressWarnings("restriction")
	String encoding = new sun.misc.BASE64Encoder().encode(userPassword.getBytes());
	
	/**
	 *Receive RHIS HA Module API data 
	 */
	 JSONObject obj;
	 int lengthArr=arrayRHISHaData.size();
	 HaElementsMap elementsMap=new HaElementsMap();
	 StringBuffer response = new StringBuffer();
	 Map haModuleMap=new HashMap<String, String>();
	 
	 for (int i = 0; i < lengthArr; i++) {
		 
		 JSONParser parser = new JSONParser();
		 try {
			
			 
			obj = (JSONObject) parser.parse(arrayRHISHaData.get(i).toString()); 
			haModuleMap.put("avvtj7FsHVl", obj.get("No_of_pregnant_women_New").toString());
			haModuleMap.put("Wy2lEoDzcCp", obj.get("r_preg_woman_old_w1").toString());
			haModuleMap.put("yckZeEVj124", obj.get("r_preg_service_anc_visit1_w1").toString());
			haModuleMap.put("EBaW0sJlmFb", obj.get("r_preg_service_anc_visit2_w1").toString());
			haModuleMap.put("jIssvfzqBVq", obj.get("r_preg_service_anc_visit3_w1").toString());
			haModuleMap.put("EFWRXafC7Xt", obj.get("r_preg_service_anc_visit4_w1").toString());
			haModuleMap.put("k5ByAS9Yh1A", obj.get("r_preg_iron_folic_acid_anc__w1").toString());
			haModuleMap.put("NIVpgCX0yJu", obj.get("r_delivery_facility_w1").toString());
			haModuleMap.put("Gc3LKjQ6Wik", obj.get("r_live_birth_w1").toString());
			haModuleMap.put("RUnkaLRqabj", obj.get("r_birth_less_weight_w1").toString());
			haModuleMap.put("TElUmHahCWp", obj.get("r_death_birth_w1").toString());
			haModuleMap.put("BLrbMSjQCbc", obj.get("r_pnc_visit1_w1").toString());
			haModuleMap.put("wsJVqtBWXoH", obj.get("r_total_death_0_7days_w1").toString());
			haModuleMap.put("vKzcZ9QX0ZR", obj.get("r_total_death_8_28days_w1").toString());
			haModuleMap.put("VwYsxbvBhKu", obj.get("r_total_death_29d_bellow_1year_w1").toString());
			haModuleMap.put("nfR2B5fll1F", obj.get("r_total_death_1y_bellow_5year_w1").toString());
			haModuleMap.put("mNJ3uVvG6Ib", obj.get("r_total_death_maternal_w1").toString());
			haModuleMap.put("T4h49pMwyHg", obj.get("r_total_death_oth_all_w1").toString());
			
			/*elementsMap.setNo_of_pregnant_women_New(obj.get("No_of_pregnant_women_New").toString());
			elementsMap.setR_preg_woman_old_w1(obj.get("r_preg_woman_old_w1").toString());
			elementsMap.setR_preg_service_anc_visit1_w1(obj.get("r_preg_service_anc_visit1_w1").toString());
			elementsMap.setR_preg_service_anc_visit2_w1(obj.get("r_preg_service_anc_visit2_w1").toString());
			elementsMap.setR_preg_service_anc_visit3_w1(obj.get("r_preg_service_anc_visit3_w1").toString());
			elementsMap.setR_preg_service_anc_visit4_w1(obj.get("r_preg_service_anc_visit4_w1").toString());
			elementsMap.setR_preg_iron_folic_acid_anc__w1(obj.get("r_preg_iron_folic_acid_anc__w1").toString());
			elementsMap.setR_delivery_facility_w1(obj.get("r_delivery_facility_w1").toString());
			elementsMap.setR_live_birth_w1(obj.get("r_live_birth_w1").toString());
			elementsMap.setR_birth_less_weight_w1(obj.get("r_birth_less_weight_w1").toString());
			elementsMap.setR_death_birth_w1(obj.get("r_death_birth_w1").toString());
			elementsMap.setR_pnc_visit1_w1(obj.get("r_pnc_visit1_w1").toString());
			elementsMap.setR_total_death_0_7days_w1(obj.get("r_total_death_0_7days_w1").toString());
			elementsMap.setR_total_death_8_28days_w1(obj.get("r_total_death_8_28days_w1").toString());
			elementsMap.setR_total_death_29d_bellow_1year_w1(obj.get("r_total_death_29d_bellow_1year_w1").toString());
			elementsMap.setR_total_death_1y_bellow_5year_w1(obj.get("r_total_death_1y_bellow_5year_w1").toString());
			elementsMap.setR_total_death_maternal_w1(obj.get("r_total_death_maternal_w1").toString());
			elementsMap.setR_total_death_oth_all_w1(obj.get("r_total_death_oth_all_w1").toString());*/
			
		 } catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		 /*
		  * Extract DHIS2 element id from table "dhis2_haElementsMap"	and data send to DHIS2	  
		 */
	  		String getElementSQL = "SELECT dhis2_element,external_datauid from \"dhis2_haElementsMap\"";
			String dhis2_element=null;
			try {
				//Statement getRhisDataStmt = connDb.createStatement();
			  //	ResultSet rhisMapElementIdResult = getRhisDataStmt.executeQuery(getElementSQL);
					
				//while (rhisMapElementIdResult.next()){
					
					//dhis2_element = rhisMapElementIdResult.getString("dhis2_element");
				Iterator it = haModuleMap.entrySet().iterator();
			    while (it.hasNext()) {
			    	
			        Map.Entry pair = (Map.Entry)it.next();
			        dhis2_element=pair.getKey().toString();
			        String rhisHaValue=pair.getValue().toString();			       
			        it.remove(); // avoids a ConcurrentModificationException

					URL url = new URL("http://103.247.238.67:8080/dhismohfw/api/dataValues?de="+dhis2_element+"&pe="+period+"&ou="+orgUnit+"");
					
					HttpURLConnection con = (HttpURLConnection) url.openConnection();
					//add reuqest header
					con.setDoOutput(true);
					con.setRequestProperty("Authorization", "Basic " + encoding);
					con.setRequestMethod("POST");
					con.setRequestProperty("User-Agent", USER_AGENT);
					con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

					String value = "value="+rhisHaValue+"";
					// Send post request
					con.setDoOutput(true);
					DataOutputStream wr = new DataOutputStream(con.getOutputStream());
					wr.writeBytes(value);
					wr.flush();
					wr.close();
					responseCode = con.getResponseCode();
					/*System.out.println("\nSending 'POST' request to URL : " + url);
					System.out.println("Post parameters : " + value);
					System.out.println("Response Code : " + responseCode);
					 */
					BufferedReader in = new BufferedReader(
					        new InputStreamReader(con.getInputStream()));
					String inputLine;

					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					in.close();
	             }
		
				
			} catch (NullPointerException e) {
				throw new RuntimeException(e);

			}finally {
				if (connDb != null) {
					try {
						connDb.close();
					} catch (SQLException e) {}
				}
			} 	
	 
	 }
	return responseCode;	 
	}
}

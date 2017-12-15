/**
 * 
 */
package com.dhis2datasetapi.model;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

/**
 * @author Julhas
 * @version V1.1
 */
public interface DHIS2DatasetApiDao {

	public String sendDataToDHIS2(JSONArray arrayRHISHaDat) throws IOException, ParseException;

}

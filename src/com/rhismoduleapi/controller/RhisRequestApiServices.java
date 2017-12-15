/**
 * 
 */
package com.rhismoduleapi.controller;

import java.io.IOException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.json.simple.parser.ParseException;
import com.datavalidation.controller.RHISApiDataValidations;
import com.rhisrequestapi.controller.RHISDataReceiveMasterAPI;

/**
 * @author Julhas Sujan(julhaspustcse@gmail.com)
 * @version V1.0
 * @since 2017
 *
 */
@Path("/rhistodhis2")
public class RhisRequestApiServices {

	/**
	 * @param moduleName, districtId, upazilaId, unionId, month, year
	 * @return DHIS2 data sent response code
	 * @url http://localhost:8082/rhiswebservices/rest/rhistodhis2/rhisModule?moduleName=HA&districtId=93&upazilaId=19&unionId=13&year=2017&month=01
	 */
	@GET
	@Path("/rhisModule")
	@Produces(MediaType.APPLICATION_JSON)
	public int getTrackInJSON(@PathParam("rhisModule") String rhisModule, @QueryParam("moduleName") String moduleName, @QueryParam("districtId") String districtId,
			@QueryParam("upazilaId") String upazilaId, @QueryParam("unionId") String unionId,@QueryParam("year") String year, 
			@QueryParam("month") String month){
		int responseCode=0;
		RHISDataReceiveMasterAPI rhisApi=new RHISDataReceiveMasterAPI();
		RHISApiDataValidations validate=new RHISApiDataValidations();

		try {
			
			if(validate.validateModuleName(moduleName) && validate.validateDistrictId(districtId) && validate.validateUpazila(upazilaId)
					&& validate.validateUnion(unionId) && validate.validateYear(year) && validate.validateMonth(month)){
				responseCode=rhisApi.receiveRHISAPIData(moduleName, districtId, upazilaId, unionId, year, month);
				System.out.println(responseCode);
			} else {
				
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseCode;
	}

}

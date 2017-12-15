package com.datavalidation.controller;

public class RHISApiDataValidations {

	public boolean validateModuleName(String moduleName){
		
		if(moduleName.isEmpty()){
			return false;
		} else {
			return true;
		}
	}
	
	
	public boolean validateDistrictId(String districtId){
			
			if(districtId.isEmpty()){
				return false;
			} else {
				return true;
			}
		}
	
	
	public boolean validateUpazila(String upazilaId){
		
		if(upazilaId.isEmpty()){
			return false;
		} else {
			return true;
		}
	}
	
	
	public boolean validateUnion(String unionId){
		
		if(unionId.isEmpty()){
			return false;
		} else {
			return true;
		}
	}
	
	
	public boolean validateYear(String year){
		
		if(year.isEmpty()){
			return false;
		} else {
			return true;
		}
	}
	
	public boolean validateMonth(String month){
			
			if(month.isEmpty()){
				return false;
			} else {
				return true;
			}
		}

}

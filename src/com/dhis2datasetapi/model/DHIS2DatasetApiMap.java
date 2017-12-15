package com.dhis2datasetapi.model;

public class DHIS2DatasetApiMap {
	
	private int No_of_pregnant_women_New;
	private int r_preg_woman_old_w1;
	
	public DHIS2DatasetApiMap(int No_of_pregnant_women_New, int r_preg_woman_old_w1){
		this.No_of_pregnant_women_New=No_of_pregnant_women_New;
		this.r_preg_woman_old_w1=r_preg_woman_old_w1;
	}
	
	
	/**
	 * @return the no_of_pregnant_women_New
	 */
	public int getNo_of_pregnant_women_New() {
		return No_of_pregnant_women_New;
	}

	/**
	 * @param no_of_pregnant_women_New the no_of_pregnant_women_New to set
	 */
	public void setNo_of_pregnant_women_New(int no_of_pregnant_women_New) {
		No_of_pregnant_women_New = no_of_pregnant_women_New;
	}

	/**
	 * @return the r_preg_woman_old_w1
	 */
	public int getR_preg_woman_old_w1() {
		return r_preg_woman_old_w1;
	}

	/**
	 * @param r_preg_woman_old_w1 the r_preg_woman_old_w1 to set
	 */
	public void setR_preg_woman_old_w1(int r_preg_woman_old_w1) {
		this.r_preg_woman_old_w1 = r_preg_woman_old_w1;
	}	
	
}

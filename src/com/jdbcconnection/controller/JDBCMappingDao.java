package com.jdbcconnection.controller;

import javax.sql.DataSource;

import com.dhis2mapping.model.HaElementsMap;
import com.dhis2mapping.model.HaElementsMapDAO;

public class JDBCMappingDao implements HaElementsMapDAO{

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void createNewMap(HaElementsMap haMap) {
		
		
	}

}

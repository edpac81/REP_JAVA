package com.model;

public class CSVInput {

	private String csvName;
	private String daoName;
	
	public CSVInput(String csvName, String daoName) {
		super();
		this.csvName = csvName;
		this.daoName = daoName;
	}

	public String getCsvName() {
		return csvName;
	}

	public String getDaoName() {
		return daoName;
	}

	public void setCsvName(String csvName) {
		this.csvName = csvName;
	}

	public void setDaoName(String daoName) {
		this.daoName = daoName;
	}
	
}

package com.tester.model;

public class TestCase {
	
	private String caseId;
	private String escenaryId;
	private RequestParameters serviceParmeters;
	private String responseFilePath;
	
	public TestCase(String caseId, String escenaryId, RequestParameters serviceParmeters) {
		super();
		this.caseId = caseId;
		this.escenaryId = escenaryId;
		this.serviceParmeters = serviceParmeters;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getEscenaryId() {
		return escenaryId;
	}

	public void setEscenaryId(String escenaryId) {
		this.escenaryId = escenaryId;
	}

	public RequestParameters getServiceParmeters() {
		return serviceParmeters;
	}

	public void setServiceParmeters(RequestParameters serviceParmeters) {
		this.serviceParmeters = serviceParmeters;
	}

	public String getResponseFilePath() {
		return responseFilePath;
	}

	public void setResponseFilePath(String responseFilePath) {
		this.responseFilePath = responseFilePath;
	}
	
}
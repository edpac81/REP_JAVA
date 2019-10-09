package com.tester.controller;

import java.io.File;
import java.io.FileOutputStream;

import com.tester.config.Configuration;
import com.tester.model.TestCase;
import com.tester.mq.MQConnector;

public class ResponseProcess {

	private String responseMessage;
	private MQConnector mqConnector;
	private boolean writeFileResponse;
	private TestCase testCase;

	public ResponseProcess() {
		this(null, false, null);
	}

	public ResponseProcess(MQConnector mqConnector, boolean writeFileResponse, TestCase testCase) {
		super();
		this.mqConnector = mqConnector;
		this.writeFileResponse = writeFileResponse;
		this.testCase = testCase;
	}

	public void execute() throws Exception {
		this.responseMessage = this.mqConnector.getMessage();
		
		if(this.writeFileResponse){
			writeFileResponse();
		}
	}

	private void writeFileResponse() throws Exception{
		// crear nombre del archivo
		String folder = Configuration.getValue("property.general.outputFolder");
		String fileExtension = Configuration.getValue("property.general.responseFileExt");
		
		// crear archivo
		File responseFile = new File(folder + "/RESPONSE_" +
				"CASE-" + this.testCase.getCaseId() + "_" +
				"ESCENARY-" + this.testCase.getEscenaryId() + 
				"." + fileExtension	);
		// crea flujo de salida para archivo
		FileOutputStream fos = new FileOutputStream(responseFile);
		
		fos.write(this.responseMessage.getBytes());
		this.testCase.setResponseFilePath(responseFile.getName());
		fos.flush();
		fos.close();
	}
	
	public String getResponseMessage() {
		return responseMessage;
	}

	public void setMQConnector(MQConnector mqConnector) {
		this.mqConnector = mqConnector;
	}

}

package com.tester.controller;

import org.w3c.dom.Document;

import com.tester.config.Configuration;
import com.tester.model.RequestKeys;
import com.tester.model.TestCase;
import com.tester.mq.MQConnector;
import com.tester.xml.ServiceRequestBuilder;

public class TestCaseController {
	
	private TestCase testCase;
	private RequestKeys requestKeys;
	private MQConnector mqConnector;
	private Document xmlRequestDocument;
	
	public TestCaseController(MQConnector mqConnector, TestCase testCase, RequestKeys requestKeys, Document xmlRequestDocument) {
		super();
		this.testCase = testCase;
		this.requestKeys = requestKeys;
		this.xmlRequestDocument = xmlRequestDocument;
		this.mqConnector = mqConnector;
	}
	
	public void runRequester() throws Exception{
		
		// si contiene transferencia de archivo
		if (Configuration.getValue("property.fileTransfer.flag").equals("1")){
			
			String ftTagName = Configuration.getValue("property.fileTransfer.fileTagName");
			String fileName = (String)this.testCase.getServiceParmeters().get(ftTagName);			
			
			FileTransferenceProcess ftProcess = new FileTransferenceProcess(this.mqConnector, fileName);
			ftProcess.execute();
			
			this.testCase.getServiceParmeters().put(ftTagName, ftProcess.getFileMessageID());
			this.testCase.getServiceParmeters().put(Configuration.getValue("property.fileTransfer.fileSizeTagName"),
					String.valueOf(ftProcess.getBytesTranfered()));
		}
		
		// crear request
		ServiceRequestBuilder requestBuilder = new ServiceRequestBuilder(this.xmlRequestDocument, this.requestKeys, this.testCase.getServiceParmeters());
		requestBuilder.builXmlRequest();
		
		//ejecutar requester
		RequestProcess requestProcess = new RequestProcess(requestBuilder.getXmlRequest(), this.mqConnector);
		requestProcess.execute();
		
		//procesar respuesta
		ResponseProcess responseProcess = new ResponseProcess(this.mqConnector,true,this.testCase);
		responseProcess.execute();
		
	}
}

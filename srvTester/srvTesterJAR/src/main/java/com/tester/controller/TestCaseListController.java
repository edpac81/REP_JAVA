package com.tester.controller;

import java.io.File;

import org.apache.poi.ss.usermodel.Workbook;
import org.w3c.dom.Document;

import com.tester.config.Configuration;
import com.tester.excel.ExcelFileReader;
import com.tester.excel.ResultWriter;
import com.tester.excel.WorkbookProcessor;
import com.tester.model.RequestKeys;
import com.tester.model.TestCase;
import com.tester.mq.MQConfigurationLoader;
import com.tester.mq.MQConnector;
import com.tester.utils.TestCaseList;
import com.tester.xml.ServiceRequestLoader;

public class TestCaseListController {

	private File xmlTemplate;
	private File xlsTestCases;
	private TestCaseList testCases;
	private RequestKeys requestKeys;
	private ServiceRequestLoader serviceRequestLoader;
	private MQConnector mqConnector;
	
	public TestCaseListController(File xmlTemplate, File xlsTestCases) {
		super();
		this.xmlTemplate = xmlTemplate;
		this.xlsTestCases = xlsTestCases;
		
		//Carga configuración
		Configuration.load();
	}
	
	public void execute() throws Exception{
		loadExcelData();
		
		loadRequestTemplate();
		
		openMqConnections();
		
		executeRequestControl();
		
		this.mqConnector.closeWorkQueues();
		
		writeResultExcelReport();
		
	}

	private void loadExcelData() throws Exception {
		ExcelFileReader xlsFileReader = new ExcelFileReader(this.xlsTestCases);
		Workbook workbook = xlsFileReader.getWorkBook();
		WorkbookProcessor workbookProcessor = new WorkbookProcessor(workbook);
		workbookProcessor.processWorkbook();
		this.testCases = workbookProcessor.getTestCaseSet();
		this.requestKeys = workbookProcessor.getRequestKeys();
	}

	private void loadRequestTemplate() throws Exception {
		this.serviceRequestLoader = new ServiceRequestLoader(this.xmlTemplate);
		this.serviceRequestLoader.openXmlFile();		
	}

	private void openMqConnections() throws Exception{
		MQConfigurationLoader mqConfLoader = new MQConfigurationLoader();
		this.mqConnector = new MQConnector(mqConfLoader.getMqConfig());
		this.mqConnector.openMQManager();
		this.mqConnector.openWorkQueues();
	}
	
	private void executeRequestControl() throws Exception{
		for (int i = 0; i < testCases.size(); i++) {
			TestCase testCase = (TestCase)this.testCases.get(i);
			Document xmlRequestDocument = this.serviceRequestLoader.getXmlDoc();
			TestCaseController requestController = new TestCaseController(this.mqConnector, testCase, requestKeys, xmlRequestDocument );
			requestController.runRequester();
		}
	}
	
	private void writeResultExcelReport(){
		ResultWriter rw = new ResultWriter(this.testCases, this.requestKeys);
		rw.writeResult();
	}
}

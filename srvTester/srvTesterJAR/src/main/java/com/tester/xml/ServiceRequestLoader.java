package com.tester.xml;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

public class ServiceRequestLoader {
	private File xmlFile;
	private Document xmlDoc;

	public ServiceRequestLoader(File xmlFile) {
		this.xmlFile = xmlFile;
	}

	public void openXmlFile() throws Exception {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		this.xmlDoc = dBuilder.parse(this.xmlFile);
	}

	public Document getXmlDoc() {
		return this.xmlDoc;
	}
}
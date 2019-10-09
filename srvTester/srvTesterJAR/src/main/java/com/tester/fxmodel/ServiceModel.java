package com.tester.fxmodel;

import java.io.File;

public class ServiceModel {

	private String serviceCode;
	private File serviceDirectory;
	private File xmlRequest;
	private File requestKeyData;

	public ServiceModel(String serviceCode) throws Exception {
		super();
		this.serviceCode = serviceCode;
		if(!serviceCode.equals("")){
			serviceDirectory = new File(".\\services\\"+serviceCode);
			if(serviceDirectory.isDirectory()){
				loadXmlRequest();
				loadServiceKey();
			}else{
				throw new Exception("Servicio no encontrado.");
			}
		}
	}

	private void loadServiceKey() throws Exception{
		this.xmlRequest = new File(this.serviceDirectory.getAbsolutePath()+"\\request.xml");
	}

	private void loadXmlRequest() throws Exception{
		try{
			this.requestKeyData = new File(this.serviceDirectory.getAbsolutePath()+"\\requestData.xlsx");
		}catch(Exception e){
			this.requestKeyData = new File(this.serviceDirectory.getAbsolutePath()+"\\requestData.xls");
		}
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public File getXmlRequest() {
		return xmlRequest;
	}

	public void setXmlRequest(File xmlRequest) {
		this.xmlRequest = xmlRequest;
	}

	public File getServiceDirectory() {
		return serviceDirectory;
	}

	public void setServiceDirectory(File serviceDirectory) {
		this.serviceDirectory = serviceDirectory;
	}

	public File getRequestKeyData() {
		return requestKeyData;
	}

	public void setRequestKeyData(File requestKeyData) {
		this.requestKeyData = requestKeyData;
	}
	
	@Override
	public String toString() {
		return (serviceCode != null ? "serviceCode=" + serviceCode : "") ;
	}
}

package com.muscle.model;

import java.io.InputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.muscle.xml.ProjectModel;

public class ProjectModelLoader {

	public final String DEFAULT_MUSCLE_NAME = "muscle.xml";
	
	private String xmlInputName;
	private ProjectModel projectModel;
	
	public ProjectModelLoader(String xmlInputName) throws Exception {
		super();
		if (xmlInputName == null){
			this.xmlInputName = DEFAULT_MUSCLE_NAME;
		}
		else if(xmlInputName.equals("")){
			this.xmlInputName = DEFAULT_MUSCLE_NAME;
		}
		else{
			this.xmlInputName = xmlInputName;
		}
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream inputStream = loader.getResourceAsStream(this.xmlInputName);
		
		JAXBContext jaxbContext = JAXBContext.newInstance(ProjectModel.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		this.projectModel = (ProjectModel)jaxbUnmarshaller.unmarshal(inputStream);
		
	}

	public String getXmlInputName() {
		return xmlInputName;
	}

	public void setXmlInputName(String xmlInputName) {
		this.xmlInputName = xmlInputName;
	}

	public ProjectModel getProjectModel() {
		return projectModel;
	}

	public void setProjectModel(ProjectModel projectModel) {
		this.projectModel = projectModel;
	}	
	
	
	
}

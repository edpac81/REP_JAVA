package com.factory;

import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.model.RPGDAOModel;
import com.util.BeanUtils;

public class DAOFactory {

	private RPGDAOModel dao;
	private VelocityEngine velocityEngine;
	private Properties properties;
	private VelocityContext velocityContext;
	private StringWriter writer;
	private String templateName;

	public DAOFactory(RPGDAOModel dao, String templateName) throws Exception {
		this.dao = dao;
		this.properties = new Properties();
		this.velocityContext = new VelocityContext();
		this.writer = new StringWriter();
		this.templateName = templateName;
		try {
			// Load the velocity properties from the class path
			this.properties.load(getClass().getClassLoader().getResourceAsStream("velocity.properties"));

			// Create and initialize the template engine
			velocityEngine = new VelocityEngine(properties);
		} catch (Exception e) {
			throw e;
		}
	}

	public void execute() {
		this.velocityContext.put("bean", this.dao);
		this.velocityContext.put("utils", new BeanUtils());
		this.velocityEngine.mergeTemplate(this.templateName, "utf-8", this.velocityContext, this.writer);
	}

	public String getResult() {
		return this.writer.toString();
	}

	public StringWriter getWriter() {
		return writer;
	}
}

package com.config;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
	
	private String fileName;
	private Properties properties;
	
	public PropertiesLoader(String fileName) {
		this.fileName = fileName;
		this.properties = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try {
			InputStream inputStream = loader.getResourceAsStream(this.fileName);
			this.properties.load(inputStream);
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Properties getProperties() {
		return properties;
	}
}
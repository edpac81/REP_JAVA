package com.muscle.config;

import java.util.Map;
import java.util.Properties;

public class Configuration {

	static Map<String, String> propertiesMap;
	static Properties configurationProperties;
	
	public static void load() {
		configurationProperties = new PropertiesLoader("GeneralConfiguration.properties").getProperties();
	}

	public static String getValue(String key) {
    if (configurationProperties.containsKey(key)) {
      return (String)configurationProperties.get(key);
    }
    return new String();
  }
}

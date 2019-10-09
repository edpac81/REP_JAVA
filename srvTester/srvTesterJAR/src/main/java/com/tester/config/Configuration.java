package com.tester.config;

import java.util.Map;
import java.util.Properties;

public class Configuration {
	
  static Map<String, String> pmap;
  static Properties configurationProperties;
  
  public static void load() {
    configurationProperties = new PropertiesLoader("GeneralConfig.properties").getProperties();
  }
  
  public static void clearMap() {
	  configurationProperties.clear();
  }
  
  public static void putValue(String key, String val) {
  	configurationProperties.put(key, val);
  }
  
  public static String getValue(String key) {
    if (configurationProperties.containsKey(key)) {
      return (String)configurationProperties.get(key);
    }
    return new String();
  }
}
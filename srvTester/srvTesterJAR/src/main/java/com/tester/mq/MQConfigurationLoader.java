package com.tester.mq;

import com.tester.config.PropertiesLoader;
import com.tester.logger.STLogger;

import java.util.Properties;

public class MQConfigurationLoader {
	private Properties prop;
	private MQConfiguration mqConfig;

	public MQConfigurationLoader() {
		this("MQConfig.properties");
	}
	public MQConfigurationLoader(String propertiesName) {
		this.prop = new PropertiesLoader(propertiesName).getProperties();
		
		this.mqConfig = new MQConfiguration();

		this.mqConfig.setQueueManagerName(this.prop.getProperty("property.MQ.QManager"));
		this.mqConfig.setFileTransferQueueName(this.prop.getProperty("property.MQ.QFileTransfer"));
		this.mqConfig.setRequestQueueName(this.prop.getProperty("property.MQ.QRequest"));
		this.mqConfig.setResponseQueueName(this.prop.getProperty("property.MQ.QResponse"));
		this.mqConfig.setChannelName(this.prop.getProperty("property.MQ.channel"));
		this.mqConfig.setServerIpAddress(this.prop.getProperty("property.MQ.server"));
		this.mqConfig.setServerPortNumber(this.prop.getProperty("property.MQ.port"));
		this.mqConfig.setUser(this.prop.getProperty("property.MQ.id"));
		this.mqConfig.setPassword(this.prop.getProperty("property.MQ.password"));
		STLogger.getLogger().trace(this.mqConfig.toString());
	}

	public MQConfiguration getMqConfig() {
		return this.mqConfig;
	}
}
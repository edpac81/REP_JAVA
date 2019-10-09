package com.tester.mq;

import java.lang.reflect.Field;

public class MQConfiguration {
	private String queueManagerName;
	private String requestQueueName;
	private String responseQueueName;
	private String fileTransferQueueName;
	private String channelName;
	private String serverIpAddress;
	private String serverPortNumber;
	private String user;
	private String password;

	public MQConfiguration() {
	}

	public MQConfiguration(String queueManagerName, String requestQueueName, String responseQueueName,
			String fileTransferQueueName, String channelName, String serverIpAddress, String serverPortNumber, String user,
			String pass) {
		this.queueManagerName = queueManagerName;
		this.requestQueueName = requestQueueName;
		this.responseQueueName = responseQueueName;
		this.fileTransferQueueName = fileTransferQueueName;
		this.channelName = channelName;
		this.serverIpAddress = serverIpAddress;
		this.serverPortNumber = serverPortNumber;
		this.user = user;
		this.password = pass;
	}

	public String toString() {
		String sb = new String();
		Field f[] = this.getClass().getFields();
		try {
			for (int i = 0; i < f.length; i++) {
				sb = sb + "[" + f[i].getName().toString() + "=" + f[i].get(this.getClass()).toString() + "]";
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return sb;
	}

	public String getQueueManagerName() {
		return this.queueManagerName;
	}

	public String getRequestQueueName() {
		return this.requestQueueName;
	}

	public String getResponseQueueName() {
		return this.responseQueueName;
	}

	public String getFileTransferQueueName() {
		return this.fileTransferQueueName;
	}

	public String getChannelName() {
		return this.channelName;
	}

	public String getServerIpAddress() {
		return this.serverIpAddress;
	}

	public String getServerPortNumber() {
		return this.serverPortNumber;
	}

	public void setQueueManagerName(String queueManagerName) {
		this.queueManagerName = queueManagerName;
	}

	public void setRequestQueueName(String requestQueueName) {
		this.requestQueueName = requestQueueName;
	}

	public void setResponseQueueName(String responseQueueName) {
		this.responseQueueName = responseQueueName;
	}

	public void setFileTransferQueueName(String fileTransferQueueName) {
		this.fileTransferQueueName = fileTransferQueueName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public void setServerIpAddress(String serverIpAddress) {
		this.serverIpAddress = serverIpAddress;
	}

	public void setServerPortNumber(String serverPortNumber) {
		this.serverPortNumber = serverPortNumber;
	}

	public String getUser() {
		return this.user;
	}

	public String getPassword() {
		return this.password;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
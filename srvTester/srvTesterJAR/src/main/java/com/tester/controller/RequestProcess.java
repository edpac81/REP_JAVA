package com.tester.controller;

import com.tester.mq.MQConnector;

public class RequestProcess {

	private String requestMessage;
	private MQConnector mqConnector;

	public RequestProcess() {
		this(null, null);
	}

	public RequestProcess(String requestMessage, MQConnector mqConn) {
		super();
		this.requestMessage = requestMessage;
		this.mqConnector = mqConn;
	}

	public void execute() throws Exception {

		// valida que el mensaje no sea nulo o blancos
		if (this.requestMessage == null || this.requestMessage.equals("")) {
			throw new Exception("Petición en blanco o no existe");
		}
		// pone mensaje en la cola
		this.mqConnector.putMessage(requestMessage);

	}

	public String getRequestMessage() {
		return requestMessage;
	}

	public void setRequestMessage(String requestMessage) {
		this.requestMessage = requestMessage;
	}

	public void setMQConnector(MQConnector mqConnector) {
		this.mqConnector = mqConnector;
	}

}

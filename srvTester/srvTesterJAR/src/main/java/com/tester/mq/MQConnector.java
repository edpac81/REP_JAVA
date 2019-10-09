package com.tester.mq;

import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.tester.config.Configuration;

import java.io.IOException;
import org.apache.commons.codec.binary.Hex;

public class MQConnector {
	private MQQueueManager qManager;
	private MQConfiguration mqConf;
	private MQQueue requestQueue;
	private MQQueue responseQueue;
	private MQQueue fileTrnsQueue;
	private MQMessage requestMessage;
	private MQMessage responseMessage;
	private MQMessage fileContMessage;

	public MQConnector(MQConfiguration mqConf) {
		this.mqConf = mqConf;

		com.ibm.mq.MQEnvironment.channel = this.mqConf.getChannelName();
		com.ibm.mq.MQEnvironment.hostname = this.mqConf.getServerIpAddress();
		com.ibm.mq.MQEnvironment.port = Integer.parseInt(this.mqConf.getServerPortNumber());
		com.ibm.mq.MQEnvironment.userID = this.mqConf.getUser();
		com.ibm.mq.MQEnvironment.password = this.mqConf.getPassword();
	}

	public void openMQManager() throws Exception {
		try {
			this.qManager = new MQQueueManager(this.mqConf.getQueueManagerName());
		} catch (MQException e) {
			e.printStackTrace();
			throw new Exception(
					"Error MQ RC" + e.reasonCode + " al conectarse al QManager " + this.mqConf.getQueueManagerName());
		}
	}

	public void openWorkQueues() throws Exception {
		try {
			int requestOpenOptions = 16;
			int responseOpenOptions = 1;
			this.requestQueue = this.qManager.accessQueue(this.mqConf.getRequestQueueName(), requestOpenOptions, null, null,
					null);
			this.responseQueue = this.qManager.accessQueue(this.mqConf.getResponseQueueName(), responseOpenOptions, null,
					null, null);

			if (Configuration.getValue("property.fileTransfer.flag").equals("1")) {
				this.fileTrnsQueue = this.qManager.accessQueue(this.mqConf.getFileTransferQueueName(), requestOpenOptions, null,
						null, null);
			}
		} catch (MQException mqe) {
			mqe.printStackTrace();
			throw new Exception("Error MQ RC" + mqe.reasonCode + " al abrir colas de trabajo");
		}
	}

	public void closeMQManager() {
		try {
			this.qManager.disconnect();
		} catch (MQException e) {
			e.printStackTrace();
		}
	}

	public void putMessage(String xmlString) throws Exception {
		MQPutMessageOptions putOptions = new MQPutMessageOptions();
		this.requestMessage = new MQMessage();
		try {
			this.requestMessage.writeString(xmlString);
			this.requestMessage.format = "MQSTR   ";
			this.requestMessage.messageType = 1;
			this.requestMessage.replyToQueueName = this.mqConf.getResponseQueueName();
			this.requestQueue.put(this.requestMessage, putOptions);
			Hex.encodeHexString(this.requestMessage.messageId).toUpperCase();
		} catch (MQException e) {
			e.printStackTrace();
			throw new Exception("Error MQ " + e.reasonCode + " al poner mensaje en la cola");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("Error de IO al poner mensaje en la cola");
		}
	}

	public String putFile(byte[] fileContent) throws Exception {
		String msgId = new String();
		MQPutMessageOptions putOptions = new MQPutMessageOptions();
		this.fileContMessage = new MQMessage();
		try {
			this.fileContMessage.write(fileContent);
			this.fileContMessage.format = "        ";
			this.fileContMessage.characterSet = 1208;
			this.fileContMessage.replyToQueueName = "";
			this.fileTrnsQueue.put(this.fileContMessage, putOptions);
			msgId = Hex.encodeHexString(this.fileContMessage.messageId).toUpperCase();
		} catch (MQException e) {
			e.printStackTrace();
			throw new Exception("Error MQ " + e.reasonCode + " al poner archivo en la cola");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("Error de IO al poner archivo en la cola");
		}
		return msgId;
	}

	public String getMessage() throws Exception {
		String msg = null;
		MQGetMessageOptions getOptions = new MQGetMessageOptions();
		try {
			this.responseMessage = new MQMessage();
			this.responseMessage.correlationId = this.requestMessage.messageId;
			getOptions.options = 1;
			getOptions.waitInterval = -1;
			getOptions.matchOptions = 2;

			this.responseQueue.get(this.responseMessage, getOptions);
			msg = this.responseMessage.readStringOfByteLength(this.responseMessage.getMessageLength());
		} catch (MQException e) {
			e.printStackTrace();
			throw new Exception("Error MQ " + e.reasonCode + " al obtener mensaje de respuesta");
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("Error de IO al al obtener mensaje de respuesta");
		}
		return msg;
	}

	public void closeWorkQueues() throws Exception {
		try {
			this.responseQueue.close();
			this.requestQueue.close();
		} catch (MQException e) {
			e.printStackTrace();
			throw new Exception("Error MQ " + e.reasonCode + " al cerrar colas de trabajo.");
		}
	}
}
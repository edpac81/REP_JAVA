package com.tester.controller;

import com.tester.mq.MQConnector;
import com.tester.utils.FileToTransfer;

public class FileTransferenceProcess {
	
	private MQConnector mqConnector;
	private String fileMessageID;
	private FileToTransfer fileToTranfer;
	private int bytesTranfered;
	
	public FileTransferenceProcess(MQConnector mqConnector, String fileName) {
		super();
		this.mqConnector = mqConnector;
		this.bytesTranfered = 0;
		this.fileToTranfer = new FileToTransfer(fileName);
	}
	
	public void execute() throws Exception{
		this.fileToTranfer.openFile();
		this.fileMessageID = this.mqConnector.putFile(this.fileToTranfer.getFileContent());
		this.bytesTranfered = this.fileToTranfer.getFileSize();
		this.fileToTranfer.closeFile();
	}

	public String getFileMessageID() {
		return fileMessageID;
	}

	public int getBytesTranfered() {
		return bytesTranfered;
	}

}
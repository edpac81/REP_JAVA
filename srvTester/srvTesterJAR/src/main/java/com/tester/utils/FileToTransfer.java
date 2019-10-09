package com.tester.utils;

import java.io.File;
import java.io.FileInputStream;

public class FileToTransfer {
	private String fileName;
	private int fileSize;
	private byte[] fileContent;
	private File fileIn;
	private FileInputStream fin;

	public FileToTransfer(String fn) {
		this.fileName = fn;
	}

	public void openFile() throws Exception {
		this.fileIn = new File(this.fileName);
		this.fin = new FileInputStream(this.fileIn);
		this.fileSize = ((int) this.fileIn.length());
		this.fileContent = new byte[this.fileSize];
		this.fin.read(this.fileContent);
	}

	public void closeFile() throws Exception {
		this.fin.close();
	}

	public int getFileSize() {
		return this.fileSize;
	}

	public byte[] getFileContent() {
		return this.fileContent;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}
}
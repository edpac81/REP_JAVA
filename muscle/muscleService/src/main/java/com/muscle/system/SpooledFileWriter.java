package com.muscle.system;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.PrintObject;
import com.ibm.as400.access.PrintObjectTransformedInputStream;
import com.ibm.as400.access.PrintParameterList;
import com.ibm.as400.access.SpooledFile;
import com.ibm.as400.access.SpooledFileList;

public class SpooledFileWriter {

	private AS400 system;
	private SpooledFile spooledFile;
	private int spoolNumber;
	private String spoolName;
	private Logger log;
	
	public SpooledFileWriter(AS400 system){
		this.system = system;
		log = LogManager.getLogger(this.getClass().getName());
	}
	
	public SpooledFileWriter(AS400 system, String spoolName, int spoolNumber, String jobName, String jobNumber,
			String jobUser) {
		super();
		log = LogManager.getLogger(this.getClass().getName());
		this.system = system;
		this.spoolNumber = spoolNumber;
		this.spoolName = spoolName;
		this.spooledFile = new SpooledFile(this.system, // AS400
				this.spoolName, // splf name
				this.spoolNumber, // splf number
				jobName, // job name
				jobUser, // job user
				jobNumber); // job number
	}

	public void getLastSpooledFile() {
		try{
			SpooledFileList splfList = new SpooledFileList( this.system );
			splfList.setUserFilter(this.system.getUserId());
			splfList.setQueueFilter("/QSYS.LIB/QUSRSYS.LIB/PRT01.OUTQ");
		  
			// open list, openSynchronously() returns when the list is completed.
      splfList.openSynchronously();
      
      //get last spooled file in the out queue
      this.spooledFile = (SpooledFile) splfList.getObject(splfList.size()-1);
      
      splfList.close();
      
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void exportSpooledFile() {
		if(spooledFile == null){
			return;
		}
		File outSpooledFile = new File("./spooled/"+spooledFile.getName()+"-"+
				spooledFile.getNumber()+"-"+
				spooledFile.getJobName()+"-"+
				spooledFile.getJobUser()+"-"+
				spooledFile.getJobNumber()+".txt");
		BufferedWriter bufferWriter = null;
		BufferedReader bufferReader = null;
		PrintObjectTransformedInputStream inputStream = null;
		
		try {
			PrintParameterList printParms = new PrintParameterList();
			printParms.setParameter(PrintObject.ATTR_WORKSTATION_CUST_OBJECT, "/QSYS.LIB/QWPDEFAULT.WSCST");
			printParms.setParameter(PrintObject.ATTR_MFGTYPE, "*WSCST");
			
			bufferWriter = new BufferedWriter(new FileWriter(outSpooledFile));
			
			inputStream = spooledFile.getTransformedInputStream(printParms);
			bufferReader = new BufferedReader(new InputStreamReader(inputStream));
			String data = "";
			while ((data = bufferReader.readLine()) != null) {
				if(!data.equals("")){
					bufferWriter.write(data);
					bufferWriter.newLine();
				}
			}
			
			if(bufferWriter != null)
				bufferWriter.close();
			
			if(bufferReader != null)
				bufferReader.close();
			
			log.info("Spooled File writed: "+outSpooledFile.getCanonicalPath());
		} catch (Exception e) {
			log.error(e);
		}
	}
}

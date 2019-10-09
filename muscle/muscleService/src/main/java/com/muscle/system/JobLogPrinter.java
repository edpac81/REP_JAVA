package com.muscle.system;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Enumeration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400Message;
import com.ibm.as400.access.Job;
import com.ibm.as400.access.JobLog;

public class JobLogPrinter {

	private Job job;
	private AS400 system;
	private Logger log;
	
	public JobLogPrinter(AS400 system, Job job) {
		this.system = system;
		this.job = job;
		log = LogManager.getLogger(this.getClass().getName());
	}

	public void printJobLog() {
		File jobLogFile = new File("./joblogs/"+job.getName()+"-"+job.getUser()+"-"+job.getNumber()+".txt");
		
		JobLog jlog = new JobLog(system, job.getName(), job.getUser(), job.getNumber());
		// Enumerate the messages in the job log then print them.
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(jobLogFile));
			Enumeration<?> messageList = jlog.getMessages();
			
			while (messageList.hasMoreElements()) {
				AS400Message message = (AS400Message) messageList.nextElement();
				writer.write (message.getID()+": "+message.getText()+"\n");
			}
			writer.close();
			log.info("jobLogFile Writed: "+jobLogFile.getCanonicalPath());
		} catch (Exception e) {
			log.error(e);
		} 
	}
}

package com.muscle.system;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400Message;
import com.ibm.as400.access.CommandCall;
import com.ibm.as400.access.Job;

public class CommandExecute {

	private AS400 system;
	private CommandCall command;
	private Job job;
	Logger log;
	
	public CommandExecute(AS400 system) throws Exception {
		log = LogManager.getLogger(this.getClass().getName());
		this.system = system;
		this.command = new CommandCall(this.system);
		this.job = command.getServerJob();
		log.info("Connected to job: " + this.job.toString());
	}

	public void run(String stringCommand) throws Exception {
		log.debug("Command: " + stringCommand);
		command.setCommand(stringCommand);

		boolean commandResult = command.run();
		AS400Message msg = command.getMessageList(0);

		if (commandResult) {
			log.debug(msg.getID() + ": " + msg.getText());
		} else {			
			throw new Exception(msg.getID() + ": " + msg.getText());
		}
	}

	public Job getJob() {
		return job;
	}
}

package com.muscle.system;

import java.beans.PropertyVetoException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.as400.access.AS400;
import com.muscle.config.Configuration;

public class SystemConnection {

	private AS400 systemConn;
	private String user;
	private String password;
	Logger logger;
	
	public SystemConnection() throws Exception {
		// crea conexion con el AS400
		this.user = Configuration.getValue("property.general.user");
		this.password = Configuration.getValue("property.general.pass");
		logger = LogManager.getLogger(this.getClass().getName());
		
		try {
			systemConn = new AS400(Configuration.getValue("property.general.iSeriesName"));
			
			if(this.user != null && !this.user.isEmpty())
				systemConn.setUserId(this.user);
			
			if(this.password != null && !this.password.isEmpty())
				systemConn.setPassword(this.password);
			
			systemConn.connectService(AS400.COMMAND);
			logger.info("Logged as "+this.user);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public AS400 getSystemConn() {
		return systemConn;
	}
}
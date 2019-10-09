package com.tester.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class STLogger {
	static org.apache.logging.log4j.Logger log;

	public STLogger() {
	}

	public static void startLooger() {
		try {
			log = LogManager.getLogger(STLogger.class.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Logger getLogger() {
		return log;
	}
}
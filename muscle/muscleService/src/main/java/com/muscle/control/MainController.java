/**
 * 
 */
package com.muscle.control;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.as400.access.AS400;
import com.muscle.config.Configuration;
import com.muscle.model.ProjectModelLoader;
import com.muscle.system.SystemConnection;

/**
 * @author eparajeles
 *
 */
public class MainController {

	/**
	 * 
	 */
	Logger logger;
	
	public MainController() {
		Configuration.load();
		logger = LogManager.getLogger(this.getClass().getName());
		try {
			ProjectModelLoader pml = new ProjectModelLoader(null);
			SystemConnection systemConn = new SystemConnection();
			AS400 system = systemConn.getSystemConn();
			
			// Phase: Build
			if(pml.getProjectModel().getBuild() != null){
				BuildController buildController = new BuildController(pml.getProjectModel().getBuild(), system);
			  buildController.execute();
			}			
			
		} catch (Exception e) {
			logger.error(e);
		}
	}

}

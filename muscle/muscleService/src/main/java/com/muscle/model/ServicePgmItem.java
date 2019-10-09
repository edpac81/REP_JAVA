/**
 * 
 */
package com.muscle.model;

import org.apache.logging.log4j.Logger;
import com.muscle.config.Configuration;
import com.muscle.control.BuildController;
import com.muscle.xml.Item;

/**
 * @author eparajeles
 *
 */
public class ServicePgmItem extends PdeItem {

	public ServicePgmItem(Item item, Logger logger) {
		super(item, logger);
	}

	@Override
	public void runBuilCommand(BuildController bc) throws Exception {
		String stringCommand = this.item.getBuildCommand() == null ? "" : this.item.getBuildCommand();

		if (stringCommand.equals("")) {
			stringCommand = Configuration.getValue("property.build.bnd.srvpgm");
			stringCommand = replaceMarkers(stringCommand);
			this.item.setBuildCommand(stringCommand);
		}
		bc.getCommand().run(item.getBuildCommand());
		try {
			bc.getCommand().run("ADDBNDDIRE "
					+ "BNDDIR(" + bc.getProjectBuild().getCurrentLibrary() +"/"+ bc.getProjectBuild().getBindingDirectory() + ") "
					+ "OBJ("+this.item.getMemberName()+")");
		} catch (Exception e) {
			logger.warn(e);
		}
	}

	protected String replaceMarkers(String command) {
		String cmd = command;
		cmd = cmd.replaceAll("&L", this.item.getSourceFileLibrary());
		cmd = cmd.replaceAll("&N", this.item.getMemberName());
		cmd = cmd.replaceAll("&F", this.item.getSourceFileName());

		return cmd;
	}
}

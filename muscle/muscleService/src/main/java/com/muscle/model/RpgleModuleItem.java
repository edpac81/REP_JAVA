package com.muscle.model;

import com.muscle.config.Configuration;
import com.muscle.control.BuildController;
import com.muscle.xml.Item;

public class RpgleModuleItem extends PdeItem {

	public RpgleModuleItem(Item item) {
		super(item, null);
	}

	@Override
	public void runBuilCommand(BuildController bc) throws Exception {
		String stringCommand = this.item.getBuildCommand() == null ? "" : this.item.getBuildCommand();

		if (stringCommand.equals("")) {
			stringCommand = Configuration.getValue("property.build.rpgle.module");
			stringCommand = replaceMarkers(stringCommand);
			this.item.setBuildCommand(stringCommand);
		}
		bc.getCommand().run(item.getBuildCommand());
	}
	
	protected String replaceMarkers(String command) {
		String cmd=command;
		cmd = cmd.replaceAll("&L", this.item.getSourceFileLibrary());
		cmd = cmd.replaceAll("&N", this.item.getMemberName());
		cmd = cmd.replaceAll("&F", this.item.getSourceFileName());
		cmd = cmd.replaceAll("&M", this.item.getMemberName());
		return cmd;
	}
}

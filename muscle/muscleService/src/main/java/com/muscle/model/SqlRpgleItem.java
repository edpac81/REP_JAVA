/**
 * 
 */
package com.muscle.model;

import com.muscle.config.Configuration;
import com.muscle.control.BuildController;
import com.muscle.xml.Item;

/**
 * @author eparajeles
 *
 */
public class SqlRpgleItem extends PdeItem{

	public SqlRpgleItem(Item item) {
		super(item);
	}
	
	@Override
	public void runBuilCommand(BuildController bc) throws Exception {
		String stringCommand = this.item.getBuildCommand()==null?"":this.item.getBuildCommand();
		
		if(stringCommand.equals("")){
			stringCommand = Configuration.getValue("property.build.sqlrpgle.object");
			stringCommand = replaceMarkers(stringCommand);
			this.item.setBuildCommand(stringCommand);
		}
		bc.getCommand().run(this.item.getBuildCommand());
	}
	
	protected String replaceMarkers(String command) {
		String cmd=command;
		cmd = cmd.replaceAll("&L", this.item.getSourceFileLibrary());
		cmd = cmd.replaceAll("&N", this.item.getMemberName());
		cmd = cmd.replaceAll("&F", this.item.getSourceFileName());
		cmd = cmd.replaceAll("&M", this.item.getMemberName());
		cmd = cmd.replaceAll("&O", this.item.getObjectType().value());
		return cmd;
	}
}

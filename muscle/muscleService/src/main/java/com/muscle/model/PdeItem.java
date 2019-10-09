package com.muscle.model;

import org.apache.logging.log4j.Logger;

import com.muscle.control.BuildController;
import com.muscle.xml.Item;

public abstract class PdeItem {

	protected Item item;
	protected Logger logger;
	
	public PdeItem(Item item){
		this(item, null);
	}
	
	public PdeItem(Item item, Logger log) {
		this.item = item;
		this.logger = log;
	}
	
	public abstract void runBuilCommand(BuildController bc) throws Exception;
	
	protected abstract String replaceMarkers(String command);
	
	@Override
	public String toString() {
		return "PdeItem [" + (item != null ? "item=" + item.toString() : "") + "]";
	}
}

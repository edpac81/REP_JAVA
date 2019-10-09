package com.model;

public class FieldModel {

	private String name;
	private String type;
	private String length;
	private String decimals;
	private boolean isKey;

	public FieldModel(){
		super();
		this.name = "";
		this.type = "";
		this.length = "";
		this.decimals = "";
		this.isKey = false;
	}
	
	public FieldModel(String name, String type, String length, String decimals, boolean isKey) {
		super();
		this.name = name;
		this.type = type;
		this.length = length;
		this.decimals = decimals;
		this.isKey = isKey;
	}
	
	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getLength() {
		return length;
	}

	public String getDecimals() {
		return decimals;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public void setDecimals(String decimals) {
		this.decimals = decimals;
	}

	public boolean isKey() {
		return isKey;
	}

	public void setKey(boolean isKey) {
		this.isKey = isKey;
	}
	
	public String toString(){
		return  (this.isKey ? "(K)" : "") + this.name +"|"+
				"type="+this.type +"|"+
				"length="+this.length +"|"+
				"decimals="+this.decimals;
	}
}

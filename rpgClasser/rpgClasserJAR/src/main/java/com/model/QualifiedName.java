package com.model;

public class QualifiedName {

	private String name;
	private String library;
	
	public QualifiedName() {
		this("","");
	}
	
	public QualifiedName(String name, String library) {
		super();
		this.name = name;
		this.library = library;
	}

	public String getName() {
		return name;
	}

	public String getLibrary() {
		return library;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLibrary(String library) {
		this.library = library;
	}
	
	public String toString(){
		return this.library.trim() + "/" + this.name.trim();
	}

}

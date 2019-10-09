package com.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A Simple Model that represents a bean with a name and a set of fields
 *
 * @author shaines
 */
public class DAOModel {

	/**
	 * The name of the bean
	 */
	private String name;
	private boolean includeIsOnFile;
	private boolean includeSelect;
	private boolean includeInsert;
	private boolean includeUpdate;
	private boolean includeDelete;
	private boolean includeSetters;
	private boolean includeGetters;

	/**
	 * A mapping of each bean field's name to its type corriente
	 * 
	 */
	private List<FieldModel> fields = new ArrayList<FieldModel>();
	private List<FieldModel> keyFields = new ArrayList<FieldModel>();

	public DAOModel() {
	}

	public DAOModel(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addField(FieldModel field) {
		fields.add(field);
		if (field.isKey())
			keyFields.add(field);
	}

	public ArrayList<FieldModel> getFields() {
		return (ArrayList<FieldModel>) fields;
	}

	public List<FieldModel> getKeyFields() {
		return keyFields;
	}

	public boolean isLastField(FieldModel field, boolean isKey) {
		int lastIndex;
		FieldModel lf;
		if (isKey) {
			lastIndex = this.keyFields.size() - 1;
			lf = this.keyFields.get(lastIndex);
		} else {
			lastIndex = this.fields.size() - 1;
			lf = this.fields.get(lastIndex);
		}
		if (lf == field) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isFirstField(FieldModel field, boolean isKey) {
		FieldModel lf;
		if (isKey) {
			lf = this.keyFields.get(0);
		} else {
			lf = this.fields.get(0);
		}
		if (lf == field) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isIncludeSelect() {
		return includeSelect;
	}

	public void setIncludeSelect(boolean includeSelect) {
		this.includeSelect = includeSelect;
	}

	public boolean isIncludeInsert() {
		return includeInsert;
	}

	public boolean isIncludeUpdate() {
		return includeUpdate;
	}

	public boolean isIncludeDelete() {
		return includeDelete;
	}

	public void setIncludeInsert(boolean includeInsert) {
		this.includeInsert = includeInsert;
	}

	public void setIncludeUpdate(boolean includeUpdate) {
		this.includeUpdate = includeUpdate;
	}

	public void setIncludeDelete(boolean includeDelete) {
		this.includeDelete = includeDelete;
	}

	public boolean isIncludeSetters() {
		return includeSetters;
	}

	public void setIncludeSetters(boolean includeSetters) {
		this.includeSetters = includeSetters;
	}

	public boolean isIncludeGetters() {
		return includeGetters;
	}

	public void setIncludeGetters(boolean includeGetters) {
		this.includeGetters = includeGetters;
	}

	public boolean isIncludeIsOnFile() {
		return includeIsOnFile;
	}

	public void setIncludeIsOnFile(boolean includeIsOnFile) {
		this.includeIsOnFile = includeIsOnFile;
	}

}
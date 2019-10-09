package com.input;

import org.apache.logging.log4j.Logger;

import com.model.DAOModel;

public abstract class DAOInputReader {

	protected DAOModel beanModel;
	protected Logger log;
	
	public DAOInputReader(DAOModel beanModel) {
		this.beanModel = beanModel;
	}

	public abstract void loadModel() throws Exception;
}

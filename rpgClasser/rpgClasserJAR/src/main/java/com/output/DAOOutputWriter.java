package com.output;

import org.apache.logging.log4j.Logger;

import com.model.DAOModel;

public abstract class DAOOutputWriter {

	protected DAOModel daoModel;
	protected Logger log;
	
	public DAOOutputWriter(DAOModel daoModel) {
		this.daoModel = daoModel;
	}

	public abstract void writeDAO() throws Exception;
}

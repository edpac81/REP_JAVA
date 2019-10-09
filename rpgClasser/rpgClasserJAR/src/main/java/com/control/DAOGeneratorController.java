package com.control;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.config.Configuration;
import com.factory.DAOFactory;
import com.input.DAOInputReader;
import com.model.RPGDAOModel;
import com.output.DAOOutputWriter;

public class DAOGeneratorController {

	Logger log;
	private DAOInputReader daoInputReader;
	private DAOOutputWriter daoOutputWriter;
	private RPGDAOModel rpgDaoModel;
	private DAOFactory rpgSourceFactory;
	private DAOFactory txtSourceFactory;
	private String rpgDAOTemplate;
	private String txtDAOTemplate;
	
	
	public DAOGeneratorController(RPGDAOModel rpgDaoModel, DAOInputReader daoInputReader, DAOOutputWriter daoOutputWriter) {
		
		this.rpgDaoModel = rpgDaoModel;
		this.daoInputReader = daoInputReader;
		this.daoOutputWriter = daoOutputWriter;
		log = LogManager.getLogger(this.getClass().getName());
		
		this.rpgDAOTemplate = Configuration.getValue("property.general.rpgDAOTemplate");
		this.txtDAOTemplate = Configuration.getValue("property.general.txtDAOTemplate");
	}

	public void execute() throws Exception{
				
		// load data from parameters
		daoInputReader.loadModel();
		
		// execute DAOFactory
		this.rpgSourceFactory = new DAOFactory(this.rpgDaoModel, this.rpgDAOTemplate);
		this.rpgSourceFactory.execute();
		
		this.txtSourceFactory = new DAOFactory(this.rpgDaoModel, this.txtDAOTemplate);
		this.txtSourceFactory.execute();
		
		// write to output
		this.rpgDaoModel.setRpgSource(this.rpgSourceFactory.getResult());
		this.rpgDaoModel.setTxtSource(this.txtSourceFactory.getResult());
		this.daoOutputWriter.writeDAO();
		/*
		this.rpgWritter = new MemberWritter(this.rpgDaoModel.getAs400(), this.rpgDaoModel.getRpgMember(), "SQLRPGLE", this.rpgRecords);
		this.rpgWritter.writeRecords();
		
		this.txtWritter = new MemberWritter(this.rpgDaoModel.getAs400(), this.rpgDaoModel.getTxtMember(), "TXT", this.txtRecords);
		this.txtWritter.writeRecords();
		*/
	}

}

package com.output;

import com.model.RPGDAOModel;

public class DAOConsoleWriter extends DAOOutputWriter {

	public DAOConsoleWriter(RPGDAOModel daoModel) {
		super(daoModel);
	}

	@Override
	public void writeDAO() throws Exception {
		RPGDAOModel rpgDao = (RPGDAOModel) this.daoModel;
		System.out.println("[RPG SOURCE]");
		System.out.println(rpgDao.getRpgSource());
		System.out.println("[TXT SOURCE]");
		System.out.println(rpgDao.getTxtSource());
	}

}

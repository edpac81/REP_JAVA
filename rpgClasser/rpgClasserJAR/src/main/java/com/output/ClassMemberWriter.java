package com.output;

import com.ibm.as400.access.QSYSObjectPathName;
import com.model.RPGDAOModel;

public class ClassMemberWriter extends DAOOutputWriter {

	private MemberWritter rpgWritter;
	private MemberWritter txtWritter;
	
	public ClassMemberWriter(RPGDAOModel daoModel){
		super(daoModel);
	}
	
	public ClassMemberWriter(RPGDAOModel daoModel,
			QSYSObjectPathName rpgMember, 
			QSYSObjectPathName txtMember) {
		this(daoModel);
	}

	@Override
	public void writeDAO() throws Exception {
		RPGDAOModel rpgDAOModel = (RPGDAOModel) this.daoModel;
		String[] rpgSource = rpgDAOModel.getRpgSource().split("\n");
		String[] txtSource = rpgDAOModel.getTxtSource().split("\n");
		
		//escribe fuente RPG en miembro del iSeries
		this.rpgWritter = new MemberWritter(
				rpgDAOModel.getAs400(), 
				rpgDAOModel.getRpgMember(), 
				"RPGLE", rpgSource);
		this.rpgWritter.writeRecords();
		
		//escribe fuente txt en miembrp del iSeries
		this.txtWritter = new MemberWritter(
				rpgDAOModel.getAs400(), 
				rpgDAOModel.getTxtMember(),
				"TXT", txtSource);
		this.txtWritter.writeRecords();
	}

}

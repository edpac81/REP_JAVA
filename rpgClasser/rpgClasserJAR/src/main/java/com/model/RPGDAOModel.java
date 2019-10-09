package com.model;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.QSYSObjectPathName;

public class RPGDAOModel extends DAOModel {

	private QSYSObjectPathName rpgMember;
	private QSYSObjectPathName txtMember;
	private String rpgSource;
	private String txtSource;
	private AS400 as400;
	
	public RPGDAOModel(String name){
		super(name);
	}
	
	public RPGDAOModel(String name,QSYSObjectPathName rpgMember,QSYSObjectPathName txtMember) {
		this(name);
		this.rpgMember = rpgMember;
		this.txtMember = txtMember;
	}

	public QSYSObjectPathName getRpgMember() {
		return rpgMember;
	}

	public void setRpgMember(QSYSObjectPathName rpgMember) {
		this.rpgMember = rpgMember;
	}

	public QSYSObjectPathName getTxtMember() {
		return txtMember;
	}

	public void setTxtMember(QSYSObjectPathName txtMember) {
		this.txtMember = txtMember;
	}

	public AS400 getAs400() {
		return as400;
	}

	public void setAs400(AS400 as400) {
		this.as400 = as400;
	}

	public String getRpgSource() {
		return rpgSource;
	}

	public void setRpgSource(String rpgSource) {
		this.rpgSource = rpgSource;
	}

	public String getTxtSource() {
		return txtSource;
	}

	public void setTxtSource(String txtSource) {
		this.txtSource = txtSource;
	}

}

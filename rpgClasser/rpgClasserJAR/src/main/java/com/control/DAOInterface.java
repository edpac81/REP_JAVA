
package com.control;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.config.Configuration;
import com.ibm.as400.access.AS400;
import com.ibm.as400.access.QSYSObjectPathName;
import com.input.CSVInputReader;
import com.input.DAOInputReader;
import com.input.TableInputReader;
import com.model.RPGDAOModel;
import com.output.ClassMemberWriter;
import com.output.DAOConsoleWriter;
import com.output.DAOMemberWriter;
import com.output.DAOOutputWriter;

/**
 * @author eparajeles
 *
 */
public class DAOInterface {

	public static final int INPUT_AS400_TABLE = 1;
	public static final int INPUT_CSV_FILE = 2;

	public static final int OUTPUT_AS400_MEMBER = 1;
	public static final int OUTPUT_CONSOLE = 2;
	
	private String daoName;
	private String csvName;
	private QSYSObjectPathName inputTable;
	private QSYSObjectPathName rpgSource;
	private QSYSObjectPathName txtSource;

	private int inputType;
	private int outputType;
	private RPGDAOModel daoModel;
	private DAOInputReader daoInput;
	private DAOOutputWriter daoOutput;
	private AS400 as400;
	
	Logger log;
	
	public DAOInterface(String daoName, boolean includeIsOnFile, 
			boolean includeSelect, boolean includeInsert, boolean includeUpdate,
			boolean includeDelete, boolean includeSetters, boolean includeGetters, 
			int inputType, int outputType) {
		super();
		Configuration.load();
		log = LogManager.getLogger(this.getClass().getName());
		log.trace("Interface started.");
		this.daoName = daoName;
		this.inputType = inputType;
		this.outputType = outputType;
		this.daoModel = new RPGDAOModel(this.daoName);
		this.daoModel.setIncludeIsOnFile(includeIsOnFile);
		this.daoModel.setIncludeSelect(includeSelect);
		this.daoModel.setIncludeInsert(includeInsert);
		this.daoModel.setIncludeUpdate(includeUpdate);
		this.daoModel.setIncludeDelete(includeDelete);
		this.daoModel.setIncludeSetters(includeSetters);
		this.daoModel.setIncludeGetters(includeGetters);
	}

	public DAOInterface(String daoName, boolean includeIsOnFile, 
			boolean includeSelect, boolean includeInsert, boolean includeUpdate, boolean includeDelete, 
			boolean includeSetters, boolean includeGetters, 
			int inputType, int outputType, 
			String inputTableLibrary,	String inputTableName, 
			String rpgSourceFileLibrary, String rpgSourceFileName, String rpgSourceMember,
			String txtSourceFileLibrary, String txtSourceFileName, String txtSourceMember) {

		this(daoName, includeIsOnFile, 
				includeSelect, includeInsert, includeUpdate, includeDelete, 
				includeSetters, includeGetters, inputType, outputType);

		this.inputTable = new QSYSObjectPathName(inputTableLibrary, inputTableName, "FILE");
		this.rpgSource = new QSYSObjectPathName(rpgSourceFileLibrary, rpgSourceFileName, rpgSourceMember, "MBR");
		this.txtSource = new QSYSObjectPathName(txtSourceFileLibrary, txtSourceFileName, txtSourceMember, "MBR");
		
		this.daoModel.setRpgMember(this.rpgSource);
		this.daoModel.setTxtMember(this.txtSource);
	}

	public DAOInterface(String daoName, boolean includeIsOnFile, 
			boolean includeSelect, boolean includeInsert, boolean includeUpdate, boolean includeDelete, 
			boolean includeSetters, boolean includeGetters, 
			int inputType, int outputType, 
			QSYSObjectPathName inputTable, QSYSObjectPathName rpgSource, QSYSObjectPathName txtSource) {

		this(daoName, includeIsOnFile, 
				includeSelect, includeInsert, includeUpdate, includeDelete, 
				includeSetters, includeGetters, inputType, outputType);		
		
		this.daoModel.setRpgMember(rpgSource);
		this.daoModel.setTxtMember(txtSource);
		
		this.inputTable = inputTable;
		this.rpgSource = rpgSource;
		this.txtSource = txtSource;
	}
	
	public DAOInterface(String daoName, String csvName, int inputType, int outputType, QSYSObjectPathName rpgSource, QSYSObjectPathName txtSource){
		this(daoName, false, 
				false, false, false, false, 
				true, true, inputType, outputType);	
		this.csvName = csvName;
		
		this.daoModel.setRpgMember(rpgSource);
		this.daoModel.setTxtMember(txtSource);
		
		this.rpgSource = rpgSource;
		this.txtSource = txtSource;
	}
	
	public DAOInterface(String daoName, String csvName, int inputType, int outputType, 
			String rpgSourceFileLibrary, String rpgSourceFileName, String rpgSourceMember,
			String txtSourceFileLibrary, String txtSourceFileName, String txtSourceMember){
		this(daoName, false, 
				false, false, false, false, 
				true, true, inputType, outputType);	
		this.csvName = csvName;
		
		this.rpgSource = new QSYSObjectPathName(rpgSourceFileLibrary, rpgSourceFileName, rpgSourceMember, "MBR");
		this.txtSource = new QSYSObjectPathName(txtSourceFileLibrary, txtSourceFileName, txtSourceMember, "MBR");
		
		this.daoModel.setRpgMember(rpgSource);
		this.daoModel.setTxtMember(txtSource);
	}

	public void buildObjects() throws Exception {
		this.as400 = createAS400Conn();
		this.daoModel.setAs400(as400);
		
		if(inputType == INPUT_AS400_TABLE) {
			buildTableInput();
		}else if(inputType == INPUT_CSV_FILE){
			buildCsvInput();
		}		
		log.trace("Objects builded.");
	}
	
	private void buildTableInput(){		
		this.daoInput = new TableInputReader(this.daoModel, this.inputTable);
		if(outputType == OUTPUT_AS400_MEMBER){
			this.daoOutput = new DAOMemberWriter(this.daoModel);
		}else if(outputType == OUTPUT_CONSOLE){
			this.daoOutput = new DAOConsoleWriter(this.daoModel);
		}		
	}

	private void buildCsvInput(){
		this.daoInput = new CSVInputReader(this.daoModel, this.csvName);
		if(outputType == OUTPUT_AS400_MEMBER){
			this.daoOutput = new ClassMemberWriter(this.daoModel);
		}else if(outputType == OUTPUT_CONSOLE){
			this.daoOutput = new DAOConsoleWriter(this.daoModel);
		}
	}
	
	public void executeController() throws Exception {
		DAOGeneratorController dgc = 
				new DAOGeneratorController(this.daoModel, this.daoInput, this.daoOutput);
		try {
			dgc.execute();
			log.trace("DAOController finished.");
		} catch (Exception e) {
			log.error("DAOController failed.",e);
			throw e;
		}
	}

	public AS400 createAS400Conn() throws Exception {
		// crea conexion con el AS400
		String user = Configuration.getValue("property.general.user");
		String pass = Configuration.getValue("property.general.pass");
		AS400 as400;

		if (user.equals("") && pass.equals("")) {
			as400 = new AS400(Configuration.getValue("property.general.iSeriesName"));
		} else if (!user.equals("") && pass.equals("")) {
			as400 = new AS400(Configuration.getValue("property.general.iSeriesName"), user);
		} else {
			as400 = new AS400(Configuration.getValue("property.general.iSeriesName"), user, pass);
		}
		return as400;
	}

	public String getCsvName() {
		return csvName;
	}

	public void setCsvName(String csvName) {
		this.csvName = csvName;
	}
}

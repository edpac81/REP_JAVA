package com.input;

import org.apache.logging.log4j.LogManager;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400FileRecordDescription;
import com.ibm.as400.access.BinaryFieldDescription;
import com.ibm.as400.access.CharacterFieldDescription;
import com.ibm.as400.access.DateFieldDescription;
import com.ibm.as400.access.ExtendedIllegalArgumentException;
import com.ibm.as400.access.FieldDescription;
import com.ibm.as400.access.PackedDecimalFieldDescription;
import com.ibm.as400.access.QSYSObjectPathName;
import com.ibm.as400.access.RecordFormat;
import com.ibm.as400.access.SequentialFile;
import com.ibm.as400.access.TimeFieldDescription;
import com.ibm.as400.access.TimestampFieldDescription;
import com.ibm.as400.access.ZonedDecimalFieldDescription;
import com.model.FieldModel;
import com.model.RPGDAOModel;

public class TableInputReader extends DAOInputReader {

	private AS400 as400;
	private QSYSObjectPathName tableOPN;
	private AS400FileRecordDescription recordDescription;
	
	public TableInputReader(RPGDAOModel daoModel, QSYSObjectPathName tableOPN) {
		super(daoModel);
		this.log = LogManager.getLogger(this.getClass().getName());
		this.as400 = daoModel.getAs400();
		this.tableOPN = tableOPN;
		new SequentialFile(this.as400,this.tableOPN.getPath());
		this.recordDescription = new AS400FileRecordDescription(as400,this.tableOPN.getPath());
		log.trace("Table Input: "+tableOPN.getLibraryName() +"/"+ tableOPN.getObjectName());
	}

	@Override
	public void loadModel() throws Exception {
		
		RecordFormat[] format = this.recordDescription.retrieveRecordFormat();
		RecordFormat recordFormat = format[0];
		FieldDescription[] fieldDescr = recordFormat.getFieldDescriptions();

		for(int i = 0; i < fieldDescr.length; i++){
			FieldModel fm = getFieldModel(fieldDescr[i]);
			try{
				@SuppressWarnings("unused")
				int ki = recordFormat.getIndexOfKeyFieldName(fm.getName());
				fm.setKey(true);
			}catch(ExtendedIllegalArgumentException e){/*no es llave*/}
			beanModel.addField(fm);
			log.trace("DAOModel_"+this.beanModel.getName()+" Added field: " + fm.toString());
		}
	}

	private FieldModel getFieldModel(FieldDescription fd){
		FieldModel fm = null;
		
		if(fd instanceof CharacterFieldDescription)
			fm = getFieldModel((CharacterFieldDescription) fd);
		
		else if(fd instanceof TimestampFieldDescription)
			fm = getFieldModel((TimestampFieldDescription) fd);
		
		else if(fd instanceof DateFieldDescription)
			fm = getFieldModel((DateFieldDescription) fd);
		
		else if(fd instanceof TimeFieldDescription)
			fm = getFieldModel((TimeFieldDescription) fd);
		
		else if(fd instanceof ZonedDecimalFieldDescription)
			fm = getFieldModel((ZonedDecimalFieldDescription) fd);
		
		else if(fd instanceof PackedDecimalFieldDescription)
			fm = getFieldModel((PackedDecimalFieldDescription) fd);
		
		else if(fd instanceof BinaryFieldDescription)
			fm = getFieldModel((BinaryFieldDescription) fd);
		
		fm.setName(fd.getDDSName());
		return fm;
	}
	
	private FieldModel getFieldModel(CharacterFieldDescription fd) {
		FieldModel fm = new FieldModel();
		fm.setType("char");
		fm.setLength(String.valueOf(fd.getLength()));
		return fm;
	}
	
	private FieldModel getFieldModel(TimestampFieldDescription fd) {
		FieldModel fm = new FieldModel();		
		fm.setType("timestamp");
		return fm;
	}
	
	private FieldModel getFieldModel(DateFieldDescription fd) {
		FieldModel fm = new FieldModel();
		fm.setType("date");
		return fm;
	}
	
	private FieldModel getFieldModel(TimeFieldDescription fd) {
		FieldModel fm = new FieldModel();
		fm.setType("time");
		return fm;
	}
	
	private FieldModel getFieldModel(ZonedDecimalFieldDescription fd) {
		FieldModel fm = new FieldModel();
		fm.setType("zoned");
		fm.setLength(String.valueOf(fd.getLength()));
		fm.setDecimals(String.valueOf(fd.getDecimalPositions()));
		return fm;
	}
	
	private FieldModel getFieldModel(PackedDecimalFieldDescription fd) {
		FieldModel fm = new FieldModel();
		fm.setType("packed");
		fm.setLength(String.valueOf(fd.getLength()));
		fm.setDecimals(String.valueOf(fd.getDecimalPositions()));
		return fm;
	}
	
	private FieldModel getFieldModel(BinaryFieldDescription fd) {
		FieldModel fm = new FieldModel();
		fm.setType("int");
		//valida si tiene la longitud de un Integer
		if(fd.getLength() == 9)
			fm.setLength("10");
		
		//asigna 0 decimales
		fm.setDecimals("0");
		return fm;
	}	
}

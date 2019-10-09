package com.input;

import java.io.FileReader;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;

import com.model.DAOModel;
import com.model.FieldModel;

public class CSVInputReader extends DAOInputReader {

	private static final String [] FILE_HEADER_MAPPING = {"name","type","length","decimals","isKey"};
	
	private String csvName;
	private FileReader fileReader;
	private CSVParser csvFileParser;
	private CSVFormat csvFileFormat;
	
	public CSVInputReader(DAOModel beanModel, String csvName) {
		super(beanModel);
		this.log = LogManager.getLogger(this.getClass().getName());
		this.csvName = csvName;
		this.csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING);
	}

	@Override
	public void loadModel() throws Exception{
		try{
  		ClassLoader cl = getClass().getClassLoader();
  		this.fileReader = new FileReader(cl.getResource(this.csvName).getFile());
  		log.trace("CSV File Input");
		}catch(Exception e){
			try{
				this.fileReader = new FileReader(this.csvName);
			}catch(Exception e2){
				throw e;
			}
		}
		this.csvFileParser = new CSVParser(this.fileReader, this.csvFileFormat);

		List<CSVRecord> csvRecords = this.csvFileParser.getRecords();
		CSVRecord csvRecord;
		FieldModel fm;
		for(int i = 1; i < csvRecords.size() ; i++){
			csvRecord = csvRecords.get(i);
			String name = csvRecord.get(FILE_HEADER_MAPPING[0]);
			String type = csvRecord.get(FILE_HEADER_MAPPING[1]);
			String length = csvRecord.get(FILE_HEADER_MAPPING[2]);
			String decimals = csvRecord.get(FILE_HEADER_MAPPING[3]);
			boolean isKey = csvRecord.get(FILE_HEADER_MAPPING[4]).equals("true")?true:false;
			fm = new FieldModel(name, type, length, decimals, isKey);
			this.beanModel.addField(fm);
			log.trace("DAOModel_"+this.beanModel.getName()+" Added field: " + fm.toString());
		}
	}
}

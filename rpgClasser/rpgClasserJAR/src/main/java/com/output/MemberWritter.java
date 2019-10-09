package com.output;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400File;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.CommandCall;
import com.ibm.as400.access.ErrorCompletingRequestException;
import com.ibm.as400.access.IFSFile;
import com.ibm.as400.access.QSYSObjectPathName;
import com.ibm.as400.access.Record;
import com.ibm.as400.access.SequentialFile;

public class MemberWritter {
	
	private AS400 system;
	private String fileName;
	private String fileLibr;
	private QSYSObjectPathName file;
	private SequentialFile secFile;
	private String strRecord[];
	private DateFormat df;
	private String memberType;
	Logger log;
	
	public MemberWritter (AS400 system, QSYSObjectPathName file, String mbrType,String strRecord[]){
		this.system = system;
		this.memberType = mbrType.toUpperCase();
		this.strRecord = strRecord;
		this.df = new SimpleDateFormat("yyMMdd");
		this.file = file;
		log = LogManager.getLogger(this.getClass().getName());
	}
	
	public void writeRecords() throws AS400SecurityException, InterruptedException, IOException, ErrorCompletingRequestException, PropertyVetoException{
		Record[] arrRecord = new Record[this.strRecord.length];
		Record wrkRecord;
		validateFile();
		
		if(!this.secFile.isOpen()){
			this.secFile.open(AS400File.WRITE_ONLY, 0, AS400File.COMMIT_LOCK_LEVEL_NONE);
		}		
		for(int i = 0; i < this.strRecord.length; i++){
			wrkRecord = this.secFile.getRecordFormat().getNewRecord();
			
			//quita saltos de línea
			strRecord[i] = strRecord[i].replaceAll("[\n\r]","");
			
			wrkRecord.setField(0, new BigDecimal((i+1)));
			wrkRecord.setField(1, new BigDecimal(df.format(new Date())));
			wrkRecord.setField(2, strRecord[i]);
			
			arrRecord[i] = wrkRecord;
			
		}
		this.secFile.write(arrRecord);
		this.secFile.close();
		log.trace("OutFile " + this.secFile.getPath() + " Writed successfully ("+ this.strRecord.length + " records).");
	}

	private void validateFile() 
			throws AS400SecurityException, 
			InterruptedException, 
			IOException, 
			ErrorCompletingRequestException, 
			PropertyVetoException {
		
		// Variables locales
		CommandCall cmd = new CommandCall(this.system);
		String sCmd;
		
		// Valida si existe el archivo fuente
		IFSFile wrkFile = new IFSFile(this.system, new QSYSObjectPathName(this.file.getLibraryName(), this.file.getObjectName(), "FILE").getPath());
		if(!wrkFile.exists()){
			sCmd = "CRTPF FILE("+this.fileLibr+"/"+this.fileName+") RCDLEN(112) FILETYPE(*SRC) MBR(*NONE) MAXMBRS(*NOMAX) REUSEDLT(*NO)";
			cmd.run(sCmd);
		}
		
		// Valida si existe el miembro
		SequentialFile wrkSF;
		IFSFile wrkMember = new IFSFile(this.system, this.file.getPath());
		if(!wrkMember.exists()){
			wrkSF = new SequentialFile(this.system,wrkFile.getPath());
			
			wrkSF.addPhysicalFileMember(this.file.getMemberName(), "DAO for "+this.file.getMemberName());
			
			sCmd = "CHGPFM FILE("+this.file.getLibraryName()+"/"+this.file.getObjectName()+")"
					+ " MBR("+this.file.getMemberName()+")"
					+ " SRCTYPE("+this.memberType+")";
			cmd.run(sCmd);
			
			this.secFile = new SequentialFile(this.system, this.file.getPath());
			this.secFile.setRecordFormat();

		}else{
			this.secFile = new SequentialFile(this.system,this.file.getPath());
			this.secFile.setRecordFormat();
			
			sCmd = "CLRPFM FILE("+this.file.getLibraryName()+"/"+this.file.getObjectName()+") MBR("+this.file.getMemberName()+")";
			cmd.run(sCmd);
		}
	}	
}
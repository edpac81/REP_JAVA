package com.tester.excel;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tester.logger.STLogger;

public class ExcelFileReader {
	public static final String FILE_TYPE_EXCEL = "XLS";
	public static final String FILE_TYPE_EXCEL_2007 = "XLSX";
	private FileInputStream fin;
	private POIFSFileSystem poifs;
	private File iofile;
	private Workbook wb;

	public ExcelFileReader(File iofile) throws Exception {
		this.iofile = iofile;
		readFile();
	}

	private void readFile() throws Exception {
		try {
			this.wb = openExcelFile();
		} catch (Exception e) {
			throw e;
		}
	}

	private Workbook openExcelFile() throws Exception {
		Workbook wb = null;
		try {
			this.fin = new FileInputStream(this.iofile);
			this.poifs = new POIFSFileSystem(this.fin);
			wb = new HSSFWorkbook(this.poifs);
			STLogger.getLogger().trace("Objeto HSSFWorkbook creado con: " + this.iofile.getAbsolutePath());
		} catch (OfficeXmlFileException e) {
			this.fin = new FileInputStream(this.iofile);
			wb = new XSSFWorkbook(this.fin);
			STLogger.getLogger().trace("Objeto XSSFWorkbook creado con: " + this.iofile.getAbsolutePath());
		} finally {
			this.fin.close();
		}

		return wb;
	}

	public Workbook getWorkBook() {
		return this.wb;
	}
}
package com.tester.excel;

import com.tester.config.Configuration;
import com.tester.logger.STLogger;
import com.tester.model.RequestKeys;
import com.tester.model.RequestParameters;
import com.tester.model.TestCase;
import com.tester.utils.TestCaseList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class WorkbookProcessor {
	private Workbook workbook;
	private TestCaseList testCaseSet;
	private RequestKeys requestKeys;

	public WorkbookProcessor(Workbook wb) {
		this.workbook = wb;
		this.testCaseSet = new TestCaseList();
	}

	public void processWorkbook() {
		Sheet workSheet = this.workbook.getSheetAt(0);
		processSheet(workSheet);
		STLogger.getLogger().trace("Objeto workSheet procesado");
	}

	private void processSheet(Sheet ws) {
		Iterator<Row> rowI = ws.iterator();
		while (rowI.hasNext()) {
			Row workRow = (Row) rowI.next();

			if (workRow.getRowNum() == 0) {
				this.requestKeys = getHeaders(workRow);
				STLogger.getLogger().trace(
						"srvKeys obtenido: " + this.requestKeys.toString());
			} else {
				TestCase stc = getTestCase(workRow);
				this.testCaseSet.add(stc);
			}
		}
		this.requestKeys.remove(0);
		this.requestKeys.remove(0);
	}

	private TestCase getTestCase(Row workRow) {
		RequestParameters hm = getRowData2(workRow);

		String caseId = (String) hm.remove(Configuration
				.getValue("property.excel.header.caseId"));
		String escenaryId = (String) hm.remove(Configuration
				.getValue("property.excel.header.escenaryId"));

		TestCase stc = new TestCase(caseId, escenaryId, hm);

		return stc;
	}

	private RequestKeys getHeaders(Row workRow) {
		RequestKeys srvKey = new RequestKeys(getRowData(workRow));

		for (int i = 0; i < srvKey.size(); i++) {
			String tagName = (String) srvKey.get(i);
			if (tagName.equals(Configuration
					.getValue("property.fileTransfer.fileTagName"))) {
				//this.testCaseSet.setFileTransferService(true);
				Configuration.putValue("property.fileTransfer.flag", "1");
			}
		}

		return srvKey;
	}

	private Collection<String> getRowData(Row workRow) {
		ArrayList<String> data = new ArrayList<String>();
		Iterator<Cell> cellI = workRow.iterator();
		while (cellI.hasNext()) {
			Cell workCell = (Cell) cellI.next();
			String str = getCellData(workCell);
			if (str != null)
				data.add(str);
		}
		return data;
	}

	private RequestParameters getRowData2(Row workRow) {
		RequestParameters data = new RequestParameters();
		for (int i = 0; i < this.requestKeys.size(); i++) {
			Cell workCell = workRow.getCell(i);
			String str = "";
			if (workCell != null) {
				str = getCellData(workCell);
			}
			data.put((String) this.requestKeys.get(i), str);
		}
		return data;
	}

	private String getCellData(Cell cell) {
		String data = new String();
		int vCellType = cell.getCellType();
		switch (vCellType) {
		case 1:
			data = cell.getStringCellValue();
			break;

		case 0:
			data = getCellStringData(cell.getNumericCellValue());
		}

		STLogger.getLogger().trace(
				"Row[" + cell.getRowIndex() + "]_Cell[" + cell.getColumnIndex()
						+ "]_data[" + data + "]");
		return data;
	}

	public String getCellStringData(double numericCellValue) {
		String result = null;
		String stdFmtP = "%.15f";
		String inStr = String.format(stdFmtP,
				new Object[] { Double.valueOf(numericCellValue) });

		int pp = inStr.indexOf(".");
		long intPart = (long) Double.parseDouble(inStr);
		result = String.valueOf(intPart);
		if (pp > 0) {
			String inStrDec = inStr.substring(pp + 1);
			long decPart = (long) Double.parseDouble(inStrDec);
			if (decPart != 0L) {
				StringBuffer sb = new StringBuffer(inStrDec);
				StringBuffer rb = sb.reverse();

				while (rb.charAt(0) == '0') {
					rb.deleteCharAt(0);
				}
				sb = rb.reverse();
				inStrDec = sb.toString();
				decPart = (long) Double.parseDouble(inStrDec);
				result = result + "." + String.valueOf(decPart);
			}
		}
		return result;
	}

	public TestCaseList getTestCaseSet() {
		return this.testCaseSet;
	}

	public RequestKeys getRequestKeys() {
		return this.requestKeys;
	}
}
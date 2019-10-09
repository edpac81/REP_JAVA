package com.tester.excel;

import com.tester.config.Configuration;
import com.tester.model.RequestKeys;
import com.tester.model.TestCase;
import com.tester.utils.TestCaseList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ResultWriter {
	private Workbook workbook;
	private TestCaseList testCaseList;
	private RequestKeys requestKeys;

	public ResultWriter(TestCaseList sts, RequestKeys sk) {
		this.workbook = new HSSFWorkbook();
		this.testCaseList = sts;
		this.requestKeys = sk;
	}

	public void writeResult() {
		try {
			Sheet sheet = this.workbook.createSheet("Results");

			Row row = sheet.createRow(0);
			Cell cell = row.createCell(0);
			cell.setCellValue("Case");
			cell = row.createCell(1);
			cell.setCellValue("Escenary");
			for (int i = 0; i < this.requestKeys.size(); i++) {
				cell = row.createCell(i + 2);
				cell.setCellType(1);
				cell.setCellValue((String) this.requestKeys.get(i));
			}
			cell = row.createCell(this.requestKeys.size() + 2);
			cell.setCellValue("Result");

			for (int i = 0; i < this.testCaseList.size(); i++) {
				TestCase stc = this.testCaseList.get(i);

				row = sheet.createRow(i + 1);
				cell = row.createCell(0);
				cell.setCellValue(stc.getCaseId());
				cell = row.createCell(1);
				cell.setCellValue(stc.getEscenaryId());

				for (int n = 0; n < this.requestKeys.size(); n++) {
					cell = row.createCell(n + 2);
					cell.setCellValue((String) stc.getServiceParmeters().get(this.requestKeys.get(n)));
				}
				cell = row.createCell(stc.getServiceParmeters().size() + 2);
				cell.setCellFormula("HYPERLINK(\"" + stc.getResponseFilePath() + "\")");
			}

			FileOutputStream fos = new FileOutputStream(
					new File(Configuration.getValue("property.general.outputFolder") + "/ResultReport.xls"));
			this.workbook.write(fos);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
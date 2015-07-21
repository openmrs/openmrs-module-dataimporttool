package org.openmrs.module.dataimporttool.dmt.xls;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.openmrs.module.dataimporttool.DataImportTool;

/**
 * A tool that reads data from XLS files. It uses JXL API
 * 
 */
public class XlsProcessor {
	private final DataImportTool config = new DataImportTool();
	private Workbook workbook;

	/**
	 * Default constructor
	 */
	public XlsProcessor() {
		// get the matching file using config info
		File inputWorkbook = new File(config.getMatchLocation()
				+ "/" + config.getMatchFile() + "."
				+ config.getMatchFormat());

		try {
			workbook = Workbook.getWorkbook(inputWorkbook);
		} catch (BiffException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Parameterized constructor
	 * 
	 * @param workbook
	 */
	public XlsProcessor(Workbook workbook) {
		this.workbook = workbook;
	}

	/**
	 * Return a table value regardless the data type
	 * 
	 * @param SHEET
	 *            the sheet of the value
	 * @param COLLUMN
	 *            the column of the value
	 * @param ROW
	 *            the row of the value
	 * @return
	 * @throws IOException
	 */
	public String process(final int SHEET, final int COLLUMN, final int ROW) {
		Sheet sheet = workbook.getSheet(SHEET);

		return sheet.getCell(COLLUMN, ROW).getContents();
	}
	
	/**
	 * Returns the number of rows of a certain sheet
	 * @param SHEET
	 * @return
	 */
	public int getSize(final int SHEET) {
		return workbook.getSheet(SHEET).getRows();
	}

}

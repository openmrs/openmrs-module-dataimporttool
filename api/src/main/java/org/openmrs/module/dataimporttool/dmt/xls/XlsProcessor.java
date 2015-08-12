/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

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

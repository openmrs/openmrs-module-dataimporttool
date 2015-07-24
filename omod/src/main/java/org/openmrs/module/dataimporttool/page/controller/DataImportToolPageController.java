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
package org.openmrs.module.dataimporttool.page.controller;

import java.util.List;

import org.openmrs.module.dataimporttool.DataImportTool;
import org.openmrs.module.dataimporttool.api.DataImportToolService;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.page.PageModel;
import org.openmrs.ui.framework.UiUtils;
import org.springframework.web.bind.annotation.RequestParam;



/**
 * Controller that handles the input of migration settings via the webUI
 * ".."
 */
public class DataImportToolPageController {

	public void get(@SpringBean DataImportToolService service,
			PageModel pageModel) {
		DataImportTool migrationSettings = service.getAllDataImportTools().get(0);
		pageModel.put("MigrationSettings", migrationSettings);	
		
	}

	public String get(@SpringBean DataImportToolService service,
			@RequestParam("matchFile") String matchFile, @RequestParam("matchFormat") String matchFormat,
			@RequestParam("matchLocation") String matchLocation, @RequestParam("leftDbDriver") String leftDbDriver,
			@RequestParam("leftUserName") String leftUserName, @RequestParam("leftPassword") String leftPassword, 
			@RequestParam("leftDbLocation") String leftDbLocation, @RequestParam String leftDbName, 
			@RequestParam("rightDbDriver") String rightDbDriver, @RequestParam("rightUserName") String rightUserName,
			@RequestParam("rightPassword") String rightPassword, @RequestParam("rightDbLocation") String rightDbLocation,
			@RequestParam("rightDbName") String rightDbName, @RequestParam("treeLimit") int treeLimit, 
			@RequestParam("allowCommit") boolean allowCommit, @RequestParam("resetProcess") boolean resetProcess,
			UiUtils ui) {

		DataImportTool dit = new DataImportTool(matchFile, matchFormat, matchLocation, leftDbDriver,leftUserName,leftPassword,
							leftDbLocation,leftDbName,rightDbDriver,rightUserName, rightPassword, 
							rightDbLocation, rightDbName, treeLimit, allowCommit, resetProcess);

		service.saveDataImportTool(dit);
		return "redirect:" + ui.pageLink("dataimporttool", "migrationSetting");

		
	}

	

}

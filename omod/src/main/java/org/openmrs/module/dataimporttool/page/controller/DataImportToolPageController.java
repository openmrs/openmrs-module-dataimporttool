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



/**
 * Controller that handles the input of migration settings via the webUI
 * ".."
 */
public class DataImportToolPageController {

	public void get(@SpringBean DataImportToolService service,
			PageModel pageModel) {
		List<DataImportTool> migrationSettings = service.getAllDataImportTools();
		pageModel.put("MigrationSettings", migrationSettings);	
		
	}

	

}

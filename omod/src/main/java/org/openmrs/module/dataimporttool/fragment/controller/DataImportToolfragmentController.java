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
package org.openmrs.module.dataimporttool.fragment.controller;

import java.util.List;
import org.openmrs.module.dataimporttool.DataImportTool;
import org.openmrs.module.dataimporttool.api.DataImportToolService;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentModel;



/**
 * DataImportTool fragment 
 * 
 */
public class DataImportToolfragmentController {


	public void controller(FragmentModel model, @SpringBean("ditService") DataImportToolService service) {

		DataImportTool dit = new DataImportTool();
		dit.setId(1);
        	dit.setTreeLimit(0);
        	dit.setAllowCommit(true);
        	dit.setResetProcess(false);
        	dit.setMatchFormat("xls");
        	dit.setLeftDbDriver("com.mysql.jdbc.Driver");
		dit.setLeftDbLocation("jdbc:mysql://localhost:3306/");
		dit.setLeftDbName("openmrs");
		dit.setRightDbDriver("sun.jdbc.odbc.JdbcOdbcDriver");
		dit.setRightDbLocation("jdbc:odbc:DRIVER={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=C:/Backup actual Cidade de XaiXai/");

 
        model.addAttribute("migrationSettings", service.getDataImportTool(1));
    }

}

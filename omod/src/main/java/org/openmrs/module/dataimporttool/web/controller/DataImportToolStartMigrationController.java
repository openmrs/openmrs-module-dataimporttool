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
package org.openmrs.module.dataimporttool.web.controller;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpSession;
import org.openmrs.module.dataimporttool.DataImportTool;
import org.openmrs.module.dataimporttool.api.DataImportToolService;
import org.springframework.web.servlet.mvc.SimpleFormController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * The main controller.
 * manages the migration settings for the DIT module.
 * behind the startMigrationSettings page.
 */
@Controller("dataimportool.DataImportToolStartMigrationController")
@RequestMapping("/module/dataimportool/startMigration")
public class  DataImportToolStartMigrationController extends SimpleFormController {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {

		//send some extra data for the view.
		
		HashMap<String, Boolean> dataMap = new HashMap<String, Boolean>();
		dataMap.put("Yes", true);
		dataMap.put("No", false);

		return dataMap;
	}

	@Override
	public ModelAndView onSubmit(Object command) throws ServletException {

		//user just submitted the form and form parameters are in the command obj.
		//this is where we would save form data to a db or invoke a service method
		// to save data.
		DataImportTool dit = (DataImportTool)command;
		log.info("Migration Settings submitted by user" + 
			dit.getMatchFile() +
			dit.getMatchFormat() +
			dit.getMatchLocation() +
			dit.getLeftDbDriver() +
			dit.getLeftUserName() +
			dit.getLeftPassword() +
			dit.getLeftDbLocation() +
			dit.getLeftDbName() +
			dit.getRightDbDriver() +
			dit.getRightUserName() +
			dit.getRightPassword() +
			dit.getRightDbLocation() +
			dit.getRightDbName() +
			dit.getTreeLimit() +
			dit.getAllowCommit() +
			dit.getResetProcess());

		return new ModelAndView(getSuccessView(), "startMigration", dit);
	}


	@Override
	protected DataImportTool formBackingObject(HttpServletRequest request) 
		throws ServletException {
		//populate the objec which will set values in our form.

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

		return dit;
	
	}
	

        /****

	@RequestMapping(value = "/module/dataimporttool/startMigration", method = RequestMethod.POST)
	public String startMigration(WebRequest request, HttpSession httpSession, ModelMap model,
                                   @RequestParam(required = false, value = "action") String action,
                                   @ModelAttribute("dit") DataImportTool dit, BindingResult errors) {
		
        	MessageSourceService mss = Context.getMessageSourceService();
       	 	DataImportToolService ditService = Context.getService(DataImportToolService.class);
		if (!Context.isAuthenticated()) {
            		errors.reject("dit.auth.required");

        	} else if (mss.getMessage("dit.purgeDataImportTool").equals(action)) {
			
			try {
                		ditService.purgeDataImportTool(dit);
                		httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "dit.delete.success");
                		return "redirect:ditList.list";

           		} catch (Exception ex) {
                		httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "dit.delete.failure");
                		log.error("Failed to Delete Migration Settings", ex);
                		return "redirect:startMigration.form?DataImportToolId=" + request.getParameter("MigrationSettingId");
            		}

		} else {
            		ditService.saveDataImportTool(dit);
            		httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "dit.saved");
        	}

       		return "redirect:startMigration.form";
    	}
       ***/
}

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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpSession;
import org.openmrs.web.WebConstants;
import org.openmrs.messagesource.MessageSourceService;
import org.openmrs.module.dataimporttool.DataImportTool;
import org.openmrs.module.dataimporttool.api.DataImportToolService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;



/**
 * The main controller.
 * manages the migration settings for the DIT module.
 * behind the startMigrationSettings page.
 */
@Controller("dataimportool.DataImportToolStartMigrationController")
@RequestMapping("/module/dataimportool/startMigration")
public class  DataImportToolStartMigrationController {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	@RequestMapping(value = "/module/dataimporttool/startMigration", method = RequestMethod.GET)
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
}

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
import javax.servlet.http.HttpSession;
import org.openmrs.api.context.Context;
import org.openmrs.web.WebConstants;
import org.openmrs.messagesource.MessageSourceService;
import org.openmrs.module.dataimporttool.DataImportTool;
import org.openmrs.module.dataimporttool.api.DataImportToolService;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.page.PageModel;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.annotation.BindParams;
import org.openmrs.ui.framework.annotation.InjectBeans;
import org.springframework.validation.BindingResult;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * Controller that handles the input of migration settings via the webUI
 * ".."
 */
public class DataImportToolPageController {

	private static final Logger log = LoggerFactory.getLogger(DataImportToolPageController.class);


	@RequestMapping(value = "/module/dataimporttool/MigrationSettingsForm.form", method = RequestMethod.POST)
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
                		return "redirect:MigrationSettingsForm.form?DataImportToolId=" + request.getParameter("MigrationSettingId");
            		}

		} else {
            		ditService.saveDataImportTool(dit);
            		httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "dit.saved");
        	}

       		return "redirect:ditList.list";
    }
}

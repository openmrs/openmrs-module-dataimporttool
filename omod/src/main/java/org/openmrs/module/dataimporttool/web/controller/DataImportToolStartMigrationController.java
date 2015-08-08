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

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.api.context.ServiceContext;
import org.springframework.stereotype.Controller;
import org.openmrs.module.dataimporttool.DataImportTool;
import org.openmrs.module.dataimporttool.api.DataImportToolService;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.BindException;





/**
 * This controller backs the /web/module/startMigration.jsp page. This controller is tied to that
 * jsp page in the /resources/webmoduleApplicationContext.xml file
 */
@Controller
@RequestMapping(value="/module/dataimporttool/addMigrationSettings.form")
public class  DataImportToolStartMigrationController extends SimpleFormController {
	
	protected final Log log = LogFactory.getLog(getClass());

	
	/**
	 * Initially called after the formBackingObject method to get the landing form name
	 * 
	 * @return String form view name
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String showForm() {
		return "/module/dataimporttool/addMigrationSettings";
	}

	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {

		//send some extra data for the view.
		
		HashMap<String, Boolean> dataMap = new HashMap<String, Boolean>();
		dataMap.put("Yes", true);
		dataMap.put("No", false);

		return dataMap;
	}

	/**
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.validation.BindException)
	 */
	@Override
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object object,
	                                BindException exceptions) throws Exception {
		
		if (Context.isAuthenticated()) {
		
			//Form entries to be validated.
			
			DataImportTool dit = (DataImportTool) object;
			DataImportToolService svc = (DataImportToolService) ServiceContext.getInstance().getService(DataImportToolService.class);
			svc.saveDataImportTool(dit);
		}
		
		//Move on to the next page
		return new ModelAndView("redirect:/module/dataimporttool/continueMigration.form");
	}

	
	/**
	 * This class returns the form backing object. This can be a string, a boolean, or a normal java
	 * pojo. The type can be set in the /config/webmoduleApplicationContext.xml file or it can be just
	 * defined by the return type of this method
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected DataImportTool formBackingObject(HttpServletRequest request) throws Exception {
		
		DataImportTool dit = null;
		
		if (Context.isAuthenticated()) {
			DataImportToolService svc = (DataImportToolService) ServiceContext.getInstance().getService(DataImportToolService.class);
			String ditId = request.getParameter("id");
			if (ditId != null)
				dit = svc.getDataImportTool(Integer.valueOf(ditId));
		}
		
		if (dit == null) {
			dit = new DataImportTool();
			dit.setId(0);
        		dit.setTreeLimit(0);
        		dit.setAllowCommit(true);
        		dit.setResetProcess(false);
        		dit.setMatchFormat("xls");
        		dit.setLeftDbDriver("com.mysql.jdbc.Driver");
			dit.setLeftDbLocation("jdbc:mysql://localhost:3306/");
			dit.setLeftDbName("openmrs");
			dit.setRightDbDriver("sun.jdbc.odbc.JdbcOdbcDriver");
			dit.setRightDbLocation("jdbc:odbc:DRIVER={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=C:/Backup actual Cidade de XaiXai/");

		}
		
		return dit;
	}
}

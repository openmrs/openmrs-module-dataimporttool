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

import javax.servlet.http.HttpServletRequest;

import org.openmrs.module.dataimporttool.DataImportTool;
import org.openmrs.module.dataimporttool.api.DataImportToolService;
import org.openmrs.module.dataimporttool.resources.DataImportToolValidator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;





/**
 * This controller backs the /web/module/startMigration.jsp page. This controller is tied to that
 * jsp page in the /resources/webmoduleApplicationContext.xml file
 */
@Controller
@RequestMapping(value="/module/dataimporttool/addMigrationSettings.form")
@SessionAttributes("dit")
public class  DataImportToolStartMigrationController {
	
	protected final Log log = LogFactory.getLog(getClass());

	/** Validator for this controller */
	private DataImportToolValidator validator;
	
	/**
	 * Constructor that registers validator
	 * 
	 * @param validator
	 */
	@Autowired
	public DataImportToolStartMigrationController(DataImportToolValidator validator) {
		this.validator = validator;
	}
	
	/**
	 * Displays the form to add a DataImportTool(Migration Settings)
	 * 
	 * @param request
	 * @param model
	 * @return new ModelAndView
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showForm(HttpServletRequest request, ModelMap model) throws ServletRequestBindingException{
	
		DataImportTool dit;
		Integer Id = ServletRequestUtils.getIntParameter(request, "Id");
		
		if (Id != null) {
			dit = Context.getService(DataImportToolService.class).getDataImportTool(Id);
		} else {
			dit = new DataImportTool();
		}
		
		model.addAttribute("dit", dit);
		return new ModelAndView("/module/dataimporttool/addMigrationSettings", model);
	}


	/**
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.validation.BindException)
	 */
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView onSubmit(@ModelAttribute("dit") DataImportTool dit, BindingResult result, SessionStatus status) {
		
		// validate form entries
		validator.validate(dit, result);
		
		if (result.hasErrors()) {
			return new ModelAndView("/module/dataimporttool/addMigrationSettings");
		}
		
		// add the new tag
		Context.getService(DataImportToolService.class).saveDataImportTool(dit);
		
		// clears the command object from the session
		status.setComplete();
		
		//Move on to the next page
		return new ModelAndView("redirect:/module/dataimporttool/continueMigration.page");
	}
}

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


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openmrs.api.context.Context;
import org.openmrs.module.dataimporttool.DataImportTool;
import org.openmrs.module.dataimporttool.api.DataImportToolService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/module/dataimporttool/continueMigration.list")
public class ContinueMigrationController {

		/** Logger for this class and subclasses */
		protected final Log log = LogFactory.getLog(this.getClass());
		
		/** Success form view name */
		private final String SUCCESS_FORM_VIEW = "/module/dataimporttool/status.list";
		
		/**
    	 * Starts Migration Process
    	 * @param HttpServletResponse
     	 */
		@RequestMapping(method = RequestMethod.GET)
		public ModelAndView showForm(HttpServletRequest request, ModelMap model) {
		
			//receives the parameters from the previous page and continues.
			log.info("Starting Data Migration");
			DataImportToolService ditService = Context.getService(DataImportToolService.class);
			
			//starts migration
			ditService.run();
		
			// Adding Migration Results to ModelMap
			// including runnable interface results.
			model.addAttribute("isRunning", ditService.isRunning());
			model.addAttribute("getResult", ditService.getResult());
			model.addAttribute("getPercent", ditService.getPercent());
			model.addAttribute("isCompleted", ditService.isCompleted());
			model.addAttribute("isStarted", ditService.isStarted());
			
			return new ModelAndView("/module/dataimporttool/continueMigration");
		}
     	 
     	 
     	@RequestMapping(method = RequestMethod.POST)
		public String continueMigration(SessionStatus status) {
			
			// clears the command object from the session
			status.setComplete();
			
			return SUCCESS_FORM_VIEW;
		}
}

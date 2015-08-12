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


@Controller
//@RequestMapping("/module/dataimporttool/continueMigration")
public class ContinueMigrationController {

		/** Logger for this class and subclasses */
		protected final Log log = LogFactory.getLog(this.getClass());

		/**
    	 * Starts Migration Process
    	 * @param HttpServletResponse
     	 */
     	@RequestMapping(value="/module/dataimporttool/continueMigration", method = RequestMethod.GET)
		public void continueMigration(ModelMap model) {
		
			DataImportTool dit;
			Object getResult;
			int getPercent;
			boolean isRunning, isCompleted, isStarted;
			Context.openSession();
			log.info("Starting Data Migration");
			dit = Context.getService(DataImportToolService.class).getDataImportTool();
			DataImportToolService ditService = Context.getService(DataImportToolService.class);
			ditService.run();//starts Migration process.
			
			// Adding Migration Results to ModelMap
			// including runnable interface results.
			model.addAttribute("isRunning", ditService.isRunning());
			model.addAttribute("getResult", ditService.getResult());
			model.addAttribute("getPercent", ditService.getPercent());
			model.addAttribute("isCompleted", ditService.isCompleted());
			model.addAttribute("isStarted", ditService.isStarted());
			
			Context.closeSession();
			
			return;
		}
}

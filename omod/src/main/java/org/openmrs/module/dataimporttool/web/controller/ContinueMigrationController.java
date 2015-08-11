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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



public class ContinueMigrationController {

		/** Logger for this class and subclasses */
		protected final Log log = LogFactory.getLog(this.getClass());

		/**
    	 * @see org.springframework.web.servlet.mvc.ParameterizableViewController#handleRequestInternal(javax.servlet.http.HttpServletRequest, 		 	 *	javax.servlet.http.HttpServletResponse)
     	 */
     	@RequestMapping("/module/dataimporttool/continueMigration")
		public void continueMigration(HttpServletRequest request) {
		
			DataImportTool dit;
			Context.openSession();
			log.info("Starting Data Migration");
			dit = Context.getService(DataImportToolService.class).getDataImportTool(0);
			Context.getService(DataImportToolService.class).run();//starts Migration process.
			Context.closeSession();
			
			return;
		}
}

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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.ServiceContext;
import org.openmrs.module.dataimporttool.api.DataImportToolService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;



public class ContinueMigrationController extends ParameterizableViewController{

	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());

	/**
    	 * @see org.springframework.web.servlet.mvc.ParameterizableViewController#handleRequestInternal(javax.servlet.http.HttpServletRequest, 		 *	javax.servlet.http.HttpServletResponse)
     	 */
    	@Override
    	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    	Map<String, Object> model = new HashMap<String, Object>();
	    
	    	DataImportToolService svc = (DataImportToolService)ServiceContext.getInstance().getService(DataImportToolService.class);
	    
	    	log.info("Starting Data Migration" + " for Migration Setting " + svc.getDataImportTool(0));
	    	model.put("Data Migration", svc.doMigration());
	    
	    	return new ModelAndView(getViewName(), model);
    	}
}

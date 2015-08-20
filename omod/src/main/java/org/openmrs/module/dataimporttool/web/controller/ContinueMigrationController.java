/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.dataimporttool.web.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openmrs.api.context.Context;
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
	private final String NEXT_FORM_VIEW = "/module/dataimporttool/continueMigration";

		
	/**
     * Shows Migration Progress
     * @param HttpServletResponse
     */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showMigration(ModelMap model, SessionStatus status) {
		
		
		return new ModelAndView(NEXT_FORM_VIEW);
	}
}

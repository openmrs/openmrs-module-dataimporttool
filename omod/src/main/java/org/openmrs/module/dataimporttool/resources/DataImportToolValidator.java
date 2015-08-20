/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.dataimporttool.resources;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.openmrs.module.dataimporttool.DataImportTool;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.BindException;



public class DataImportToolValidator implements Validator {

		public boolean supports(Class clazz) {
			return DataImportTool.class.equals(clazz);
		}
	
		//This validator only validates DataImportTool objects
		public void validate(Object obj, Errors e) {

			ValidationUtils.rejectIfEmptyOrWhitespace(e, "matchFile", "matchFile.empty");
			ValidationUtils.rejectIfEmpty(e, "leftDbDriver", "leftDbDriver.empty");
			ValidationUtils.rejectIfEmpty(e, "rightDbDriver", "rightDbDriver.empty");
			ValidationUtils.rejectIfEmpty(e, "MatchLocation", "MatchLocation.empty");
			ValidationUtils.rejectIfEmpty(e, "rightDbLocation", "rightDbLocation.empty");
			ValidationUtils.rejectIfEmpty(e, "leftDbLocation", "leftDbLocation.empty");
			ValidationUtils.rejectIfEmpty(e, "leftDbName", "leftDbName.empty");
			ValidationUtils.rejectIfEmpty(e, "rightDbName", "rightDbName.empty");


			DataImportTool dit = (DataImportTool) obj;
			if (dit.getMatchFormat().compareToIgnoreCase("xls") != 0)
				e.rejectValue("matchFormat", "Match format must be xls");

		}

	}

	






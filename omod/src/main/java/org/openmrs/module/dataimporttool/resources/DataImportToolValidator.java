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

	






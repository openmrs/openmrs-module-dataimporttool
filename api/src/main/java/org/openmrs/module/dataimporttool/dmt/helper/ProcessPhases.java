/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.dataimporttool.dmt.helper;

/**
 * System representation of the process phases
 *
 */
public interface ProcessPhases {
	
	/*
	 * Validates the matching logic and
	 * matching data
	 */
	public static final String VALIDATION = "VALID";
	/*
	 * Translates the matching objects into SQL INSERT and
	 * SELECT queries
	 */
	public static final String TRANSLATION = "TRANS";
	/*
	 * Executes the queries into the target databases
	 */
	public static final String EXECUTION = "EXEC";

}

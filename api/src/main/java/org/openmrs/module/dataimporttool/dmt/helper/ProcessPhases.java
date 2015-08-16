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

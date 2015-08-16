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

package org.openmrs.module.dataimporttool.dmt.util.log;

/**
 * This contract must be implemented by all the components performing logs
 *
 */
public interface LogIt {
	
	/**
	 * Write the log report at the end of the validation process
	 */
	public void logEndOfProcess();
	
	/**
	 * Writes a simple text report containing the phase and message
	 * 
	 * @param phase
	 * @param text
	 */
	public void writeSimpleInfoLog(final String phase, final String text);

}

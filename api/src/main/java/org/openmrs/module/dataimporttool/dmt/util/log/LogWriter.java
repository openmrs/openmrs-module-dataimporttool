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

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public final class LogWriter {

	private static LogWriter writer;

	/**
	 * Creates and returns a single instance of this class
	 * @return
	 */
	public static LogWriter getWriter() {

		if (writer == null) {
			writer = new LogWriter();
			DOMConfigurator.configure("api/src/main/resources/log4j.xml");//to be checked
		}
		return writer;
	}
	
	/**
	 * Write the event to the log file
	 * @param event
	 */
	public String writeLog(Event event) {
		Logger log = Logger.getLogger(this.getClass());
		
		if(event.getType().equals(Info.TYPE)) {
			log.info(event.toString());
		}
		if(event.getType().equals(Warning.TYPE)) {
			log.warn(event.toString());
		}
		if(event.getType().equals(Error.TYPE)) {
			log.error(event.toString());
		}
		
		return event.toString();
	}
}

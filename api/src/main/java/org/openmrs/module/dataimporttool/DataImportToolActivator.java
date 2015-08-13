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
package org.openmrs.module.dataimporttool;


import org.apache.commons.logging.Log; 
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.Activator;
import org.openmrs.GlobalProperty;
import org.openmrs.module.Extension;
import org.openmrs.module.Module;
import org.openmrs.module.ModuleFactory;


/**
 * This class contains the logic that is run every time this module is either started or stopped.
 */
public class DataImportToolActivator implements Activator {
	
	protected Log log = LogFactory.getLog(getClass());
		
	/**
	 * @see org.openmrs.module.Activator#startup()
	 */
	public void startup() {
		
		// create necessary default migration settings if they have not been created
		
		
		
		
		// configure extension points based on global properties
		
		
		log.info("Starting Data Import Tool");
	}
	
	/**
	 * @see org.openmrs.module.Activator#shutdown()
	 */
	public void shutdown() {
		
		// need to delete the migration settings stored here to assure that they aren't double-loaded on next startup
		
		log.info("Shutting down Patient Flags Module");
	}
		
}

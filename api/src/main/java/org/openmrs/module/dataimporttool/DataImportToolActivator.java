/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
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

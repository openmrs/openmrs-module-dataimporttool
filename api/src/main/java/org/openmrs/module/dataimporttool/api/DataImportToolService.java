/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.dataimporttool.api;

import java.util.List;

import org.openmrs.api.OpenmrsService;
import org.springframework.transaction.annotation.Transactional;
import org.openmrs.module.dataimporttool.DataImportTool;
import org.openmrs.module.dataimporttool.api.db.DataImportToolDAO;
import org.openmrs.module.dataimporttool.dmt.helper.SystemException;


/**
 * This service exposes module's core functionality. It is a Spring managed bean which is configured in moduleApplicationContext.xml.
 * <p>
 * It can be accessed only via Context:<br>
 * <code>
 * Context.getService(DataImportToolService.class).someMethod();
 * </code>
 * 
 * @see org.openmrs.api.context.Context
 */
@Transactional
public interface DataImportToolService extends OpenmrsService, Runnable {

		public int getPercent();
		
		public boolean isStarted();
		
		public boolean isCompleted();
		
		public void setRunning(boolean running);
		
		public boolean isRunning();
		
		public int getResult();
		
		public void run();
		
		public DataImportToolDAO getDao();
		
	 	/**
	 	 * This performs the migration of the data
	     * datatype
		 * 
	 	 * @param null
	 	 * @throws SystemException
	 	 */
		@Transactional
		public int doMigration();

		/**
         * Gets a list of migration settings.
         *
         * @return the DataImportTool list.
         */
        @Transactional(readOnly = true)
        List<DataImportTool> getAllDataImportTools();
        
        /**
         * Gets a migration setting for a given id.
         *
         * @return the dit with the given id
         */
         
        @Transactional(readOnly = true)
        DataImportTool getDataImportTool();
        /**
         * Saves a new or existing setting.
         *
         * @param dit the migration setting to save.
         * @return the saved setting.
         */
		@Transactional
        int saveDataImportTool(DataImportTool dit);
        
        /**
         * Deletes a migration setting from the database.
         *
         * @param dit the setting to delete.
         */
		@Transactional
        void purgeDataImportTool(DataImportTool dit);
}

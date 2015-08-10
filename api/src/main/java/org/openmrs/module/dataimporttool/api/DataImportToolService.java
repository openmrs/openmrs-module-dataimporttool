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
package org.openmrs.module.dataimporttool.api;

import java.util.List;

import org.openmrs.api.OpenmrsService;
import org.springframework.transaction.annotation.Transactional;
import org.openmrs.module.dataimporttool.DataImportTool;
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
         * @param id the dit id
         * @return the dit with the given id
         */
        @Transactional(readOnly = true)
        DataImportTool getDataImportTool(Integer Id);
        /**
         * Saves a new or existing setting.
         *
         * @param dit the migration setting to save.
         * @return the saved setting.
         */
	@Transactional
        DataImportTool saveDataImportTool(DataImportTool dit);
        /**
         * Deletes a migration setting from the database.
         *
         * @param dit the setting to delete.
         */
	@Transactional
        void purgeDataImportTool(DataImportTool dit);
}

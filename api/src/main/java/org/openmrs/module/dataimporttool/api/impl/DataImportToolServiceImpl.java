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
package org.openmrs.module.dataimporttool.api.impl;

import org.openmrs.api.impl.BaseOpenmrsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.openmrs.module.dataimporttool.ValidationManager;
import org.openmrs.module.dataimporttool.TranslationManager;
import org.openmrs.module.dataimporttool.api.DataImportToolService;
import org.openmrs.module.dataimporttool.dmt.dao.DAOFactory;
import org.openmrs.module.dataimporttool.dmt.helper.SystemException;

/**
 * It is a default implementation of {@link DataImportToolService}.
 */
public class DataImportToolServiceImpl extends BaseOpenmrsService implements DataImportToolService {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private DAOFactory dao;
	
	/**
    	 * @param dao the dao to set
    	 */
    	public void setDao(DAOFactory dao) {
	   	this.dao = dao;
    	}
    
    	/**
    	 * @return the dao
    	 */
   	public DAOFactory getDao() {
		return dao;
  	}

	/**
	 * doMigration performs the migration process.
	 *
	 * @throws SystemException
	 */
	@Override
	public void doMigration() throws SystemException {
		ValidationManager vm = new ValidationManager();
    		if(!vm.execute()) return;

    		TranslationManager tm = new TranslationManager(vm.getTree());
    		tm.execute();
	}

	
}

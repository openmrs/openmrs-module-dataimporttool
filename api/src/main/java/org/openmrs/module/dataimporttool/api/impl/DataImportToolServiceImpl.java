/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.dataimporttool.api.impl;


import java.util.List;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;
import org.openmrs.module.dataimporttool.api.DataImportToolService;
import org.openmrs.module.dataimporttool.ValidationManager;
import org.openmrs.module.dataimporttool.TranslationManager;
import org.openmrs.module.dataimporttool.DataImportTool;
import org.openmrs.module.dataimporttool.api.db.DataImportToolDAO;
import org.openmrs.module.dataimporttool.api.DataImportToolService;
import org.openmrs.module.dataimporttool.dmt.helper.SystemException;

/**
 * It is a default implementation of {@link DataImportToolService}.
 */
public class DataImportToolServiceImpl extends BaseOpenmrsService implements DataImportToolService {
	
		protected final Log log = LogFactory.getLog(this.getClass());
	
		private DataImportToolDAO dao;
		private DataImportTool dit = new DataImportTool();
		private int counter;
    	private int sum;
    	private boolean started;
    	private boolean running;
    	private int sleep;

    	public DataImportToolServiceImpl() {
    
        	counter = 0;
        	sum     = 0;
        	started = false;
        	running = false;
    	}

		@Override
		public synchronized int getPercent() {
    		return counter;
		}
	
		@Override
		public synchronized boolean isStarted() {
    		return started;
		}
		
		@Override
		public synchronized boolean isCompleted() {
    		return counter == 100;
		}
		
		@Override
		public synchronized void setRunning(boolean running) {
			this.running = running;
		}
	
		@Override
		public synchronized boolean isRunning() {
    		return running;
		}
		
        /**
         * @param dao the dao to set
         */
        public void setDao(DataImportToolDAO dao) {
                this.dao = dao;
        }
        
        @Override
        public synchronized int getResult() {
    		if (isCompleted())
        		return sum;
    		else
        		return 0;
		}

        /**
         * @return the dao
         */
        public DataImportToolDAO getDao() {
                return dao;
        }

        /**
         * @see org.openmrs.module.dataimporttool.api.DataImportToolService#getAllDataImportTools()
         */
        @Override
		@Transactional
        public List<DataImportTool> getAllDataImportTools() {
                return dao.getAllDataImportTools();
        }
        /**
         * @see org.openmrs.module.department.api.DataImportToolService#getDataImportTool(java.lang.Integer)
         * TODO: Since Object will be serialised to file, it will be passed to validation manager for migration
         */
        @Override
		@Transactional
    	public DataImportTool getDataImportTool() {
            	return dit;
    	}
        /**
         * @see org.openmrs.module.dataimporttool.api.DataImportToolService#saveDataImportTool(org.openmrs.module.dataimporttool.DataImportTool)
         * TODO: Object will be saved to a file for further processing or further use in future session.
         *
         */
        @Override
		@Transactional
        public int saveDataImportTool(DataImportTool dit) {
        		this.dit = dit;
                return 0;
        }
        /**
         * @see org.openmrs.module.dataimporttool.api.DataImportToolService#purgeDataImportTool(org.openmrs.module.dataimporttool.DataImportTool)
         */
        @Override
		@Transactional
        public void purgeDataImportTool(DataImportTool dit) {
        
	    	dao.purgeDataImportTool(dit);
        }

		/**
		 * doMigration performs the migration process.
	 	 *
	 	 * @throws SystemException
		 */
		@Override
		@Transactional
		public int doMigration() {
		
    		try {
        		Thread.sleep(sleep);
        		ValidationManager vm = new ValidationManager();
    			if(!vm.execute()) return -1;
				counter = 50;
				sum += counter;
		
    			TranslationManager tm = new TranslationManager(vm.getTree());
    			tm.execute();
    			counter = 100;
    			sum += counter;
       
    			} catch (SystemException | InterruptedException | Exception e ) {
        			setRunning(false);
        			return -1;
    			}			

			return 0;
		}
		
		@Override
		public void run() {
    		try {
        		setRunning(true);
        		while (isRunning() && !isCompleted())
            		doMigration();
            		
    		} finally {
        		setRunning(false);
    		}
    	}

	
}

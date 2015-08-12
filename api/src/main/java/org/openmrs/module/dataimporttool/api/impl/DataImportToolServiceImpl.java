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
        public synchronized Object getResult() {
    		if (isCompleted())
        		return new Integer(sum);
    		else
        		return null;
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
            	return dao.getDataImportTool();
    	}
        /**
         * @see org.openmrs.module.dataimporttool.api.DataImportToolService#saveDataImportTool(org.openmrs.module.dataimporttool.DataImportTool)
         * TODO: Object will be saved to a file for further processing or further use in future session.
         *
         */
        @Override
		@Transactional
        public DataImportTool saveDataImportTool(DataImportTool dit) {
                return dao.saveDataImportTool(dit);
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
       
    		} catch (SystemException | InterruptedException e ) {
        		setRunning(false);
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

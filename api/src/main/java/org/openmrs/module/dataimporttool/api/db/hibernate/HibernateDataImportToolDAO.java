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
package org.openmrs.module.dataimporttool.api.db.hibernate;

import java.util.List;
import org.openmrs.module.dataimporttool.DataImportTool;
import org.openmrs.module.dataimporttool.api.db.DataImportToolDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;


/**
 * It is a default implementation of  {@link DataImportToolDAO}.
 */
public class HibernateDataImportToolDAO implements DataImportToolDAO {
		protected final Log log = LogFactory.getLog(this.getClass());
	
		private SessionFactory sessionFactory;
	
		/**
     	 * @param sessionFactory the sessionFactory to set
     	 */
     	public void setSessionFactory(SessionFactory sessionFactory) {
	   		this.sessionFactory = sessionFactory;
    	}
    
		/**
     	 * @return the sessionFactory
    	 */
    	public SessionFactory getSessionFactory() {
	   		return sessionFactory;
   	 	}

		@Override
		@Transactional
		public int runUpdateQuery(String queryString) {
			Query q = sessionFactory.getCurrentSession().createQuery(queryString);
			return q.executeUpdate();
		}

		@Override
		@Transactional
		public List runListQuery(String queryString) {
			Query q = sessionFactory.getCurrentSession().createQuery(queryString);
			return q.list();
		}

		/**
         * @see org.openmrs.module.dataimporttool.api.db.DataImportToolDAO#getAllDepartments()
         */
        @Override
		@Transactional
        public List<DataImportTool> getAllDataImportTools() {
                return sessionFactory.getCurrentSession().createCriteria(DataImportTool.class).list();
        }
        
        /**
         * @see org.openmrs.module.dataimporttool.api.DataImportToolService#getDataImportTool(java.lang.Integer)
         */
        @Override
		@Transactional
        public DataImportTool getDataImportTool() {
        	
                return (DataImportTool)sessionFactory.getCurrentSession().createCriteria(DataImportTool.class).list().get(0);
        }
        
        /**
         * @see org.openmrs.module.dataimporttool.api.db.DataImportToolDAO#saveDataImportTool(org.openmrs.module.dataimporttool.DataImportTool)
         */
        @Override
		@Transactional
        public DataImportTool saveDataImportTool(DataImportTool dit) {
                sessionFactory.getCurrentSession().save(dit);
                return dit;
        }
        
        /**
         * @see org.openmrs.module.dataimporttool.api.db.DataImportToolDAO#purgeDataImportTool(org.openmrs.module.dataImportTool.DataImportTool)
         */
        @Override
		@Transactional
        public void purgeDataImportTool(DataImportTool dit) {
                sessionFactory.getCurrentSession().delete(dit);
        }
}

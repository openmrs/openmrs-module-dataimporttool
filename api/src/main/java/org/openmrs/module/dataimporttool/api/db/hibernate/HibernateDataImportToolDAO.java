/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
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

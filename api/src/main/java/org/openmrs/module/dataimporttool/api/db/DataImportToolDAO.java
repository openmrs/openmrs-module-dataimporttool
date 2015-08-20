/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.dataimporttool.api.db;

import org.openmrs.module.dataimporttool.DataImportTool;
import org.hibernate.SessionFactory;

import java.util.List;

import org.openmrs.api.db.DAOException;
import org.springframework.transaction.annotation.Transactional;

/**
 *  Database methods for {@link DataImportToolService}.
 * 
 * This class is used to manipulate SQL-based databases using SQL native queries
 *
 */
@Transactional
public interface DataImportToolDAO {

	/**
	 * getSessionFactory
	 *
	 */
	public SessionFactory getSessionFactory();

	/**
	 * runUpdateQuery
	 *
	 * @param queryString
	 */
	public int runUpdateQuery(String queryString);

	/**
	 * runListQuery, executes the SQL queries on the list
 	 *
	 * @param queryString
	 * @return List
	 */
	public List runListQuery(String queryString);


	 /**
          * @see org.openmrs.module.dataimporttool.api.DataImportToolService#getAllDataImportTools()
          */
        public List<DataImportTool> getAllDataImportTools();
        /**
         * @see org.openmrs.module.dataimporttool.api.DataImportToolService#getDataImportTool(java.lang.Integer)
	 */
        public DataImportTool getDataImportTool();
        /**
         * @see org.openmrs.module.dataimportool.api.DataImportToolService#saveDataImportTool(org.openmrs.module.dataimporttool.DataImportTool)
         */
        public DataImportTool saveDataImportTool(DataImportTool dit);
        /**
         * @see org.openmrs.module.dataimportool.api.DataImportToolService#purgeDataImportTool(org.openmrs.module.dataimporttool.DataImportTool)
         */
        void purgeDataImportTool(DataImportTool dit);
}

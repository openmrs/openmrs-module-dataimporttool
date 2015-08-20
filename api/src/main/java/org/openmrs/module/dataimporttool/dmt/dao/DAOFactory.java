/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.dataimporttool.dmt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.openmrs.api.context.Context;

import org.hibernate.Session;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.dataimporttool.DataImportTool;
import org.openmrs.module.dataimporttool.api.DataImportToolService;
import org.openmrs.module.dataimporttool.dmt.helper.DAOTypes;
import org.openmrs.module.dataimporttool.dmt.helper.SystemException;

/**
 * This class is a factory that generates instances of {@link DatabaseUtil }
 *
 */
public final class DAOFactory {
	private DataImportTool dit; 
	private DatabaseUtil sourceDAO;
	private DatabaseUtil targetDAO;
	private static DAOFactory instance;
	private Connection connection;
	protected final Log log = LogFactory.getLog(this.getClass());

	private DAOFactory() {
		dit = Context.getService(DataImportToolService.class).getDataImportTool();
	}
	
	private DAOFactory(DataImportTool dit) {
		if( dit != null) 
			this.dit = dit;
		else
			this.dit = Context.getService(DataImportToolService.class).getDataImportTool();
	}
	
	/**
	 * This is a method that returns a single instance of this class
	 * @return
	 */
	public static DAOFactory getInstance() {
		if(instance == null) {
			instance = new DAOFactory();
		}
		return instance;
	}
	
	/**
	 * This is a method that returns a single instance of this class
	 * @param cr
	 * @return
	 */
	public static DAOFactory getInstance(DataImportTool dit) {
		if(instance == null) {
			instance = new DAOFactory(dit);
		}
		return instance;
	}

	/**
	 * This method returns a DAO based on its type (target or source)
	 * The drive name is configured before return
	 * @param type
	 * @return
	 * @throws SystemException
	 */
	public DatabaseUtil getDAO(DAOTypes type) throws SystemException {
		
			if (type == DAOTypes.SOURCE) {
				if( sourceDAO == null)
					sourceDAO = createDAOs(dit, type);

				try {
					// set the database driver class
					Class.forName(dit.getRightDbDriver());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				return sourceDAO;

			} else if (type == DAOTypes.TARGET) {
				// create DAO if not exists
				if( targetDAO == null) 
					targetDAO = createDAOs(dit, type);
					
				try {
					Class.forName(dit.getLeftDbDriver());
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				return targetDAO;

			} else {
				throw new SystemException("The type of datasource is invalid");
			}
		
	}
	
	/**
	 * Destroy all DAO resources
	 * @param type
	 */
	public void destroyDAO(DAOTypes type) throws SystemException {
		try {
			if (type == DAOTypes.SOURCE) {
				sourceDAO.close();
				sourceDAO = null;

			} else if (type == DAOTypes.TARGET) {
				targetDAO.close();
				targetDAO = null;
			} else {
				throw new SystemException("The type of datasource is invalid");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method creates a DAO based on the type of DS
	 * @param ds
	 * @return
	 * @throws SystemException
	 */
	private DatabaseUtil createDAOs(final DataImportTool dit, DAOTypes type)
			throws SystemException {
		// create connections DataImportTool Entity object.
		if (dit == null) {
			throw new SystemException(
					"The datasource info doesn't exist in entity object");
		}
		
		if (type == DAOTypes.TARGET) { //only for target data source
		
			try {
				Session session = Context.getService(DataImportToolService.class).getDao().getSessionFactory().openSession();
				connection = session.connection();
				connection.setAutoCommit(false);// disable auto-commit
				connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);			
							
				connection = DriverManager.getConnection(
				dit.getLeftDbLocation() + dit.getLeftDbName(),
				dit.getLeftUserName(), dit.getLeftPassword());

				if (connection != null)
					return new DatabaseUtil(connection);

			} catch (Exception e) {
				e.printStackTrace();
			}//end try - catch blog

		} else {
			
			try {	
				Session session = Context.getService(DataImportToolService.class).getDao().getSessionFactory().openSession();
				connection = session.connection();
				connection = DriverManager.getConnection(dit.getRightDbLocation() + dit.getRightDbName(),
														 dit.getRightUserName(), dit.getRightPassword());
				
				if (connection != null) 
					return new DatabaseUtil(connection);

			} catch (Exception e) {
				e.printStackTrace();
			} //end try-catch blog
		}

		return null;
	}
}

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
package org.openmrs.module.dataimporttool.dmt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.openmrs.api.context.Context;
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

	private DAOFactory() {
		dit = new DataImportTool();
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
				connection = DriverManager.getConnection(
					dit.getRightDbLocation() + dit.getRightDbName(),
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

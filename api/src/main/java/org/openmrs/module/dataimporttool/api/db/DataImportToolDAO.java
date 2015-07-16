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
package org.openmrs.module.dataimporttool.api.db;

import org.openmrs.module.dataimporttool.api.DataImportToolService;

import java.util.List;

import org.openmrs.api.db.DAOException;

/**
 *  Database methods for {@link DataImportToolService}.
 * 
 * This class is used to manipulate SQL-based databases using SQL native queries
 *
 */
public interface DataImportToolDAO {

	/**
	 * Allows to commit the transaction from outside
	 * 
	 * @throws DA0Exception
	 */
	public void commit() throws DAOException;

	/**
	 * Allows to rollback the transaction from outside
 	 *
	 * @throws DAOException
	 */
	public void rollback() throws DAOException;
	
	/**
	 * Allows to save the point to rollback from outside
	 *
	 * @throws DAOException
	 */
	public void setSavePoint() throws DAOException;

	/**
	 * This method is to execute a query in the database to retrieve data in rows & columns
	 *
	 * @param query
	 * @return list of list objects.
	 * @throws DAOException
	 */
	public List<List<Object>> executeQuery(String query) throws DAOException;

	/**
	 * This method close the connection
	 * 
	 * @throws DAOException
	 */
	public void close() throws DAOException;

	/**
	 * This method returns the name of retrieved table columns in a certain
	 * position
	 * 
	 * @param column
	 * @return
	 */
	public String getColumnName(int column)throws DAOException;

	/**
	 * This method map the database specific datatype into java language
	 * datatype
	 * 
	 * @param column
	 * @return
	 */
	public Class getColumnClass(int column) throws DAOException;

	/**
	 * This method returns the total number of columns
	 * 
	 * @return
	 */
	public int getColumnCount();

	/**
	 * This method returns the total number of rows
	 * 
	 * @return
	 */
	public int getRowCount();

	/**
	 * This method returns a value in specified position
	 * 
	 * @param aRow
	 * @param aColumn
	 * @return
	 */
	public Object getValueAt(int aRow, int aColumn);

	/**
	 * This method returns all data retrieved from the select statement executed
	 * 
	 * @return
	 */
	public List<List<Object>> getRows();

	/**
	 * This method maps java datatype into database specific datatype
	 * 
	 * @param column
	 * @param value
	 * @return
	 */
	public String dbRepresentation(int column, Object value);

	/**
	 * This method execute query and return the PKs of affected records
	 * 
	 * @param query
	 * @return result
	 */
	public List<List<Object>> executeUpdate(String query) throws DAOException;

	/**
	 * This method execute prepared statement that can affect multiple columns
	 *
	 * @param query
	 * @param parameters
	 * @return
	 * @throws DAOException
	 */
	public int executePreparedStatement(String query, List<Object> parameters)
			throws DAOException;

	/**
	 * This method execute prepared statement to that can affect single column
	 * using id filtering
	 *
	 * @param query
	 * @param value
	 * @param target
	 * @return
	 * @throws DAOException
	 */
	public int executePreparedUpdate(String query, Object value, Object target)
			throws DAOException;

	/**
	 * This method takes a value and returns its string representation in the database
	 * 
	 * @param value
	 * @return
	 */
	public String cast(Object value) throws DAOException;

	/**
	 * This method aggregates the result set in a java { @link List }
	 *
	 * @return list of list objects
	 * @throws DAOException
	 */
	public List<List<Object>> constructRows() throws DAOException;

}

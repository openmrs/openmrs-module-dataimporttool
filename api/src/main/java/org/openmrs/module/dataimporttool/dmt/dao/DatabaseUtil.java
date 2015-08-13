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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.openmrs.api.context.Context;
import org.hibernate.Session;

import org.openmrs.module.dataimporttool.api.DataImportToolService;
import org.openmrs.module.dataimporttool.dmt.helper.MatchConstants;
import org.openmrs.module.dataimporttool.dmt.helper.SystemException;

/**
 * This class is used to manipulate SQL-based databases using SQL native queries
 * 
 * @author Valério João
 * @since May 22, 2008 Modified on August 23, 2008
 */
public class DatabaseUtil {

	private List<List<Object>> rows;
	private String[] columnNames = null;
	private ResultSetMetaData metaData;
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	/**
	 * Parameterized constructor
	 * 
	 * @param connection
	 *            the database connection
	 * @throws Exception
	 */
	public DatabaseUtil(Connection connection) throws Exception {
		this.connection = connection;
		try {
			if (this.connection != null) {
				statement = this.connection.createStatement();
			} else {
				Session session = Context.getService(DataImportToolService.class).getDao().getSessionFactory().getCurrentSession();
				this.connection = session.connection();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Allows to commit the transaction from outside
	 */
	public void commit() {
		try {
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Allows to rollback the transaction from outside
	 */
	public void rollback() {
		try {
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Allows to save the point to rollback from outside
	 */
	public void setSavePoint() {
		try {
			connection.setSavepoint();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is to execute a query in the database to retrieve data in
	 * rows & columns
	 * 
	 * @param query
	 * @return
	 */
	public List<List<Object>> executeQuery(String query) throws SystemException {
		// test if connection and statement were properly created
		if (connection == null || statement == null) {
			try {
				throw new Exception(
						"There is no database to execute the query.");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		try {
			resultSet = statement.executeQuery(query);// execute a given select
														// query
			return constructRows();
		} catch (SQLException ex) {
			System.out.println(query);
			ex.printStackTrace();
			throw new SystemException("Unable do execute SQL query");
		}
	}

	/**
	 * This method close the connection
	 * 
	 * @throws Exception
	 */
	public void close() throws Exception {
		// check if tools were created
		if (resultSet != null) {
			resultSet.close();
		}
		if (statement != null) {
			statement.close();
		}
		connection.close();
	}

	/**
	 * This method returns the name of retrieved table columns in a certain
	 * position
	 * 
	 * @param column
	 * @return
	 */
	public String getColumnName(int column) {
		if (columnNames[column] != null) {
			return columnNames[column];
		} else {
			return "";
		}
	}

	/**
	 * This method map the database specific datatype into java language
	 * datatype
	 * 
	 * @param column
	 * @return
	 */
	public Class getColumnClass(int column) {
		int type;
		try {
			type = metaData.getColumnType(column + 1);
		} catch (SQLException e) {
			return getColumnClass(column);
		}

		switch (type) {
		case Types.CHAR:
		case Types.VARCHAR:
		case Types.LONGVARCHAR:
			return String.class;

		case Types.BIT:
			return Boolean.class;

		case Types.TINYINT:
		case Types.SMALLINT:
		case Types.INTEGER:
			return Integer.class;

		case Types.BIGINT:
			return Long.class;

		case Types.FLOAT:
		case Types.DOUBLE:
			return Double.class;

		case Types.DATE:
			return java.sql.Date.class;

		default:
			return Object.class;
		}
	}

	/**
	 * This method returns the total number of columns
	 * 
	 * @return
	 */
	public int getColumnCount() {
		return columnNames.length;
	}

	/**
	 * This method returns the total number of rows
	 * 
	 * @return
	 */
	public int getRowCount() {
		return rows.size();
	}

	/**
	 * This method returns a value in specified position
	 * 
	 * @param aRow
	 * @param aColumn
	 * @return
	 */
	public Object getValueAt(int aRow, int aColumn) {
		List<Object> newRow = null;

		try {
			newRow = rows.get(aRow);
		} catch (ArrayIndexOutOfBoundsException ex) {
			ex.printStackTrace();
		}
		return newRow.get(aColumn);
	}

	/**
	 * This method returns all data retrieved from the select statement executed
	 * 
	 * @return
	 */
	public List<List<Object>> getRows() {
		return rows;
	}

	/**
	 * This method maps java datatype into database specific datatype
	 * 
	 * @param column
	 * @param value
	 * @return
	 */
	public String dbRepresentation(int column, Object value) {
		int type;

		if (value == null) {
			return "null";
		}

		try {
			type = metaData.getColumnType(column + 1);
		} catch (SQLException e) {
			return value.toString();
		}

		switch (type) {
		case Types.INTEGER:
		case Types.DOUBLE:
		case Types.FLOAT:
			return value.toString();
		case Types.BIT:
			return ((Boolean) value).booleanValue() ? "1" : "0";
		case Types.DATE:
			return value.toString(); // This will need some conversion.
		default:
			return "\"" + value.toString() + "\"";
		}
	}

	/**
	 * This method execute query and return the PKs of affected records
	 * 
	 * @param query
	 * @return result
	 */
	public List<List<Object>> executeUpdate(String query) throws SystemException {
		int result = 0;// number of affected rows
		try {
			result = statement.executeUpdate(query,
					Statement.RETURN_GENERATED_KEYS);
			if (result != 0) {
				resultSet = statement.getGeneratedKeys();
				return constructRows();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SystemException("Unable do execute SQL query");
		}
		return null;
	}

	/**
	 * This method execute prepared statement that can affect multiple columns
	 * @param query
	 * @param parameters
	 * @return
	 * @throws SQLException
	 * @throws SystemException
	 */
	public int executePreparedStatement(String query, List<Object> parameters)
			throws SQLException, SystemException {
		// declare the prepared statement
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(query);// start the statement
			Object parameter;// to hold parameter values
			// traverse the parameters
			for (int i = 0; i < parameters.size(); i++) {
				parameter = parameters.get(i);// retrieve parameter from
												// collection
				// check parameter type
				if (parameter instanceof String) {
					ps.setString(i + 1, (String) parameter);
				} else if (parameter instanceof Integer) {
					ps.setInt(i + 1, (Integer) parameter);
				} else if (parameter instanceof Date) {
					ps.setDate(i + 1, (Date) parameter);
				} else if (parameter instanceof Boolean) {
					ps.setBoolean(i + 1, (Boolean) parameter);
				} else if (parameter instanceof Long) {
					ps.setLong(i + 1, (Long) parameter);
				} else if (parameter instanceof Double) {
					ps.setDouble(i + 1, (Double) parameter);
				} else if (parameter instanceof Float) {
					ps.setFloat(i + 1, (Float) parameter);
				} else if (parameter instanceof byte[]) {
					ps.setBytes(i + 1, (byte[]) parameter);
				} else {
					ps.setObject(i + 1, parameter);
				}
			}
			// execute the query
			return ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SystemException("Unable do execute SQL query");
		} finally {
			if (ps != null) {
				ps.close();
			}
		}
	}

	/**
	 * This method execute prepared statement to that can affect single column
	 * using id filtering
	 * @param query
	 * @param value
	 * @param target
	 * @return
	 * @throws SQLException
	 * @throws SystemException
	 */
	public int executePreparedUpdate(String query, Object value, Object target)
			throws SQLException, SystemException {
		// declare the prepared statement
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(query);// start the statement
			// check parameter type
			if (value instanceof String) {
				ps.setString(1, (String) value);
				ps.setLong(2, (Long) target);
			} else if (value instanceof Integer) {
				ps.setInt(1, (Integer) value);
				ps.setLong(2, (Long) target);
			} else if (value instanceof Date) {
				ps.setDate(1, (Date) value);
				ps.setLong(2, (Long) target);
			} else if (value instanceof Boolean) {
				ps.setBoolean(1, (Boolean) value);
				ps.setLong(2, (Long) target);
			} else if (value instanceof Long) {
				ps.setLong(1, (Long) value);
				ps.setLong(2, (Long) target);
			} else if (value instanceof Double) {
				ps.setDouble(1, (Double) value);
				ps.setLong(2, (Long) target);
			} else if (value instanceof Float) {
				ps.setFloat(1, (Float) value);
				ps.setLong(2, (Long) target);
			} else if (value instanceof byte[]) {
				ps.setBytes(1, (byte[]) value);
				ps.setLong(2, (Long) target);
			} else {
				ps.setObject(1, value);
				ps.setLong(2, (Long) target);
			}
			// execute the query
			return ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new SystemException("Unable do execute SQL query");
		} finally {
			if (ps != null) {
				ps.close();
			}
		}
	}
	
	/**
	 * This method takes a value and returns its string representation in the database
	 * @param value
	 * @return
	 */
	public String cast(Object value) throws SystemException {
		if(value == null) {
			throw new SystemException("Invalid database value to cast");
		}
		
		String valueStr = value.toString();
		
		if(value instanceof java.util.Date) {
			String valueDateStr = valueStr.substring(0, valueStr.lastIndexOf('.'));
			
			return "#" + valueDateStr + "#";
		}
		
		if(!valueStr.matches("^[-+]?\\d+(\\.\\d+)?$")) {
			if(valueStr.equalsIgnoreCase(MatchConstants.NULL)) {
				return valueStr;
			}
			return "\'" + valueStr + "\'";
		}
		return valueStr;
	}

	/**
	 * This method aggregates the result set in a java { @link List }
	 */
	private List<List<Object>> constructRows() throws SQLException {
		metaData = resultSet.getMetaData(); // get the query result
		// assign the number of columns retrieved
		int numberOfColumns = metaData.getColumnCount();
		// array to hold the name of retrieved columns
		columnNames = new String[numberOfColumns];
		// Get the column names and cache them.
		for (int i = 0; i < numberOfColumns; i++) {
			columnNames[i] = metaData.getColumnLabel(i + 1);
		}
		// store each row
		rows = new ArrayList<List<Object>>();
		while (resultSet.next()) {
			List<Object> columns = new ArrayList<Object>();
			// traverse data and dispose them in columns & rows
			for (int column = 1; column <= getColumnCount(); column++) {
				columns.add(resultSet.getObject(column));
			}
			rows.add(columns); // store each row
		}
		return rows;
	}
}

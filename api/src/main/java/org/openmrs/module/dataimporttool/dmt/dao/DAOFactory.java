package org.esaude.dmt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.esaude.dmt.config.schema.DatasourceType;
import org.esaude.dmt.helper.DAOTypes;
import org.esaude.dmt.helper.SystemException;
import org.esaude.dmt.util.ConfigReader;

/**
 * This class is a factory that generates instances of {@link DatabaseUtil }
 * @author Valério João
 * @since 10-09-2014
 *
 */
public final class DAOFactory {
	private ConfigReader cr;
	private DatabaseUtil sourceDAO;
	private DatabaseUtil targetDAO;
	private static DAOFactory instance;
	private Connection connection;

	private DAOFactory() {
		cr = ConfigReader.getInstance();
	}
	
	private DAOFactory(ConfigReader cr) {
		this.cr = cr;
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
	public static DAOFactory getInstance(ConfigReader cr) {
		if(instance == null) {
			instance = new DAOFactory(cr);
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
		DatasourceType ds = null;

		if (type == DAOTypes.SOURCE) {
			ds = cr.getConfig().getSourceDs();
			// create DAO if not exists
			if (sourceDAO == null) {
				sourceDAO = createDAOs(ds, type);
			}
			// set the database driver class
			try {
				Class.forName(ds.getDriveName());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return sourceDAO;
		} else if (type == DAOTypes.TARGET) {
			ds = cr.getConfig().getTargetDs();
			// create DAO if not exists
			if (targetDAO == null) {
				targetDAO = createDAOs(ds, type);
			}
			// set the database driver class
			try {
				Class.forName(ds.getDriveName());
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
	public void destroy(DAOTypes type) throws SystemException {
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
	private DatabaseUtil createDAOs(final DatasourceType ds, DAOTypes type)
			throws SystemException {
		// create connections using config.xml
		if (ds == null) {
			throw new SystemException(
					"The datasource info doesn't exist in config.xml");
		}
		try {
			connection = DriverManager.getConnection(
					ds.getDatabaseLocation() + ds.getDatabaseName(),
					ds.getUsername(), ds.getPassword());
			// only for target DS
			if (type == DAOTypes.TARGET) {
				// only for target datasource
				try {
					connection.setAutoCommit(false);// disable auto-commit
					connection
							.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);// allow
																								// to
																								// access
																								// uncommitted
																								// data
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (connection != null) {
				return new DatabaseUtil(connection);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

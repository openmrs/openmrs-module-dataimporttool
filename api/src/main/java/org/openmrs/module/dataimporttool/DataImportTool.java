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
package org.openmrs.module.dataimporttool;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.Serializable;
import org.openmrs.BaseOpenmrsObject;
import org.openmrs.BaseOpenmrsMetadata;

/**
 * It is a model class. It should extend either {@link BaseOpenmrsObject} or {@link BaseOpenmrsMetadata}.
 */
public class DataImportTool extends BaseOpenmrsObject implements Serializable {

	//creates a logger for this class
	protected final Log logger = LogFactory.getLog(getClass());

	private static final long serialVersionUID = 1L;
	private Integer id;
	
	private int treeLimit = 0;                                    /* Limit on number of trees to be processed */
	private boolean allowCommit = true, resetProcess = false;     /* Further details during migration process */

	private String leftUserName,leftPassword, leftDbLocation, leftDbName;     /* Details of the left DB User for the Migration */
	private String rightUserName, rightPassword, rightDbLocation, rightDbName; /* Details for the right DB for the Migration */
	private String leftDbDriver, rightDbDriver;                   /* Left and Right DB Drivers */
	private String matchFile, matchFormat, matchLocation;                                     /* Details of the matching file */
	
	@Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * sets tree limit(the limit number of trees to be processed, zero (0) or negative (-x) = unlimited )
         * @param treeLimit
	 */
	public void setTreeLimit(int treeLimit) {
		this.treeLimit = treeLimit;
	}

	/**
	 * Gets the tree Limit
	 *
	 * @return treeLimit
	 */
	public int getTreeLimit() {
		return treeLimit;
	}

	/**
	 * Sets whether or not the results will be committed 
	 * @param allowCommit
	 */
	public void setAllowCommit(boolean allowCommit) {
		this.allowCommit = allowCommit;
	}

	/**
	 * Gets whether or not the results will be committed
	 *
	 * @return allowCommit
	 */
	public boolean getAllowCommit() {
		return allowCommit;

	}

	/**
	 * Sets return process
	 *
	 * @param returnProcess
	 */
	public void setResetProcess(boolean resetProcess) {
		this.resetProcess = resetProcess;
	}

	/**
	 * Gets return process
	 *
	 * @return returnProcess
	 */
	public boolean getResetProcess() {
		return resetProcess;
	}

	/*
	 * sets matching File 
	 * @param matchFile
         */
	public void setMatchFile(String matchFile) {
		this.matchFile = matchFile;
	}
	
	public void setMatchFormat(String matchFormat) {
		this.matchFormat = matchFormat;
	}

	public void setMatchLocation(String matchLocation) {
		this.matchLocation = matchLocation;
	}

	/**
	 * Gets the matching file
	 * 
	 * @return matchFile
	 */
	public String getMatchFile() {
		return matchFile;
	}

	public String getMatchFormat() {
		return matchFormat;
	}

	public String getMatchLocation() {
		return matchLocation;

	}

	/**
	 * sets the left database driver
	 *
	 * @param leftDbDriver
	 */
	public void setLeftDbDriver(String leftDbDriver) {
		this.leftDbDriver = leftDbDriver;
	}

	/**
	 * Gets the left DB Driver
	 *
	 * @return leftDbDriver
	 */
	public String getLeftDbDriver() {
		return leftDbDriver;
	}

	/**
	 * sets the left database Name
	 *
	 * @param leftDbName
	 */
	public void setLeftDbName(String leftDbName) {
		this.leftDbName = leftDbName;
	}

	/**
	 * Gets the left DB Name
	 *
	 * @return leftDbName
	 */
	public String getLeftDbName() {
		return leftDbName;
	}

	/**
	 * Sets left DB username
	 *
	 * @param leftUserName
	 */
	public void setLeftUserName(String leftUserName) {
		this.leftUserName = leftUserName;
	}

	/**
	 * Gets left DB username
	 *
	 * @return leftUserName
	 */
	public String getLeftUserName() {
		return leftUserName;
	}

	/**
	 * Sets left password
	 *
	 * @param leftPassword
	 */
	public void setLeftPassword(String leftPassword) {
		this.leftPassword = leftPassword;
	}

	/**
	 * Gets left password
	 *
	 * @return leftPassword
	 */
	public String getLeftPassword() {
		return leftPassword;
	}

	/**
	 * Sets left DB Location
	 *
	 * @param leftDbLocation
	 */
	public void setLeftDbLocation(String leftDbLocation) {
		this.leftDbLocation = leftDbLocation;
	}
	
	/**
	 * Gets left DB location
	 *
	 * @return leftDbLocation
	 */
	public String getLeftDbLocation() {
		return leftDbLocation;
	}

	/**
	 * sets the right database driver
	 *
	 * @param leftDbDriver
	 */
	public void setRightDbDriver(String rightDbDriver) {
		this.rightDbDriver = rightDbDriver;
	}

	/**
	 * Gets the right DB Driver
	 *
	 * @return rightDbDriver
	 */
	public String getRightDbDriver() {
		return rightDbDriver;
	}

	/**
	 * sets the Right database Name
	 *
	 * @param rightDbName
	 */
	public void setRightDbName(String rightDbName) {
		this.rightDbName = rightDbName;
	}

	/**
	 * Gets the right DB Name
	 *
	 * @return rightDbName
	 */
	public String getRightDbName() {
		return rightDbName;
	}


	/**
	 * Sets right DB username
	 *
	 * @param rightUserName
	 */
	public void setRightUserName(String RightUserName) {
		this.rightUserName = rightUserName;
	}

	/**
	 * Gets right DB username
	 *
	 * @return rightUserName
	 */
	public String getRightUserName() {
		return rightUserName;
	}

	/**
	 * Sets right password
	 *
	 * @param rightPassword
	 */
	public void setRightPassword(String rightPassword) {
		this.rightPassword = rightPassword;
	}

	/**
	 * Gets right password
	 *
	 * @return rightPassword
	 */
	public String getRightPassword() {
		return rightPassword;
	}

	/**
	 * Sets right DB Location
	 *
	 * @param rightDbLocation
	 */
	public void setRightDbLocation(String rightDbLocation) {
		this.rightDbLocation = rightDbLocation;
	}
	
	/**
	 * Gets right DB location
	 *
	 * @return rightDbLocation
	 */
	public String getRightDbLocation() {
		return rightDbLocation;
	}

	
	
}

/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.dataimporttool.dmt.util.log;

import java.util.Date;

/**
 * 
 * Class defines attribute for an INFO event.
 *
 */
public class Info extends Event {
	public final static String TYPE = "INFO";
	
	public Info() {
		super();
	}
	
	/**
	 * Parameterized constructor
	 * @param description
	 * @param phase
	 * @param timestamp
	 * @param tupleId
	 * @param partId
	 * @param partName
	 */
	public Info(String description, String phase, Date timestamp,
			int tupleId, int partId, String partName) {
		super(description, phase, timestamp, tupleId, partId, partName);
	}

	@Override
	public String getType() {
		return Info.TYPE;
	}
	
	@Override
	public String toString() {
		return Info.TYPE + getFase()
				+ getTupleId() + getPartName()
				+ getPartId() 
				+ getDescricao();
	}
}

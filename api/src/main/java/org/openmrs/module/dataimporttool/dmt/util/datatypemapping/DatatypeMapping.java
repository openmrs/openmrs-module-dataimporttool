/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.dataimporttool.dmt.util.datatypemapping;

import java.util.ArrayList;
import java.util.List;

/**
 * This class maps the datatypes based on their compatibility. The mapping itself
 * is created in a CSV file
 *
 */
public class DatatypeMapping {
	private String head;
	private String current;
	private List<String> members;
	private List<String> containedMappings;
	
	/**
	 * Default constructor
	 */
	public DatatypeMapping() {
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}
	
	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public List<String> getMembers() {
		if(members == null) {
			members = new ArrayList<String>();
		}
		return members;
	}

	public void setMembers(List<String> members) {
		this.members = members;
	}

	public List<String> getContainedMappings() {
		if(containedMappings == null) {
			containedMappings = new ArrayList<String>();
		}
		return containedMappings;
	}

	public void setSupportedMappings(List<String> containedMappings) {
		this.containedMappings = containedMappings;
	}
}

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

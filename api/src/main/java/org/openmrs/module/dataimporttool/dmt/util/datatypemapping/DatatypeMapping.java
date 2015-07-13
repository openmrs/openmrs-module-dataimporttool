package org.openmrs.module.dataimporttool.dmt.util.datatypemapping;

import java.util.ArrayList;
import java.util.List;

/**
 * This class maps the datatypes based on their compatibility. The mapping itself
 * is created in a CSV file
 * @author Valério João
 * @since 25-08-2014
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

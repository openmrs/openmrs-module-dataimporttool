package org.openmrs.module.dataimporttool.dmt.util.datatypemapping;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class reads a CSV file of datatype mapping and composes a data structure representation
 * of the mappings.
 * This mapping algorithm is as follows:
 * (1) The first value of each line is a datatype in OpenMRS database
 * (2) The first value of each line (head) is compatible with all the types that follows the line
 * (3) If the value that follows the line is also a head in another line, it means that there is a 
 * compatibility between the two lines:
 * 		(a) The containing line can receive the contained line values without any issue
 * 		(b) The contained line can be transformed to receive values of the containing line
 * (4) The logic assumes that the contained line is always on top of the containing line
 * 
 * @author Valério João
 * @since 25-08-2014
 *
 */
public final class DatatypeMappingReader {
	private boolean match;
	private BufferedReader br = null;
	private final String csvFile = "src/main/resources/datatype_mapping.csv";
	private Map<String, DatatypeMapping> headMappings;
	private Map<String, DatatypeMapping> currentMappings;
	
	public DatatypeMappingReader() {
		headMappings  = new HashMap<String, DatatypeMapping>();
		currentMappings  = new HashMap<String, DatatypeMapping>();
		try {
			br = new BufferedReader(new FileReader(csvFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Processes the CSV file and composes a data structure of datatype mapping
	 * @return
	 */
	public DatatypeMappingReader process() {
		final String CSV_SPLIT_BY = ";";
		headMappings = new HashMap<String, DatatypeMapping>();
		//read CSV file top down
		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] datatypes = line.split(CSV_SPLIT_BY);
				//the mapping object
				DatatypeMapping headMapping = new DatatypeMapping();
				headMapping.setCurrent(datatypes[0]);
				//set all the menbers of the group
				for(String member : datatypes) {
					headMapping.getMembers().add(member);
					//check if member is the head of a mapping line
					if(headMappings.containsKey(member)) {
						headMapping.getContainedMappings().add(member);
					}
					//create member mapping and store in map
					DatatypeMapping currentMapping = new DatatypeMapping();
					currentMapping.setCurrent(member);
					currentMapping.setHead(datatypes[0]);
					currentMappings.put(currentMapping.getCurrent(), currentMapping);
				}
				headMappings.put(headMapping.getCurrent(), headMapping);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	/**
	 * Verifies whether or not the right datatype matches with the left datatype
	 * @param left
	 * @param right
	 * @return
	 */
	public boolean verify(String left, String right) {
		match = false;//reset this variable because there is only one instance
		DatatypeMapping datatypeMapping = headMappings.get(left);
		//if left datatype doesnt exist in the CSV, there is no match
		if(datatypeMapping == null) {
			//datatype is a member
			DatatypeMapping currentMapping = currentMappings.get(left);
			if(currentMapping == null) {
				return false;
			}
			//get the head of the member
			datatypeMapping = headMappings.get(currentMapping.getHead());
		}
		checkLine(datatypeMapping, right);
		return match;
	}
	
	/**
	 * Recursively look for contained mapping lines
	 * @param datatypeMapping
	 * @param right
	 * @return
	 */
	private void checkLine(DatatypeMapping datatypeMapping, String right) {
		if(datatypeMapping.getMembers().contains(right)) {
			match = true;
		} else {
			for(String containedMapping : datatypeMapping.getContainedMappings()) {
				checkLine(headMappings.get(containedMapping), right);
			}
		}
	}
}

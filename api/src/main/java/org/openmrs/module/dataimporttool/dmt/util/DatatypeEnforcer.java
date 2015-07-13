package org.openmrs.module.dataimporttool.dmt.util;

import org.openmrs.module.dataimporttool.dmt.helper.MatchConstants;
import org.openmrs.module.dataimporttool.dmt.helper.SystemException;

/**
 * This class enforces string values to be of a certain datatype format
 * @author Valério João
 * @since 07-11-2014
 *
 */
public class DatatypeEnforcer {
	
	/**
	 * This method enforces a String value to be a datatype specific format
	 * @param datatype
	 * @param value
	 * @return
	 * @throws SystemException
	 */
	public String enforce(String datatype, Object value)  throws SystemException {
		Object enforced = null;
		// value should not be null
		if (value == null) {
			throw new SystemException("The enforced value is null");
		}
		String valueStr = value.toString();
		//enforce based on datatype
		if(datatype.equalsIgnoreCase(MatchConstants.DOUBLE)) {
			enforced = enforceDouble(valueStr);
		} else {
			enforced = value;
		}
		return cast(enforced);
	}

	/**
	 * This method enforces a String value to be Double format
	 * @param value the value to be enforced
	 * @return
	 * @throws SystemException
	 */
	private Double enforceDouble(String value) throws SystemException {
		// replace all the commas to dot
		value = value.replace(',', '.');
		// final value should be casted to double
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException nfe) {
			throw new SystemException(
					"Failed to enforce the argument value: " + value + " to double");
		}
	}
	
	/**
	 * This method takes a value and returns its string representation in the database
	 * @param value
	 * @return
	 */
	private String cast(Object value) throws SystemException {
		String valueStr = value.toString();
		
		if(!valueStr.matches("^[-+]?\\d+(\\.\\d+)?$")) {
			if(valueStr.equalsIgnoreCase(MatchConstants.NULL)) {
				return valueStr;
			}
			return "\'" + valueStr + "\'";
		}
		return valueStr;
	}

}

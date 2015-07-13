package org.openmrs.module.dataimporttool.dmt.helper;

/**
 * A system specific exception class
 * @author Valério João
 * @since 27-08-2014
 *
 */
public class SystemException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SystemException() {
		super();
	}
	
	public SystemException(String msg) {
		super(msg);
	}
}

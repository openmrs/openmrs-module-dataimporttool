package org.openmrs.module.dataimporttool.dmt.util.log;

/**
 * This contract must be implemented by all the components performing logs
 * @author Valério João
 * @since 17-12-2014
 *
 */
public interface LogIt {
	
	/**
	 * Write the log report at the end of the validation process
	 */
	public void logEndOfProcess();
	
	/**
	 * Writes a simple text report containing the phase and message
	 * 
	 * @param phase
	 * @param text
	 */
	public void writeSimpleInfoLog(final String phase, final String text);

}

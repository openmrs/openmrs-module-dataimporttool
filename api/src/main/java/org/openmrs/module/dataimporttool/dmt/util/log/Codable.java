package org.openmrs.module.dataimporttool.dmt.util.log;

/**
 * Contract for events that must log a code
 * @author Valério João
 * @since 21-08-2014
 *
 */
public interface Codable {
	
	public String getCodigo();
	
	public void setCodigo(String codigo);

}

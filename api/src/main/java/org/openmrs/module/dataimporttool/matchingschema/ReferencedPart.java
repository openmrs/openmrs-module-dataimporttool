package org.openmrs.module.dataimporttool.matchingschema;

import java.util.Map;

/**
 * This interface is implemented by matching classes that contains r
 * eferences (L-References or L-References)
 * @author Valério João
 * @since 17-09-2014
 *
 */
public interface ReferencedPart {
	
	/**
     * Gets the value of the leftReference property.
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReferenceType }
     * 
     * 
     */
    public Map<Integer, ReferenceType> getReferences();
	
	/**
     * Sets the value of the leftReference property.
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReferenceType }
     * 
     * 
     */
    public void setReferences(Map<Integer, ReferenceType> references);

}

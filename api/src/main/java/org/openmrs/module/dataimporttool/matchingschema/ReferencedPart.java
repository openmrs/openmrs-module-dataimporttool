/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.dataimporttool.matchingschema;

import java.util.Map;

/**
 * This interface is implemented by matching classes that contains
 * references (L-References or L-References)
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

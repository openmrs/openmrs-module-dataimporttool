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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.openmrs.module.dataimporttool.dmt.helper.ValidationStatuses;


/**
 * <p>Java class for referenceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 */
@SuppressWarnings("restriction")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "referenceType", propOrder = {
    "referencee",
    "referenced",
    "nameDesc",
    "datatype",
    "size",
    "predecessor",
    "referencedValue"
})
public class ReferenceType {

    @XmlElement(required = true)
    protected ReferenceSideType referencee;
    @XmlElement(required = true)
    protected ReferenceSideType referenced;
    @XmlElement(name = "name_desc")
    protected String nameDesc;
    @XmlElement(required = true)
    protected String datatype;
    @XmlElement(required = true)
    protected Integer size;
    @XmlElement(required = true)
    protected Integer predecessor;
    @XmlElement(required = true)
    protected Object referencedValue;
    @XmlAttribute(name = "id", required = true)
    protected Integer id;
    private List<ValidationStatuses> validationStatuses;

    /**
     * Gets the value of the referencee property.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceSideType }
     *     
     */
    public ReferenceSideType getReferencee() {
        return referencee;
    }

    /**
     * Sets the value of the referencee property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceSideType }
     *     
     */
    public void setReferencee(ReferenceSideType value) {
        this.referencee = value;
    }

    /**
     * Gets the value of the referenced property.
     * 
     * @return
     *     possible object is
     *     {@link ReferenceSideType }
     *     
     */
    public ReferenceSideType getReferenced() {
        return referenced;
    }

    /**
     * Sets the value of the referenced property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferenceSideType }
     *     
     */
    public void setReferenced(ReferenceSideType value) {
        this.referenced = value;
    }

    /**
     * Gets the value of the nameDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameDesc() {
        return nameDesc;
    }

    /**
     * Sets the value of the nameDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameDesc(String value) {
        this.nameDesc = value;
    }

    /**
     * Gets the value of the datatype property.
     * 
     */
    public String getDatatype() {
        return datatype;
    }

    /**
     * Sets the value of the datatype property.
     * 
     */
    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }
    
    /**
     * Gets the value of the size property.
     * 
     */
    public Integer getSize() {
        return size;
    }

    /**
     * Sets the value of the size property.
     * 
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * Gets the value of the predecessor property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPredecessor() {
        return predecessor;
    }

    /**
     * Sets the value of the predecessor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPredecessor(Integer value) {
        this.predecessor = value;
    }

    /**
     * Gets the value of the referencedValue property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getReferencedValue() {
        return referencedValue;
    }

    /**
     * Sets the value of the referencedValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setReferencedValue(Object value) {
        this.referencedValue = value;
    }
    
    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }
    
    public List<ValidationStatuses> getValidationStatuses() {
		if(validationStatuses == null) {
			validationStatuses = new ArrayList<ValidationStatuses>();
		}
		return validationStatuses;
	}

}

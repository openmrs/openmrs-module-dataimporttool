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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.openmrs.module.dataimporttool.dmt.helper.ValidationStatuses;


/**
 * <p>Java class for matchType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 */
@SuppressWarnings("restriction")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "matchType", propOrder = {
	"tupleId",
    "terminology",
    "left",
    "right",
    "valueMatchId",
    "references"
})
public class MatchType implements ReferencedPart {

	@XmlElement(required = true)
	private Integer tupleId;
    @XmlElement(required = true)
    protected String terminology;
    @XmlElement(required = true)
    protected MatchSideType left;
    @XmlElement(required = true)
    protected MatchSideType right;
    @XmlElement(defaultValue = "false")
    protected Object valueMatchId;
    protected Map<Integer, ReferenceType> references;//R-References
    @XmlAttribute(name = "id", required = true)
    @XmlElement(required = false)
    protected Object defaultValue;
    @XmlElement(required = true)
    protected Integer id;
    @XmlElement(required = true)
	private String pk;
    @XmlElement(required = true)
	private String pool;
    private List<ValidationStatuses> validationStatuses;

    /**
     * Gets the value of the terminology property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerminology() {
        return terminology;
    }

    /**
     * Sets the value of the terminology property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminology(String value) {
        this.terminology = value;
    }

    /**
     * Gets the value of the left property.
     * 
     * @return
     *     possible object is
     *     {@link MatchSideType }
     *     
     */
    public MatchSideType getLeft() {
        return left;
    }

    /**
     * Sets the value of the left property.
     * 
     * @param value
     *     allowed object is
     *     {@link MatchSideType }
     *     
     */
    public void setLeft(MatchSideType value) {
        this.left = value;
    }

    /**
     * Gets the value of the right property.
     * 
     * @return
     *     possible object is
     *     {@link MatchSideType }
     *     
     */
    public MatchSideType getRight() {
        return right;
    }

    /**
     * Sets the value of the right property.
     * 
     * @param value
     *     allowed object is
     *     {@link MatchSideType }
     *     
     */
    public void setRight(MatchSideType value) {
        this.right = value;
    }

    /**
     * Gets the value of the valueMatchId property.
     * 
     */
    public Object getValueMatchId() {
        return valueMatchId;
    }

    /**
     * Sets the value of the valueMatchId property.
     * 
     */
    public void setValueMatchId(Object value) {
        this.valueMatchId = value;
    }

    /**
     * Gets the value of the rightReference property.
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReferenceType }
     * 
     * 
     */
    public Map<Integer, ReferenceType> getReferences() {
    	if(references == null) {
    		references = new HashMap<Integer, ReferenceType>();
    	}
        return this.references;
    }
    
    /**
     * Sets the value of the rightReference property.
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReferenceType }
     * 
     * 
     */
    public void setReferences(Map<Integer,ReferenceType> references) {
        this.references =  references;
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

    /**
     * Gets the value of the defaultValue property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
	public Object getDefaultValue() {
		return defaultValue;
	}

	/**
     * Sets the value of the defaultValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
     * Gets the value of the tupleId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
	public Integer getTupleId() {
		return tupleId;
	}

	/**
     * Sets the value of the tupleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
	public void setTupleId(Integer tupleId) {
		this.tupleId = tupleId;
	}

	public List<ValidationStatuses> getValidationStatuses() {
		if(validationStatuses == null) {
			validationStatuses = new ArrayList<ValidationStatuses>();
		}
		return validationStatuses;
	}

	/**
     * Gets the value of the pk property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	public String isPk() {
		return pk;
	}

	/**
     * Sets the value of the pk property.
     * 
     * @param
     *     possible object is
     *     {@link String }
     *     
     */
	public void setPk(String pk) {
		this.pk = pk;
	}

	/**
     * Sets the value of the pool property.
     * 
     * @param
     *     possible object is
     *     {@link String }
     *     
     */
	public void setPool(String pool) {
		this.pool = pool;
		
	}
	
	/**
     * Gets the value of the pool property.
     * 
     * return
     *     possible object is
     *     {@link String }
     *     
     */
	public String setPool() {
		return pool;
		
	}
}

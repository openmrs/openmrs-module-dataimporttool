/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
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


/**
 * <p>Java class for tupleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 */
@SuppressWarnings("restriction")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tupleType", propOrder = {
    "terminology",
    "table",
    "desc",
    "matches",
    "references"
})
public class TupleType implements ReferencedPart {

    @XmlElement(required = true)
    protected String terminology;
    @XmlElement(required = true)
    protected String table;
    @XmlElement(required = true)
    protected String desc;
    @XmlElement(required = true)
    protected List<MatchType> matches;
    protected Map<Integer, ReferenceType> references;//L-References
    @XmlAttribute(name = "id", required = true)
    protected Integer id;

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
     * Gets the value of the table property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTable() {
        return table;
    }

    /**
     * Sets the value of the table property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTable(String value) {
        this.table = value;
    }

    /**
     * Gets the value of the desc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets the value of the desc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesc(String value) {
        this.desc = value;
    }

    /**
     * Gets the value of the match property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the match property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMatch().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MatchType }
     * 
     * 
     */
    public List<MatchType> getMatches() {
        if (matches == null) {
            matches = new ArrayList<MatchType>();
        }
        return this.matches;
    }

    /**
     * Gets the value of the leftReference property.
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
     * Sets the value of the leftReference property.
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ReferenceType }
     * 
     * 
     */
    public void setReferences(Map<Integer, ReferenceType> references) {
        this.references = references;
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

}

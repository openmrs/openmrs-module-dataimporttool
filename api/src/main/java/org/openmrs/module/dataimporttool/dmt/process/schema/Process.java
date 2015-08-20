/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.dataimporttool.dmt.process.schema;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="last_stop_point" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="last_stop_date" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="last_stop_status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "lastStopPoint",
    "lastStopDate",
    "lastStopStatus"
})
@XmlRootElement(name = "process")
public class Process {

    @XmlElement(name = "last_stop_point", required = true)
    protected BigInteger lastStopPoint;
    @XmlElement(name = "last_stop_date", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastStopDate;
    @XmlElement(name = "last_stop_status", required = true)
    protected String lastStopStatus;

    /**
     * Gets the value of the lastStopPoint property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getLastStopPoint() {
        return lastStopPoint;
    }

    /**
     * Sets the value of the lastStopPoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setLastStopPoint(BigInteger value) {
        this.lastStopPoint = value;
    }

    /**
     * Gets the value of the lastStopDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastStopDate() {
        return lastStopDate;
    }

    /**
     * Sets the value of the lastStopDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastStopDate(XMLGregorianCalendar value) {
        this.lastStopDate = value;
    }

    /**
     * Gets the value of the lastStopStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastStopStatus() {
        return lastStopStatus;
    }

    /**
     * Sets the value of the lastStopStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastStopStatus(String value) {
        this.lastStopStatus = value;
    }

}

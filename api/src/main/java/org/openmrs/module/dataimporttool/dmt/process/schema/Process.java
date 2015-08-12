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

/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.dataimporttool.dmt.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.Result;

import org.openmrs.util.OpenmrsClassLoader;
import org.openmrs.module.dataimporttool.dmt.process.schema.Process;

/**
 * Tool that reads info from XML configuration file for translation/execution process. This tool uses JAXB API to
 * unmarshal/marshal XML into Java Objects.
 * Note: This tool needs to be refactored since migration settings are read from the Entity class.
 * 
 *
 */
public final class ProcessReader {
	private static ProcessReader instance;
	private JAXBContext jaxbContext;

	private ProcessReader() {
		try {
			jaxbContext = JAXBContext.newInstance("org.openmrs.module.dataimporttool.dmt.process.schema");
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the unique instance of <link>ConfigReader</link>
	 * @return
	 */
	public static ProcessReader getInstance() {
		//create a new instance if doesnt exist
		if(instance == null) {
			instance = new ProcessReader();
		}
		return instance;
	}
	
	/**
	 * Used to get the configuration objects
	 * @return
	 */
	public Process getProcess() {

		Process process = null;
		try {
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			process  = (Process) jaxbUnmarshaller.unmarshal(OpenmrsClassLoader.getInstance().getResourceAsStream("process.xml"));

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return process;
	}
	
	/**
	 * Write the process stop information to process.xml file
	 * @param stop
	 * @param status
	 */
	public void recordProcess(Integer stop, Date date, String status) {
		
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(date);
		XMLGregorianCalendar xmlDate = null;
		try {
			xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
        
		Process process = new Process();
		process.setLastStopPoint(new BigInteger(stop.toString()));
		process.setLastStopDate(xmlDate);
		process.setLastStopStatus(status);
		
		try {
			File file = new File(OpenmrsClassLoader.getInstance().findResource("process.xml").getPath());
			Marshaller m = jaxbContext.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(process, file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
}

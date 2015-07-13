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

import org.openmrs.module.dataimporttool.dmt.App;
import org.openmrs.module.dataimporttool.dmt.process.schema.Process;

/**
 * Tool that reads info from XML configuration file for translation/execution process. This tool uses JAXB API to
 * unmarshal/marshal XML into Java Objects.
 * 
 * @author Valério João
 * @since 188-11-2014
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
			process  = (Process) jaxbUnmarshaller.unmarshal(new FileInputStream(App.MAIN_PATH + "/process.xml"));

		} catch (JAXBException | FileNotFoundException e) {
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
			File file = new File(App.MAIN_PATH + "/process.xml");
			Marshaller m = jaxbContext.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(process, file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
}

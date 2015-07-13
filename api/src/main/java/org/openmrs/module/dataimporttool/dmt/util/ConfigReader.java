package org.openmrs.module.dataimporttool.dmt.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.openmrs.module.dataimporttool.dmt.App;
import org.openmrs.module.dataimporttool.dmt.config.schema.Config;

/**
 * Tool that reads info from XML configuration file. This tool uses JAXB API to
 * unmarshal XML into Java OObjects
 * 
 * @author Valério João
 * @since 21-08-2014
 *
 */
public final class ConfigReader {
	private static ConfigReader instance;

	private ConfigReader() {
		
	}
	
	/**
	 * Get the unique instance of <link>ConfigReader</link>
	 * @return
	 */
	public static ConfigReader getInstance() {
		//create a new instance if doesnt exist
		if(instance == null) {
			instance = new ConfigReader();
		}
		return instance;
	}
	
	/**
	 * Used to get the configuration objects
	 * @return
	 */
	public Config getConfig(/*final String path*/) {

		Config config = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance("org.openmrs.module.dataimporttool.dmt.config.schema");

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			config  = (Config) jaxbUnmarshaller.unmarshal(new FileInputStream(App.MAIN_PATH + "/config.xml"));

		} catch (JAXBException | FileNotFoundException e) {
			e.printStackTrace();
		}
		return config;
	}
}

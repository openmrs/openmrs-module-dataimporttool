package org.openmrs.module.dataimporttool.dmt.util.log;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 *  Event Code, contains the migration error messages.
 *  NOTE: Should be moved to messages.properties file
 */
public class EventCode {
	private final String BUNDLE_NAME = "api/src/main/resources/messages.properties";
	private final Properties props = new Properties();

	public EventCode() {
		try {
			props.load(new FileInputStream(BUNDLE_NAME));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getString(String key) {
		return props.getProperty(key);
	}
}

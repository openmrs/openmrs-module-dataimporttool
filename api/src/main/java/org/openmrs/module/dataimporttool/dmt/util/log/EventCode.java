/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.dataimporttool.dmt.util.log;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.openmrs.util.OpenmrsClassLoader;


/**
 *  Event Code, contains the migration error messages.
 *  NOTE: Should be moved to messages.properties file
 */
public class EventCode {
	private final String BUNDLE_NAME = OpenmrsClassLoader.getInstance().findResource("messages.properties").getPath();
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

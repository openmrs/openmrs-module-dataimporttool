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

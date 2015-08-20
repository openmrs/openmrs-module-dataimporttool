/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.dataimporttool.api;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.dataimporttool.DataImportTool;


/**
 * Tests {@link ${DataImportToolService}}.
 * Tests the ability to read configurations from entity class.
 */
public class  DataImportToolServiceTest {

	private DataImportTool dit;

	@Before
	public void setUp() throws Exception {
		dit = new DataImportTool("matchFile", "xls", "matchLocation", "leftDbDriver",
				"leftUserName", "leftPassword", "leftDbLocation", "leftDbName", "rightDbDriver", 
				"rightUserName", "rightPassword", "rightDbLocation", "rightDbName", 1,
				false,false);
	}

	@Test
	public void testReadConfigData() {
		
		//Dummy tests checks proper creation of migration settings entity class
		assertNotNull(dit);
		assertNotNull(dit.getMatchFile());
		assertNotNull(dit.getMatchFormat());
		assertNotNull(dit.getMatchLocation());
		assertNotNull(dit.getResetProcess());
	}
	
}

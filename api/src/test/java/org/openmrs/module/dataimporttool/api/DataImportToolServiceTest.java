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

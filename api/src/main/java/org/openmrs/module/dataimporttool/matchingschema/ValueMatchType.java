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

package org.openmrs.module.dataimporttool.matchingschema;


import java.util.HashMap;
import java.util.Map;

import org.openmrs.module.dataimporttool.dmt.xls.Sheets;
import org.openmrs.module.dataimporttool.dmt.xls.XlsProcessor;



/**
 * This class builds the value mapping representation from the XLS mapping file
 *
 */
public class ValueMatchType {
	public static final Map<Integer, Map<String, String>> valueMatches = new HashMap<Integer, Map<String, String>>();
	private static final XlsProcessor pr = new XlsProcessor();

	static {
		int lastGroupId = 0;
		Map<String, String> valueMapGroup = null;
		for (int i = Sheets.VALUE_MATCH.ROW_START; i < pr
				.getSize(Sheets.VALUE_MATCH.INDEX); i++) {
			int groupId = Integer.valueOf(pr.process(Sheets.VALUE_MATCH.INDEX,
					Sheets.VALUE_MATCH.ID, i));

			// create a new group if id is new
			if (groupId != lastGroupId) {
				valueMapGroup = new HashMap<String, String>();
				valueMatches.put(groupId, valueMapGroup);
			}
			valueMapGroup.put(pr.process(Sheets.VALUE_MATCH.INDEX,
					Sheets.VALUE_MATCH.PK_R, i).toLowerCase(), pr.process(
					Sheets.VALUE_MATCH.INDEX, Sheets.VALUE_MATCH.PK_L, i));
			lastGroupId = groupId;
		}
	}
}

package org.openmrs.module.dataimporttool.matchingschema;


import java.util.HashMap;
import java.util.Map;

import org.openmrs.module.dataimporttool.dmt.xls.Sheets;
import org.openmrs.module.dataimporttool.dmt.xls.XlsProcessor;

/**
 * This class builds the value mapping representation from the XLS mapping file
 * @author Valério João
 * @since 19-10-2014
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

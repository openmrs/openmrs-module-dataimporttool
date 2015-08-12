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

package org.openmrs.module.dataimporttool.dmt.util;

import java.util.ArrayList;
import java.util.List;

import org.openmrs.module.dataimporttool.dmt.helper.SystemException;
import org.openmrs.module.dataimporttool.matchingschema.MatchSideType;
import org.openmrs.module.dataimporttool.matchingschema.MatchType;

/**
 * A builder that creates the {@link MatchType } instances
 * @author Valério João
 * @since 27-08-2014
 *
 */
public class MatchBuilder {
	private MatchType matchType;
	private List<MatchType> matches;

	public final static String LEFT_SIDE = "left";
	public final static String RIGHT_SIDE = "right";
	
	public MatchBuilder() {
		matches = new ArrayList<MatchType>();
	}
	
	/**
	 * Create a match of tuple with basic data
	 * @param tupleId
	 * @param id
	 * @param terminology
	 * @param hasValueMatch
	 * @param defaultValue
	 *  @param pool
	 * @return
	 * @throws SystemException
	 */
	public MatchBuilder createMatch(final Integer tupleId, final Integer id, final String terminology,
			final Object valueMatchId, final Object defaultValue, final String pk, final String pool) {

		matchType = new MatchType();
		matchType.setTupleId(tupleId);
		matchType.setId(id);
		matchType.setTerminology(terminology);
		matchType.setValueMatchId(valueMatchId);
		matchType.setDefaultValue(defaultValue);
		matchType.setPk(pk);
		matchType.setPool(pool);

		matches.add(matchType);

		return this;
	}

	/**
	 * Create the sides (LEFT and RIGHT) of the match
	 * @param table
	 * @param column
	 * @param type
	 * @param size
	 * @param isRequired
	 * @param side
	 * @return
	 * @throws SystemException
	 */
	public MatchBuilder createMatchSide(final String table, 
			final String column, 
			final String type, 
			final Integer size, 
			final String isRequired, 
			final String side) 
			throws SystemException {
		if(matchType == null) {
			throw new SystemException("Cannot create MatchTypeSide without create MatchType first");
		}
		MatchSideType matchSide = new MatchSideType();
		matchSide.setTable(table);
		matchSide.setColumn(column);
		matchSide.setDatatype(type);
		matchSide.setSize(size);
		matchSide.setIsRequired(isRequired);
		
		if(side.equalsIgnoreCase(MatchBuilder.LEFT_SIDE)) {
			matchType.setLeft(matchSide);
		}
		else if(side.equalsIgnoreCase(MatchBuilder.RIGHT_SIDE)) {
			matchType.setRight(matchSide);
		} else {
			throw new SystemException("Invalid match side parameter value");
		}
		return this;
	}
	
	public List<MatchType> process() {
		return matches;
	}
	
	public MatchType getMatch() {
		return matchType;
	}

}

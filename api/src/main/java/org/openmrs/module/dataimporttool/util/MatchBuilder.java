package org.esaude.dmt.util;

import java.util.ArrayList;
import java.util.List;

import org.esaude.dmt.helper.SystemException;
import org.esaude.matchingschema.MatchSideType;
import org.esaude.matchingschema.MatchType;

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

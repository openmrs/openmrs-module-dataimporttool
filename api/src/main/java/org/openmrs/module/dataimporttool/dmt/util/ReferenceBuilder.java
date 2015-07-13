package org.esaude.dmt.util;

import java.util.HashMap;
import java.util.Map;

import org.esaude.dmt.helper.SystemException;
import org.esaude.matchingschema.ReferenceSideType;
import org.esaude.matchingschema.ReferenceType;

/**
 * A builder that creates the {@link ReferenceType } instances
 * @author Valério João
 * @since 27-08-2014
 *
 */
public class ReferenceBuilder {
	private Map<Integer, ReferenceType> references;
	private ReferenceType referenceType;
	public final static String REFERENCEE = "referencee";
	public final static String REFERENCED = "referenced";
	
	public ReferenceBuilder() {
		references = new HashMap<Integer, ReferenceType>();
	}
	
	/**
	 * Creates the reference object and add it to a collection
	 * @param id
	 * @param datatype
	 * @param size
	 * @param nameDesc
	 * @param predecessor
	 * @return
	 */
	public ReferenceBuilder createReference(final Integer id, 
			final String datatype, 
			final Integer size, 
			final String nameDesc,
			final Integer predecessor) {
		referenceType = new ReferenceType();
		referenceType.setId(id);
		referenceType.setDatatype(datatype);
		referenceType.setSize(size);
		referenceType.setNameDesc(nameDesc);
		referenceType.setPredecessor(predecessor);
		
		references.put(id, referenceType);
		
		return this;
	}
	
	/**
	 * Create the reference side (LEFT or RIGHT)
	 * @param table
	 * @param column
	 * @param side
	 * @return
	 * @throws SystemException
	 */
	public ReferenceBuilder createReferenceSide(final String table, 
			final String column, 
			final String side) 
			throws SystemException {
		if(referenceType == null) {
			throw new SystemException("Cannot create ReferenceSideType without create ReferenceType first");
		}
		
		ReferenceSideType referenceSide = new ReferenceSideType();
		referenceSide.setTable(table);
		referenceSide.setColumn(column);
		
		if(side.equalsIgnoreCase(ReferenceBuilder.REFERENCEE)) {
			referenceType.setReferencee(referenceSide);
		}
		else if(side.equalsIgnoreCase(ReferenceBuilder.REFERENCED)) {
			referenceType.setReferenced(referenceSide);
		} else {
			throw new SystemException("Invalid reference side parameter value");
		}
		return this;
	}
	
	/**
	 * Create the referenced value (Constant or FK)
	 * @param value
	 * @return
	 * @throws SystemException
	 */
	public ReferenceBuilder createReferencedValue(Object value) 
			throws SystemException {
		if(referenceType == null) {
			throw new SystemException("Cannot create ReferenceValueType without create ReferenceType first");
		}
		referenceType.setReferencedValue(value);
		return this;
	}
	
	public Map<Integer, ReferenceType> processMap() {
		return references;
	}
	
	public ReferenceType getReference() {
		return referenceType;
	}
	
}

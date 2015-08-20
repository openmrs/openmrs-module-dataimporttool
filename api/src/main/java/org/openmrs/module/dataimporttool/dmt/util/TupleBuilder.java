/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.dataimporttool.dmt.util;

import org.openmrs.module.dataimporttool.dmt.helper.SystemException;
import org.openmrs.module.dataimporttool.matchingschema.TupleType;



/**
 * A builder that creates the {@link TupleType } instances
 *
 */
public class TupleBuilder {
	private TupleType tupleType;
	private final TupleTree tree = new TupleTree();

	public TupleBuilder() {
	}

	/**
	 * Create a tuple with basic data
	 * 
	 * @param id
	 * @param terminology
	 * @param table
	 * @param desc
	 * @return
	 */
	public TupleBuilder createTuple(final Integer id,
			final String terminology, 
			final String table, 
			final String desc,
			final Integer parent) throws SystemException {
		tupleType = new TupleType();
		tupleType.setId(id);
		tupleType.setTerminology(terminology);
		tupleType.setTable(table);
		tupleType.setDesc(desc);
		//set the head of the three if doesn't exist one
		if(parent == null) {
			tree.setHead(tupleType);
		} else {
			tree.getTree(parent).addLeaf(tupleType);
		}
		return this;
	}
	
	
	public TupleTree process() {
		return tree;
	}
	
	public TupleType getTuple() {
		return tupleType;
	}
}

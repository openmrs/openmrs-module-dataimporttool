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

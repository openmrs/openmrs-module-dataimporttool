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

import java.util.Date;

/**
 * 
 * Classe que define os atributos para eventos do tipo INFO
 * @author Valério João
 * @since 21-08-2014
 */
public class Info extends Event {
	public final static String TYPE = "INFO";
	
	public Info() {
		super();
	}
	
	/**
	 * Parameterized constructor
	 * @param descricao
	 * @param fase
	 * @param timestamp
	 * @param tupleId
	 * @param partId
	 * @param partName
	 */
	public Info(String descricao, String fase, Date timestamp,
			int tupleId, int partId, String partName) {
		super(descricao, fase, timestamp, tupleId, partId, partName);
	}

	@Override
	public String getType() {
		return Info.TYPE;
	}
	
	@Override
	public String toString() {
		return Info.TYPE + getFase()
				+ getTupleId() + getPartName()
				+ getPartId() 
				+ getDescricao();
	}
}

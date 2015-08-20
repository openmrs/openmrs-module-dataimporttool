/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.dataimporttool.dmt.util.log;

import java.util.Date;


public class Error extends Event implements Codable {
	
	private String codigo;
	public final static String TYPE = "ERROR"; 
	
	/**
	 * Default constructor
	 */
	public Error() {
		super();
	}
	
	/**
	 * Parameterized constructor
	 * @param descricao
	 * @param fase
	 * @param timestamp
	 * @param codigo
	 * @param tupleId
	 * @param partId
	 * @param partName
	 */
	public Error(String descricao, String fase, Date timestamp, String codigo, int tupleId, int partId , String partName) {
		super(descricao, fase, timestamp, tupleId, partId, partName);
		this.codigo = codigo;
	}

	public String getCodigo() {
		if(codigo != null || !codigo.isEmpty()) {
			return " " + codigo;
		}
		return "";
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public String getType() {
		return Error.TYPE;
	}
	
	@Override
	public String toString() {
		return Error.TYPE + 
				getCodigo() + 
				getFase() +
				getTupleId() +
				getPartName() + 
				getPartId() + 
				getDescricao();
	}
}

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
 * Classe que define os atributos para eventos do tipo WARNING
 * 
 * @author Edias Jambaia
 * @author Valério João
 * @since 21-08-2014
 */
public class Warning extends Event implements Codable {
	private String codigo;
	public final static String TYPE = "WARNING";

	/**
	 * Default constructor
	 */
	public Warning() {
		super();
	}

	/**
	 * Parameterized constructor
	 * 
	 * @param descricao
	 * @param fase
	 * @param timestamp
	 * @param codigo
	 * @param tupleId
	 * @param partId
	 * @param partName
	 */
	public Warning(String descricao, String fase, Date timestamp,
			String codigo, int tupleId, int partId, String partName) {
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
		return Warning.TYPE;
	}

	@Override
	public String toString() {
		return Warning.TYPE +
				getCodigo() +
				getFase() +
				getTupleId() + 
				getPartName() +
				getPartId() + 
				getDescricao();
	}
}

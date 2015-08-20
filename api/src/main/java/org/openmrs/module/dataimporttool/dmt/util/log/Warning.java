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

/**
 * 
 * Class defines attributes for a WARNING Event
 * 
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

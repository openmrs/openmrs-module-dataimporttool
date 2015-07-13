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

/**
 * 
 * Classe que define os atributos para eventos do tipo ERROR
 * 
 * @author Edias Jambaia
 * @author Valério João
 * @since 21-08-2014
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

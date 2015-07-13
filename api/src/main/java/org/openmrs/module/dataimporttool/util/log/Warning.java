package org.esaude.dmt.util.log;

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

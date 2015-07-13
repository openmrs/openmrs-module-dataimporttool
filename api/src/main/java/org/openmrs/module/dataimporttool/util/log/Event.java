package org.esaude.dmt.util.log;

import java.util.Date;

/**
 * Classe que define um evento para Log
 * 
 * @author Edias Jambaia
 * @author Valério João
 * @since 21-08-2014
 */
public abstract class Event {

	private String descricao;
	private String fase;
	private Date timestamp;
	private int tupleId;
	private int partId;
	private String partName;

	/**
	 * Default constructor
	 */
	public Event() {
	}

	/**
	 * Parameterized constructor
	 * 
	 * @param descricao
	 * @param fase
	 * @param timestamp
	 */
	public Event(String descricao, String fase, Date timestamp, int tupleId,
			int partId, String partName) {
		this.descricao = descricao;
		this.fase = fase;
		this.timestamp = timestamp;
		this.tupleId = tupleId;
		this.partId = partId;
		this.partName = partName;
	}

	public String getDescricao() {
		if(descricao != null) {
			return " - " + descricao;
		}
		return "";
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getFase() {
		if(fase != null) {
			return " at: " + fase;
		}
		return "";
	}

	public void setFase(String fase) {
		this.fase = fase;
	}

	public String getTupleId() {
		if(tupleId != 0) {
			return " Tuple/Match: " + tupleId;
		}
		return "";
	}

	public void setTupleId(int tupleId) {
		this.tupleId = tupleId;
	}

	public String getPartId() {
		if(partId != 0) {
			return " : " + partId;
		}
		return "";
	}

	public void setPartId(int partId) {
		this.partId = partId;
	}
	
	public String getPartName() {
		if(partName != null) {
			return " " + partName;
		}
		return "";
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	/**
	 * Specializations must provide their event type through this method
	 * 
	 * @return
	 */
	public abstract String getType();
}

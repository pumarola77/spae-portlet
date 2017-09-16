package com.liferay.docs.spae.model;

import java.math.BigDecimal;

public class Planificador {
	
	private String      nomMetaActivitat;
	private String      cicle;
	private String      cursAcademic;
	private BigDecimal  indexReferencia;
	
	public String getNomMetaActivitat() {
		return nomMetaActivitat;
	}
	
	public void setNomMetaActivitat(String nomMetaActivitat) {
		this.nomMetaActivitat = nomMetaActivitat;
	}
	
	public String getCicle() {
		return cicle;
	}
	
	public void setCicle(String cicle) {
		this.cicle = cicle;
	}
	
	public String getCursAcademic() {
		return cursAcademic;
	}

	public void setCursAcademic(String cursAcademic) {
		this.cursAcademic = cursAcademic;
	}

	public BigDecimal getIndexReferencia() {
		return indexReferencia;
	}

	public void setIndexReferencia(BigDecimal indexReferencia) {
		this.indexReferencia = indexReferencia;
	}

}

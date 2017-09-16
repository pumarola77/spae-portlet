package com.liferay.docs.spae.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="spae_assistencia")
public class Assistencia {
	
	@Id
	@GeneratedValue( strategy=javax.persistence.GenerationType.AUTO  )
	private int idAssistencia;

	@Column(name="dataAssistencia", nullable=false)
	private Date dataAssistencia;
	
	@Column(name="assistir", length=1 , nullable=false)
	private String assistir;
	
	@Column(name="comentari", length=250)
	private String comentari;
	
	@ManyToOne
	@JoinColumn(name="idActivitat")
	private Activitat idActivitat;
	
	@ManyToOne
	@JoinColumn(name="idInscripcio")
	private Inscripcio idInscripcio;

	public int getIdAssistencia() {
		return idAssistencia;
	}

	public void setIdAssistencia(int idAssistencia) {
		this.idAssistencia = idAssistencia;
	}

	public Date getDataAssistencia() {
		return dataAssistencia;
	}

	public void setDataAssistencia(Date dataAssistencia) {
		this.dataAssistencia = dataAssistencia;
	}

	public String getAssistir() {
		return assistir;
	}

	public void setAssistir(String assistir) {
		this.assistir = assistir;
	}

	public String getComentari() {
		return comentari;
	}

	public void setComentari(String comentari) {
		this.comentari = comentari;
	}

	public Activitat getIdActivitat() {
		return idActivitat;
	}

	public void setIdActivitat(Activitat idActivitat) {
		this.idActivitat = idActivitat;
	}

	public Inscripcio getIdInscripcio() {
		return idInscripcio;
	}

	public void setIdInscripcio(Inscripcio idInscripcio) {
		this.idInscripcio = idInscripcio;
	}
	
	
}

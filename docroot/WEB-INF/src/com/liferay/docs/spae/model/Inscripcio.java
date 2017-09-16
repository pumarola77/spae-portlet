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
@Table(name="spae_inscripcio")
public class Inscripcio {

	@Id
	@GeneratedValue( strategy=javax.persistence.GenerationType.AUTO  )
	private int idInscripcio;
	
	@Column(name="numInscripcio", length=20 , nullable=false)
	private String numInscripcio;
	
	@Column(name="comentari", length=250)
	private String comentari;
	
	@Column(name="dataInscripcio", nullable=false)
	private Date dataInscripcio;
	
	@Column(name="confirmada", length=1 , nullable=false)
	private String confirmada;
	
	@Column(name="cancelada", length=1 , nullable=false)
	private String cancelada;
	
	@Column(name="activa", length=1 , nullable=false)
	private String activa;
	
	@Column(name="motiu", length=150)
	private String motiu;
	
	@ManyToOne
	@JoinColumn(name="idAlumne")
	private Alumne idAlumne;
	
	@ManyToOne
	@JoinColumn(name="idActivitat")
	private Activitat idActivitat;
	
	public int getIdInscripcio() {
		return idInscripcio;
	}

	public void setIdInscripcio(int idInscripcio) {
		this.idInscripcio = idInscripcio;
	}

	public String getNumInscripcio() {
		return numInscripcio;
	}

	public void setNumInscripcio(String numInscripcio) {
		this.numInscripcio = numInscripcio;
	}

	public String getComentari() {
		return comentari;
	}

	public void setComentari(String comentari) {
		this.comentari = comentari;
	}

	public Date getDataInscripcio() {
		return dataInscripcio;
	}

	public void setDataInscripcio(Date dataInscripcio) {
		this.dataInscripcio = dataInscripcio;
	}

	public String getConfirmada() {
		return confirmada;
	}

	public void setConfirmada(String confirmada) {
		this.confirmada = confirmada;
	}

	public String getCancelada() {
		return cancelada;
	}

	public void setCancelada(String cancelada) {
		this.cancelada = cancelada;
	}

	public String getActiva() {
		return activa;
	}

	public void setActiva(String activa) {
		this.activa = activa;
	}

	public String getMotiu() {
		return motiu;
	}

	public void setMotiu(String motiu) {
		this.motiu = motiu;
	}

	public Alumne getIdAlumne() {
		return idAlumne;
	}

	public void setIdAlumne(Alumne idAlumne) {
		this.idAlumne = idAlumne;
	}

	public Activitat getIdActivitat() {
		return idActivitat;
	}

	public void setIdActivitat(Activitat idActivitat) {
		this.idActivitat = idActivitat;
	}

	
}

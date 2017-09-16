package com.liferay.docs.spae.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="spae_alumne")
public class Alumne {
	
	@Id
	@GeneratedValue( strategy=javax.persistence.GenerationType.AUTO  )
	private int idAlumne;
	
	@Column(name="nom", length=100 , nullable=false)
	private String nom;
	
	@Column(name="cognoms", length=200 , nullable=false)
	private String cognoms;
	
	@Column(name="cicle", length=60 , nullable=false)
	private String cicle;
	
	@Column(name="cursacademic", length=60 , nullable=false)
	private String cursAcademic;
	
	@Column(name="edat", nullable=false)
	private Integer edat;
	
	@Column(name="identificador", length=10 , nullable=false)
	private String identificador;
	
	@ManyToOne
	@JoinColumn(name="idTutor")
	private Tutor idTutor;

	public int getIdAlumne() {
		return idAlumne;
	}

	public void setIdAlumne(int idAlumne) {
		this.idAlumne = idAlumne;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCognoms() {
		return cognoms;
	}

	public void setCognoms(String cognoms) {
		this.cognoms = cognoms;
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

	public Integer getEdat() {
		return edat;
	}

	public void setEdat(Integer edat) {
		this.edat = edat;
	}

	public Tutor getIdTutor() {
		return idTutor;
	}

	public void setIdTutor(Tutor idTutor) {
		this.idTutor = idTutor;
	}
	
	public String getIdentificador() {
		return identificador;
	}
	
	public void setIdIdentificador(String identificador) {
		this.identificador = identificador;
	}
	
}

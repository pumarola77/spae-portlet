package com.liferay.docs.spae.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="spae_tutors")
public class Tutor {

	@Id
	@GeneratedValue( strategy=javax.persistence.GenerationType.AUTO  )
	private int idTutor;
	
	@Column(name="nom", length=100 , nullable=false)
	private String nom;
	
	@Column(name="cognoms", length=200 , nullable=false)
	private String cognoms;
	
	@Column(name="telefon", length=20 , nullable=false)
	private String telefon;
	
	@Column(name="email", length=40)
	private String email;

	public int getIdTutor() {
		return idTutor;
	}

	public void setIdTutor(int idTutor) {
		this.idTutor = idTutor;
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

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}

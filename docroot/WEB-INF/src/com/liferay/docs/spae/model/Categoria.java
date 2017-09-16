package com.liferay.docs.spae.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="spae_categoria")
public class Categoria {
	
	@Id
	@GeneratedValue( strategy=javax.persistence.GenerationType.AUTO  )
	private int idCategoria;
	
	@Column(name="nom", length=60 , unique=true , nullable=false)
	private String nom;
	
	@Column(name="descripcio", length=250)
	private String descripcio;

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescripcio() {
		return descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

}

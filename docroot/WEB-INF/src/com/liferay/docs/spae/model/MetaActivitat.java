package com.liferay.docs.spae.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="spae_metaactivitat")
public class MetaActivitat {

	@Id
	@GeneratedValue( strategy=javax.persistence.GenerationType.AUTO  )
	private int idMetaActivitat;
	
	@Column(name="nom", length=60 , unique=true , nullable=false)
	private String nom;
	
	@Column(name="descripcio", length=250)
	private String descripcio;
	
	/*
	@Column(name="categoria", length=100 , nullable=false)
	private String categoria;
	*/
	
	@ManyToOne
	@JoinColumn(name="idCategoria")
	private Categoria idCategoria;
	
	public int getIdMetaActivitat() {
		return idMetaActivitat;
	}

	public void setIdMetaActivitat(int idMetaActivitat) {
		this.idMetaActivitat = idMetaActivitat;
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

	public Categoria getCategoria() {
		return idCategoria;
	}

	public void setCategoria(Categoria idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	
}

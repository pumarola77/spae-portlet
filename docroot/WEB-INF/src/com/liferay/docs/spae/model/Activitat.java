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
@Table(name="spae_activitat")
public class Activitat {
	
	@Id
	@GeneratedValue( strategy=javax.persistence.GenerationType.AUTO  )
	private int idActivitat;
	
	@Column(name="nomActivitat", length=60 , nullable=false)
	private String nomActivitat;
	
	@Column(name="descripcio", length=250)
	private String descripcio;
	
	@Column(name="cursAcademic", length=60, nullable=false)
	private String cursAcademic;
	
	@Column(name="cicle", length=60, nullable=false)
	private String cicle;
	
	@Column(name="trimestre", length=60, nullable=false)
	private String trimestre;
	
	@Column(name="minAlumnes", nullable=false)
	private Integer minAlumnes;
	
	@Column(name="maxAlumnes", nullable=false)
	private Integer maxAlumnes;
	
	@Column(name="dtInici", nullable=false)
	private Date dtInici;
	
	@Column(name="dtFinal")
	private Date dtFinal;
	
	@Column(name="horaInici", nullable=false)
	private String horaInici;
	
	@Column(name="horaFinal", nullable=false)
	private String horaFinal;
	
	@Column(name="duracio", nullable=false)
	private Integer duracio;
	
	@Column(name="activa", nullable=false , length=1)
	private String activa;
	
	@Column(name="cancelada", nullable=false , length=1)
	private String cancelada;
	
	@Column(name="motiuCancel" , length=250)
	private String motiuCancel;
	
	@Column(name="confirmada", nullable=false, length=1)
	private String confirmada;
	
	@Column(name="empresaResponsable" , length=250)
	private String empresaResponsable;
	
	@Column(name="monitorResponsable" , length=250)
	private String monitorResponsable;
	
	@Column(name="dies" , length=250)
	private String dies;
	
	@ManyToOne
	@JoinColumn(name="idMetaActivitat")
	private MetaActivitat idMetaActivitat;

	public int getIdActivitat() {
		return idActivitat;
	}

	public void setId(int idActivitat) {
		this.idActivitat = idActivitat;
	}

	public String getNomActivitat() {
		return nomActivitat;
	}

	public void setNomActivitat(String nomActivitat) {
		this.nomActivitat = nomActivitat;
	}

	public String getDescripcio() {
		return descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

	public String getCursAcademic() {
		return cursAcademic;
	}

	public void setCursAcademic(String cursAcademic) {
		this.cursAcademic = cursAcademic;
	}

	public String getCicle() {
		return cicle;
	}

	public void setCicle(String cicle) {
		this.cicle = cicle;
	}

	public String getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(String trimestre) {
		this.trimestre = trimestre;
	}

	public Integer getMinAlumnes() {
		return minAlumnes;
	}

	public void setMinAlumnes(Integer minAlumnes) {
		this.minAlumnes = minAlumnes;
	}

	public Integer getMaxAlumnes() {
		return maxAlumnes;
	}

	public void setMaxAlumnes(Integer maxAlumnes) {
		this.maxAlumnes = maxAlumnes;
	}

	public Date getDtInici() {
		return dtInici;
	}

	public void setDtInici(Date dtInici) {
		this.dtInici = dtInici;
	}

	public Date getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(Date dtFinal) {
		this.dtFinal = dtFinal;
	}

	public String getHoraInici() {
		return horaInici;
	}

	public void setHoraInici(String horaInici) {
		this.horaInici = horaInici;
	}

	public String getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}

	public Integer getDuracio() {
		return duracio;
	}

	public void setDuracio(Integer duracio) {
		this.duracio = duracio;
	}

	public String getActiva() {
		return activa;
	}

	public void setActiva(String activa) {
		this.activa = activa;
	}

	public String getCancelada() {
		return cancelada;
	}

	public void setCancelada(String cancelada) {
		this.cancelada = cancelada;
	}

	public String getMotiuCancel() {
		return motiuCancel;
	}

	public void setMotiuCancel(String motiuCancel) {
		this.motiuCancel = motiuCancel;
	}

	public String getConfirmada() {
		return confirmada;
	}

	public void setConfirmada(String confirmada) {
		this.confirmada = confirmada;
	}

	public String getEmpresaResponsable() {
		return empresaResponsable;
	}

	public void setEmpresaResponsable(String empresaResponsable) {
		this.empresaResponsable = empresaResponsable;
	}

	public String getMonitorResponsable() {
		return monitorResponsable;
	}

	public void setMonitorResponsable(String monitorResponsable) {
		this.monitorResponsable = monitorResponsable;
	}

	public String getDies() {
		return dies;
	}

	public void setDies(String dies) {
		this.dies = dies;
	}

	public MetaActivitat getIdMetaActivitat() {
		return idMetaActivitat;
	}

	public void setIdMetaActivitat(MetaActivitat idMetaActivitat) {
		this.idMetaActivitat = idMetaActivitat;
	}
	
	
	
}

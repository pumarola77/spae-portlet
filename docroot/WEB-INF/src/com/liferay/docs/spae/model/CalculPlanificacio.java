package com.liferay.docs.spae.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.liferay.docs.spae.util.HibernateUtil;

public class CalculPlanificacio {
	
	private int idCategoria;
	private String cicle;
	private String nomMetaActivitat;
	private String cursAcademic;
	private BigDecimal inscripcionsper;
	private BigDecimal assistenciaper;
	
	public int getIdCategoria() {
		return idCategoria;
	}
	
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	public String getCicle() {
		return cicle;
	}
	
	public void setCicle(String cicle) {
		this.cicle = cicle;
	}
	
	public String getNomMetaActivitat() {
		return nomMetaActivitat;
	}
	
	public void setNomMetaActivitat(String nomMetaActivitat) {
		this.nomMetaActivitat = nomMetaActivitat;
	}
	
	public String getCursAcademic() {
		return cursAcademic;
	}
	
	public void setCursAcademic(String cursAcademic) {
		this.cursAcademic = cursAcademic;
	}
	
	public BigDecimal getInscripcionsper() {
		return inscripcionsper;
	}
	
	public void setInscripcionsper(BigDecimal inscripcionsper) {
		this.inscripcionsper = inscripcionsper;
	}
	
	public BigDecimal getAssistenciaper() {
		return assistenciaper;
	}

	public void setAssistenciaper(BigDecimal assistenciaper) {
		this.assistenciaper = assistenciaper;
	}
	
	
	@SuppressWarnings("unchecked")
	public static List<Planificador> calculPlanificador(Integer idCategoria) {
		
		Session session = null;
		
		List<Planificador> listPlanificador = new ArrayList<Planificador>();
		List<CalculPlanificacio> listCalculPlanificacio = new ArrayList<CalculPlanificacio>();
		CalculPlanificacio calculPlanificacioValid = null;
		
		session = HibernateUtil.openSession();
		session.beginTransaction();	
		
		/* Obte Calcul Inscripcions */
		
		Criteria criteriaInscripcions  = session.createCriteria(Inscripcio.class);
		criteriaInscripcions.add( Restrictions.eq("cancelada", "N"));
		criteriaInscripcions.add( Restrictions.eq("confirmada", "Y"));
		
		Criteria criteriaActivitat = criteriaInscripcions.createCriteria("idActivitat", "act");
		Criteria criteriaMetaActivitat = criteriaActivitat.createCriteria("idMetaActivitat" , "meta");
		
		Criteria criteriaCategoria = criteriaMetaActivitat.createCriteria("idCategoria", "cate");
		criteriaCategoria.add( Restrictions.eq("idCategoria", idCategoria));
		
		ProjectionList properties = Projections.projectionList();
		properties.add( Projections.property("cate.idCategoria"));
		properties.add( Projections.property("act.cicle"));
		properties.add( Projections.property("meta.nom"));
		properties.add( Projections.property("act.cursAcademic"));
		properties.add( Projections.property("act.minAlumnes"));
		
	
		properties.add( Projections.rowCount());
		properties.add( Projections.groupProperty("cate.idCategoria"));
		properties.add( Projections.groupProperty("act.cicle"));
		properties.add( Projections.groupProperty("meta.nom"));
		properties.add( Projections.groupProperty("act.cursAcademic"));
		properties.add( Projections.groupProperty("act.minAlumnes"));
		
		
		// Continuar a partir d'aqui s'ha de realitzar el calcul i guardar els valors
		// a una llista CalculPlanificacio
		
		List<Object[]> listDatos = criteriaInscripcions.setProjection(properties).list();
		for (Object[] datos : listDatos) {
			
			Integer idcategoria      = Integer.parseInt(datos[0].toString());
			String  cicle      	     = datos[1].toString();
			String  nomMetaActivitat = datos[2].toString();
			String  cursAcademic     = datos[3].toString();
			BigDecimal minAlumnes    = new BigDecimal(Integer.parseInt(datos[4].toString()));
			BigDecimal numInscrits   = new BigDecimal(Integer.parseInt(datos[5].toString()));
								
			BigDecimal parcial       = numInscrits.divide(minAlumnes,2,RoundingMode.HALF_UP);
			BigDecimal totalInscrits = parcial.multiply(new BigDecimal(100));
						
				
			CalculPlanificacio calcul = new CalculPlanificacio();
			calcul.setIdCategoria(idcategoria);
			calcul.setCicle(cicle);
			calcul.setNomMetaActivitat(nomMetaActivitat);
			calcul.setCursAcademic(cursAcademic);
			calcul.setInscripcionsper(totalInscrits);
			
			listCalculPlanificacio.add(calcul);			
		}
			
		// Obte Calcul Per Assistencia en Percentatge
		
		Criteria criteriaAssistencia  = session.createCriteria(Assistencia.class);
		criteriaAssistencia.add( Restrictions.eq("assistir", "Y"));
		
		Criteria criteriaActivitatAssist     = criteriaAssistencia.createCriteria("idActivitat", "act");
		Criteria criteriaMetaActivitatAssist = criteriaActivitatAssist.createCriteria("idMetaActivitat" , "meta");
		
		Criteria criteriaCategoriaAssist = criteriaMetaActivitatAssist.createCriteria("idCategoria", "cate");
		criteriaCategoriaAssist.add( Restrictions.eq("idCategoria", idCategoria));

		ProjectionList propertiesAssist = Projections.projectionList();
		propertiesAssist.add( Projections.property("cate.idCategoria"));
		propertiesAssist.add( Projections.property("act.cicle"));
		propertiesAssist.add( Projections.property("meta.nom"));
		propertiesAssist.add( Projections.property("act.cursAcademic"));
		
		propertiesAssist.add( Projections.rowCount());
		
		propertiesAssist.add( Projections.groupProperty("cate.idCategoria"));
		propertiesAssist.add( Projections.groupProperty("act.cicle"));
		propertiesAssist.add( Projections.groupProperty("meta.nom"));
		propertiesAssist.add( Projections.groupProperty("act.cursAcademic"));

		List<Object[]> listAssistencia = criteriaAssistencia.setProjection(propertiesAssist).list();
		
		for (Object[] datosAssistencia : listAssistencia) {
			
			Integer idcategoriaAssist  = Integer.parseInt(datosAssistencia[0].toString());
			String cicleAssist         = datosAssistencia[1].toString(); 
			String metaAssist          = datosAssistencia[2].toString();	 
			String cursAcademicAssist  = datosAssistencia[3].toString();
			BigDecimal numAssistencia  = new BigDecimal(Integer.parseInt(datosAssistencia[4].toString()));
			
			// Per cada un ha de buscar el numero totalAssistencia
			
			Criteria criteriaAssistencia2   = session.createCriteria(Assistencia.class);
			Criteria criteriaActivitat2     = criteriaAssistencia2.createCriteria("idActivitat", "act");
			criteriaActivitat2.add( Restrictions.eq("cicle", cicleAssist));
			criteriaActivitat2.add( Restrictions.eq("cursAcademic", cursAcademicAssist));
								
			Criteria criteriaMetaActivitat2 = criteriaActivitat2.createCriteria("idMetaActivitat" , "meta");
			
			Criteria criteriaCategoria2 = criteriaMetaActivitat2.createCriteria("idCategoria", "cate");
			criteriaCategoria2.add( Restrictions.eq("idCategoria", idcategoriaAssist));
						
			ProjectionList propertiesAssist2 = Projections.projectionList();
			propertiesAssist2.add( Projections.rowCount());
			
			List<Long> listAssistencia2 = criteriaAssistencia2.setProjection(propertiesAssist2).list();
			
			BigDecimal numTotalAssistencia = new BigDecimal(1);
			
			if ( listAssistencia2.size() == 1 ) {
				numTotalAssistencia = new BigDecimal(listAssistencia2.get(0));
			}
						
			// Calcul Percentatge numAssistencia / numTotalAssistencia
			BigDecimal parcial2         = numAssistencia.divide(numTotalAssistencia,2,RoundingMode.HALF_UP);
			BigDecimal totalAssistencia = parcial2.multiply(new BigDecimal(100));
			
			// Assignar-lo a la llista CalculPlanificacio
			
			Boolean lb_existe = false;
			Integer index = 0;
			Integer posicio = 0;
			Iterator<CalculPlanificacio> calculPlanificacio = listCalculPlanificacio.iterator();
			while ( calculPlanificacio.hasNext() || lb_existe == false) {				
				calculPlanificacioValid = calculPlanificacio.next();
				
				if ( ( calculPlanificacioValid.getCicle().compareTo(cicleAssist) == 0 ) && 
				     ( calculPlanificacioValid.getCursAcademic().compareTo(cursAcademicAssist) == 0 ) &&
				     ( calculPlanificacioValid.getNomMetaActivitat().compareTo(metaAssist) == 0)
				   )
				{
					lb_existe = true;
					posicio = index;
				} else {
					index = index + 1;
				}
								
			}
			
			if ( lb_existe == true ) {
				listCalculPlanificacio.get(posicio).setAssistenciaper(totalAssistencia);
			}
			
		}
		
		// Carrega la llista a la classe Planificador i retorna
		Iterator<CalculPlanificacio> calculPlanificacio = listCalculPlanificacio.iterator();
		while ( calculPlanificacio.hasNext() ) {
			calculPlanificacioValid = calculPlanificacio.next();
			
			Planificador planificador = new Planificador();
			planificador.setCicle(calculPlanificacioValid.getCicle());
			planificador.setNomMetaActivitat(calculPlanificacioValid.getNomMetaActivitat());
			planificador.setCursAcademic(calculPlanificacioValid.getCursAcademic());
			
			// Valor inscripcions
			BigDecimal indexInscripcions = calculPlanificacioValid.getInscripcionsper().divide(new BigDecimal(100),2,RoundingMode.HALF_UP);
			
			// Valor assistencia
			BigDecimal indexAssistencia = calculPlanificacioValid.getAssistenciaper().divide(new BigDecimal(100),2,RoundingMode.HALF_UP);
			
			// Totals
			BigDecimal indexReferencia = indexInscripcions.multiply(indexAssistencia);
			
			planificador.setIndexReferencia(indexReferencia);
					
			listPlanificador.add(planificador);
		}
		
			
		return listPlanificador;
	}
	
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Planificador> prova = calculPlanificador(4);
		Planificador planificadorValid = null;
		
		Iterator<Planificador> prova1 = prova.iterator();
		
		while ( prova1.hasNext()) {
			planificadorValid = prova1.next();
			
			System.out.println("Test " + planificadorValid.getNomMetaActivitat());
			System.out.println("test " + planificadorValid.getIndexReferencia());
			System.out.println("Test " + planificadorValid.getCursAcademic());
			System.out.println("Test " + planificadorValid.getIndexReferencia());
			
		}

	}
	*/
	
}

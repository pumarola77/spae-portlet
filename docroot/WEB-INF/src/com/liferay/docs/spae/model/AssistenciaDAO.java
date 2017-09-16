package com.liferay.docs.spae.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Date;

import org.hibernate.Session;

import com.liferay.docs.spae.util.HibernateUtil;

public class AssistenciaDAO {

	
	/**
	 * Llista assistencies pendents de validar a partir activitat.
	 * @param idActivitat idenficador activitat
	 * @return assistencies pendents de validar
	 */
	public static List<Assistencia> getAssistenciaNova(Integer idActivitat) {
		
		List<Assistencia> listAssistencia = new ArrayList<Assistencia>();
		Inscripcio inscripcioActual = null;
		
		java.util.Date fecha = new Date();
		
		// Obte les inscripcions actives per les activitats
		List<Inscripcio> listInscripcions = InscripcioDAO.getInscripcioActivitat(idActivitat);
		
		if ( listInscripcions.size() > 0 ) {
			
			Iterator<Inscripcio> inscripcions = listInscripcions.iterator();
			
			while ( inscripcions.hasNext()) {
				inscripcioActual = inscripcions.next();
				
				Assistencia novaassistencia = new Assistencia();
				novaassistencia.setAssistir("P");
				novaassistencia.setIdActivitat(inscripcioActual.getIdActivitat());
				novaassistencia.setIdInscripcio(inscripcioActual);	
				novaassistencia.setDataAssistencia(fecha);
				novaassistencia.setComentari(null);
				
				listAssistencia.add(novaassistencia);
				
			}
							
		}
		
		return listAssistencia;
	}
	
	
	public static void addAssistencia(Integer idInscripcio , Integer idActivitat , Date dtAssistencia , 
			String assistir, String comentari ) {
		
		Session session = null;
		
		session = HibernateUtil.openSession();
		session.beginTransaction();
		
		// Carrega activitat
		Activitat activitatActual = ActivitatDAO.getActivitatId(idActivitat);
		
		// Carrega inscripcio
		Inscripcio inscripcioActual = InscripcioDAO.getInscripcioId(idInscripcio);
		
		
		Assistencia assistencia = new Assistencia();
		
		assistencia.setAssistir(assistir);
		assistencia.setDataAssistencia(dtAssistencia);
		assistencia.setIdActivitat(activitatActual);
		assistencia.setIdInscripcio(inscripcioActual);
		assistencia.setComentari(comentari);

		session.save(assistencia);
		session.getTransaction().commit();
		session.close();
	}
	
}

package com.liferay.docs.spae.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.liferay.docs.spae.util.HibernateUtil;

public class InscripcioDAO {
	
	public static void addInscripcio(Integer idAlumne , Integer idActivitat, String comentari) {
		
		Session session = null;
		
		session = HibernateUtil.openSession();
		session.beginTransaction();
		
		Inscripcio inscripcio = new Inscripcio();
		
		// Recull Objecte metaactivitat
		Activitat activitat = ActivitatDAO.getActivitatId(idActivitat);
		Alumne alumne = AlumneDAO.getAlumneId(idAlumne);
		
		java.util.Date dataInscripcio = new Date();
		
		String numInscripcio = idActivitat.toString() + "_" + idAlumne.toString();
		
		inscripcio.setActiva("Y");
		inscripcio.setCancelada("N");
		inscripcio.setConfirmada("N");
		inscripcio.setDataInscripcio(dataInscripcio);
		inscripcio.setNumInscripcio(numInscripcio);
		inscripcio.setIdActivitat(activitat);
		inscripcio.setIdAlumne(alumne);
		inscripcio.setComentari(comentari);
		
		session.save(inscripcio);
		session.getTransaction().commit();
		session.close();
		
	}
	
	
	/**
	 * 
	 * @param emailAddress Adreça email tutor
	 * @param idActivitat Identificador Activitat
	 * @return Llista Alumnes que no estan inscrits a l'activitat i del qual es tutor.
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Alumne> getNotInscripcioAlumnes(String emailAddress,Integer idActivitat) throws Exception {

		Session session = null;
		
		/* Alumnes que te un tutor */
		List<Alumne> alumnesTutors = new ArrayList<Alumne>();
		List<Activitat> activitats = null;
		
		Alumne alumneValid = null;
		
		/* Alumnes sense inscripcions */
		List<Alumne> alumnesSenseIns = new ArrayList<Alumne>();
		
		session = HibernateUtil.openSession();
		session.beginTransaction();	
		
		// Selecciona informacio de l'activitat
		Criteria criteriaActivitat = session.createCriteria(Activitat.class);
		activitats = criteriaActivitat.add( Restrictions.eq("idActivitat", idActivitat)).list();
				
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			alumnesTutors = AlumneDAO.getAlumnesTutor(emailAddress);
		
			// Recorre la llista i esborra aquells alumnes que ja estan inscrits a l'activitat.
			if ( alumnesTutors.size() > 0 ) {
				Iterator<Alumne> alumnes = alumnesTutors.iterator();
				
				while ( alumnes.hasNext()) {
					alumneValid = alumnes.next();
						
					// Busco si per identificador del alumne esta assignat a una inscripcio
					// Aquesta activitat.
										
					if ( ( getTeInscripcio(alumneValid.getIdAlumne(), idActivitat) == false ) &&
					     ( alumneValid.getCicle().compareTo(activitats.get(0).getCicle()) == 0 ) &&
					     ( alumneValid.getCursAcademic().compareTo(activitats.get(0).getCursAcademic()) == 0 )  
					   )
					{
						alumnesSenseIns.add(alumneValid);
					}
					
				}
			}
			
		
		}
		catch ( Exception e) {
			e.printStackTrace();
		}
		
		return alumnesSenseIns;
		
	}	
	
	
	@SuppressWarnings("unchecked")
	public static List<Activitat> getActivitatsSenseInsAlumne(String emailAddress) throws Exception {
		
		/* Activitats que no esta inscrit un alumne */
		List<Activitat> activitatsSenseIns = new ArrayList<Activitat>();
		
		/* Alumnes que te un tutor */
		List<Alumne> alumnesTutors = new ArrayList<Alumne>();
		List<Activitat> activitats = null;
		
		Alumne alumneValid = null;
		Activitat activitatValid = null;
		
		Boolean lb_existe=false;
		
		Session session = null;
				
		try {
			
			session = HibernateUtil.openSession();
			session.beginTransaction();			
			
			alumnesTutors = AlumneDAO.getAlumnesTutor(emailAddress);
			
			// Recorre la llista alumnes asssignats a un tutor
			if ( alumnesTutors.size() > 0 ) {
				Iterator<Alumne> alumnes = alumnesTutors.iterator();
				
				while ( alumnes.hasNext()) {
					alumneValid = alumnes.next();
					
					// Selecciona les activitats que pot realitzar l'alumne
					Criteria criteriaActivitat = session.createCriteria(Activitat.class);
					activitats = criteriaActivitat.add( Restrictions.eq("activa", "Y")).
									add( Restrictions.eq("cancelada", "N")).
									add( Restrictions.eq("cicle", alumneValid.getCicle())).
									add( Restrictions.eq("cursAcademic", alumneValid.getCursAcademic())).
									addOrder( Property.forName("idActivitat").asc()).list();
									
									
					if ( activitats.size() > 0 ) {
						
						Iterator<Activitat> listActivitats = activitats.iterator();
						
						while ( listActivitats.hasNext()) {
							activitatValid = listActivitats.next();
							
							//Comprovar que activitat no esta donada alta a activatatsSenseIns
							lb_existe = false;
							if ( activitatsSenseIns.size() > 0 ) {
								for ( int i=0; i<activitatsSenseIns.size(); i++) {
									if ( activitatsSenseIns.get(i).getIdActivitat() == activitatValid.getIdActivitat()) {
										lb_existe = true;
									}
								}
							}
							
							if ( lb_existe == false ) {
								if ( getTeInscripcio(alumneValid.getIdAlumne(), activitatValid.getIdActivitat()) == false ) 
								{
									activitatsSenseIns.add(activitatValid);
								}
							}
						}
						
					}
					
				}
			}
			
		} 
		catch ( Exception e) {
			e.printStackTrace();
		}
		
		
		return activitatsSenseIns;
	}  	
	
	@SuppressWarnings("unchecked")
	public static Boolean getTeInscripcio(Integer idAlumne , Integer idActivitat) {
		Boolean lb_existe = false;
		List<Inscripcio> inscripcio = null;
		Inscripcio inscripcioValid = null;
		
		Session session = null;
		
		session = HibernateUtil.openSession();
		session.beginTransaction();	
			
		// Obte Inscripcions Actives
		Criteria criteriaInscripcio = session.createCriteria(Inscripcio.class);
		inscripcio = criteriaInscripcio.add( Restrictions.eq("activa", "Y")).
						add( Restrictions.eq("cancelada", "N")).list();
		
		lb_existe = false;
		if ( inscripcio.size() > 0 ) {
			
			Iterator<Inscripcio> inscripcions = inscripcio.iterator();
			
			while ( inscripcions.hasNext() ) {
				inscripcioValid = inscripcions.next();
				
				if ( ( idActivitat.compareTo(inscripcioValid.getIdActivitat().getIdActivitat()) == 0) && 
					 ( idAlumne.compareTo(inscripcioValid.getIdAlumne().getIdAlumne()) == 0 ) )
				{
					lb_existe = true;
				}
			}
		}
		
		
		return lb_existe;
	}
	
	
	@SuppressWarnings("unchecked")
	public static List<Inscripcio> getInscripcionsActives(String emailAddress) {
		Session session = null;
		List<Inscripcio> inscripcio = null;
		List<Tutor> tutor = null;
		Inscripcio inscripcioActual = null;
		
		List<Inscripcio> listInscripcions = new ArrayList<Inscripcio>();
		
		session = HibernateUtil.openSession();
		session.beginTransaction();	
		
		// Obte indenticador Tutor
		Criteria criteriaTutor = session.createCriteria(Tutor.class);
		tutor = criteriaTutor.add( Restrictions.eq("email", emailAddress) ).list();
				
		if ( tutor.size() == 1 ) {
		
			// Un email nomes pot estar assignat a un tutor
			Integer idTutor = tutor.get(0).getIdTutor();
			
			Criteria criteriaInscripcio = session.createCriteria(Inscripcio.class);
			inscripcio = criteriaInscripcio.add( Restrictions.eq("activa", "Y")).
					add( Restrictions.eq("cancelada", "N")).list();
								
			// Buscar que la inscripcio correspongui al tutor logat.
			// I es donen alta a la variable listInscripcions
		
			if ( inscripcio.size() > 0 ) {
				
				Iterator<Inscripcio> inscripcions = inscripcio.iterator();
								
				while ( inscripcions.hasNext() ) {
					inscripcioActual = inscripcions.next();
										
					if ( idTutor.compareTo(inscripcioActual.getIdAlumne().getIdTutor().getIdTutor()) == 0 ) {
						listInscripcions.add(inscripcioActual);
					}
					
				}
				
			}
				
		}
				
		return listInscripcions;
	}
	
	
	@SuppressWarnings("unchecked")
	public static void confirmaInscripcio(Integer idInscripcio) throws Exception {
		
		Session session = null;
				
		session = HibernateUtil.openSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Inscripcio.class);	
		List<Inscripcio> listInscripcions = criteria.add( Restrictions.eq("idInscripcio", idInscripcio)).list();
		
		if ( listInscripcions.size() > 0 ) {
			for ( Inscripcio inscripcio : listInscripcions) {
				inscripcio.setConfirmada("Y");
				session.update(inscripcio);
			}
			
			session.getTransaction().commit();
		}
		
		session.close();
		
	}
	
	/**
	 * Retorna una inscripcio a partir del identificador
	 * @param activitatId Identificador inscripcio
	 * @return inscripcio
	 */
	@SuppressWarnings("unchecked")
	public static Inscripcio getInscripcioId(Integer idInscripcio) {
		Inscripcio inscripcio = null;
		Session session = null;
				
		try {	
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Inscripcio.class);
			List<Inscripcio> inscripcions = criteria.add( Restrictions.eq("idInscripcio", idInscripcio)).list();
			
			if ( inscripcions.size() > 0 ) {
				inscripcio = inscripcions.get(0);
			}
			
		} 
		catch ( Exception e) {
			e.printStackTrace();
		}
		
		return inscripcio;
		
	}
	
	@SuppressWarnings("unchecked")
	public static void cancelInscripcio(Integer idInscripcio , String cancelMotiu) throws Exception {
		
		Session session = null;
		
		session = HibernateUtil.openSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Inscripcio.class);
		List<Inscripcio> listinscripcions = criteria.add( Restrictions.eq("idInscripcio", idInscripcio)).list();
				
		if ( listinscripcions.size() > 0) {
			for ( Inscripcio inscripcio : listinscripcions) {
				inscripcio.setCancelada("Y");
				inscripcio.setMotiu(cancelMotiu);				
				session.update(inscripcio);
			}
			
			session.getTransaction().commit();
		}
		
		session.close();
		
	}
	
	@SuppressWarnings("unchecked")
	public static List<Activitat> getActivitatsInscripcions() {
		
		List<Activitat> listActivitatInscripcio = new ArrayList<Activitat>();
		Activitat activitatActual = null;
		Inscripcio inscripcioActual = null;
		Boolean lb_existe=false;
		
		Session session = null;
		
		session = HibernateUtil.openSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Activitat.class);
		List<Activitat> listActivitats = criteria.add( Restrictions.eq("confirmada", "Y")).
				add( Restrictions.eq("cancelada", "N")).
				add( Restrictions.eq("activa", "Y")).list();
				
		
		if ( listActivitats.size() > 0 ) {
			
			Iterator<Activitat> activitats = listActivitats.iterator();
			
			while ( activitats.hasNext() ) {
				activitatActual = activitats.next();
					
				Criteria criteriaInscripcio = session.createCriteria(Inscripcio.class);
				List<Inscripcio> listInscripcions = criteriaInscripcio.add( Restrictions.eq("activa", "Y")).
						add( Restrictions.eq("confirmada", "Y")).
						add( Restrictions.eq("cancelada", "N")).list();
						
				Integer idactivitat = activitatActual.getIdActivitat();
				
				
				lb_existe = false;
				if ( listInscripcions.size() > 0 ) {
					
					Iterator<Inscripcio> inscripcions = listInscripcions.iterator();
								
					while ( inscripcions.hasNext() && lb_existe == false ) {
						inscripcioActual = inscripcions.next();
												
						if ( idactivitat.compareTo(inscripcioActual.getIdActivitat().getIdActivitat()) == 0 ) {
							lb_existe = true;
						}
					}
				}
				
				if ( lb_existe == true ) {
					listActivitatInscripcio.add(activitatActual);					
				}
				
				
			}

			
		}
		
		return listActivitatInscripcio;
	}
	
	/**
	 * Retorna les Inscripcions actives per activitat
	 * @param idActivitat identificador activitat
	 * @return inscripcions realitzades a activitat
	 */
	@SuppressWarnings("unchecked")
	public static List<Inscripcio> getInscripcioActivitat(Integer idActivitat) {
		
		Session session = null;
		
		session = HibernateUtil.openSession();
		session.beginTransaction();
		
		Criteria criteriaInscripcio = session.createCriteria(Inscripcio.class);
		
		criteriaInscripcio.add( Restrictions.eq("activa", "Y")).
				add( Restrictions.eq("confirmada", "Y")).
				add( Restrictions.eq("cancelada", "N"));
		
		Criteria criteriaActivitat  = criteriaInscripcio.createCriteria("idActivitat");
		criteriaActivitat.add( Restrictions.eq("idActivitat", idActivitat));
		
		List<Inscripcio> inscripcio = criteriaInscripcio.list();
				
		return inscripcio;
	}

}

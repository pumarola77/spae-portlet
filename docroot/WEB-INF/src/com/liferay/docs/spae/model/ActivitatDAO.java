package com.liferay.docs.spae.model;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.liferay.docs.spae.util.HibernateUtil;

public class ActivitatDAO {

	
	public static void addActivitat(String nom , String descripcio , String cursacademic , String cicle , String trimestre ,
			Date dtInici , Date dtFinal , String horainci , String horafinal , Integer duracio , Integer minAlumnes ,
			Integer maxAlumnes , Integer idmetaactivitat , String dies , String empresaResponsable , String monitorResponsable ) 
	{
		Session session = null;
		
		session = HibernateUtil.openSession();
		session.beginTransaction();
		
		Activitat activitat = new Activitat();
		
		// Recull Objecte metaactivitat
		MetaActivitat metaactivitat = MetaActivitatDAO.getMetaActivitatId(idmetaactivitat);
		
		activitat.setNomActivitat(nom);
		activitat.setDescripcio(descripcio);
		activitat.setCursAcademic(cursacademic);
		activitat.setCicle(cicle);
		activitat.setTrimestre(trimestre);
		activitat.setDtInici(dtInici);
		activitat.setDtFinal(dtFinal);
		activitat.setHoraInici(horainci);
		activitat.setHoraFinal(horafinal);
		activitat.setDuracio(duracio);
		activitat.setMinAlumnes(minAlumnes);
		activitat.setMaxAlumnes(maxAlumnes);
		activitat.setIdMetaActivitat(metaactivitat);
		activitat.setDies(dies);
		activitat.setEmpresaResponsable(empresaResponsable);
		activitat.setMonitorResponsable(monitorResponsable);
		activitat.setActiva("Y");
		activitat.setCancelada("N");
		activitat.setConfirmada("N");
				
		
		session.save(activitat);
		session.getTransaction().commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public static void updateActivitat(Integer IdActivitat , String descripcio , Date dtInici , Date dtFinal ,
				String horaInici , String horaFinal , Integer duracio , Integer minAlumnes , Integer maxAlumnes ,
				String dies , String empresaResponsable , String monitorResponsable )
	{
		Session session = null;
		
		session = HibernateUtil.openSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Activitat.class);	
		List<Activitat> listactivitats = criteria.add( Restrictions.eq("idActivitat", IdActivitat)).list();
				
		
		if ( listactivitats.size() > 0 ) {
			
			for ( Activitat activitat : listactivitats) {
				
				activitat.setDescripcio(descripcio);
				activitat.setDtInici(dtInici);
				activitat.setDtFinal(dtFinal);
				activitat.setHoraInici(horaInici);
				activitat.setHoraFinal(horaFinal);
				activitat.setDuracio(duracio);
				activitat.setMinAlumnes(minAlumnes);
				activitat.setMaxAlumnes(maxAlumnes);
				activitat.setDies(dies);
				activitat.setEmpresaResponsable(empresaResponsable);
				activitat.setMonitorResponsable(monitorResponsable);
				
				session.update(activitat);
				
			}
			
			session.getTransaction().commit();
		}
		
		session.close();
		
	}
	
	@SuppressWarnings("unchecked")
	public static void confirmaActivitat(Integer idActivitat) throws Exception {
		
		Session session = null;
				
		session = HibernateUtil.openSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Activitat.class);	
		List<Activitat> listactivitats = criteria.add( Restrictions.eq("idActivitat", idActivitat)).list();
		
		if ( listactivitats.size() > 0 ) {
			for ( Activitat activitat : listactivitats) {
				activitat.setConfirmada("Y");
				session.update(activitat);
			}
			
			session.getTransaction().commit();
		}
		
		session.close();
		
	}
	
	@SuppressWarnings("unchecked")
	public static void cancelActivitat(Integer idActivitat , String cancelMotiu) throws Exception {
		
		Session session = null;
		
		session = HibernateUtil.openSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Activitat.class);
		List<Activitat> listactivitats = criteria.add( Restrictions.eq("idActivitat", idActivitat)).list();
		
		if ( listactivitats.size() > 0) {
			for ( Activitat activitat : listactivitats) {
				activitat.setCancelada("Y");
				activitat.setMotiuCancel(cancelMotiu);
				session.update(activitat);
			}
			
			session.getTransaction().commit();
		}
		
		session.close();
		
	}
	
	/**
	 * Retorna les activitats del sistema
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Activitat> getActivitatsAll() throws Exception {
		Session session = null;
		
		try {
			
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Activitat.class);
			List<Activitat> activitats = criteria.add( Restrictions.eq("activa", "Y")).list();
			
			return activitats;
		} 
		catch ( Exception e ) {
			throw new Exception(e);
		}
		
	}
	
	/**
	 * Retorna una activitat a partir del identificador
	 * @param activitatId Identificador activitat
	 * @return activitat
	 */
	@SuppressWarnings("unchecked")
	public static Activitat getActivitatId(Integer activitatId) {
		Activitat activitat = null;
		Session session = null;
				
		try {	
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Activitat.class);
			List<Activitat> activitats = criteria.add( Restrictions.eq("idActivitat", activitatId)).list();
			
			if ( activitats.size() > 0 ) {
				activitat = activitats.get(0);
			}
			
		} 
		catch ( Exception e) {
			e.printStackTrace();
		}
		
		return activitat;
		
	}
	
	/**
	 * Retorna les activitats que accepten inscripcions ( confirmades , activtes i no cancel.lades )
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Activitat> getActivitatsIns() throws Exception {
		Session session = null;
		
		try {
			
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Activitat.class);
			List<Activitat> activitats = criteria.add( Restrictions.eq("activa", "Y")).add( Restrictions.eq("confirmada", "Y")).add( Restrictions.eq("cancelada", "N")).list();
			
			return activitats;
		} 
		catch ( Exception e ) {
			throw new Exception(e);
		}
		
	}
		
}

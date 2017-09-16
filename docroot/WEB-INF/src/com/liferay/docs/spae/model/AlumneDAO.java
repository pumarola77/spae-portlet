package com.liferay.docs.spae.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.liferay.docs.spae.util.HibernateUtil;

public class AlumneDAO {

	/**
	 * Retorna una activitat a partir del identificador
	 * @param activitatId Identificador activitat
	 * @return activitat
	 */
	@SuppressWarnings("unchecked")
	public static Alumne getAlumneId(Integer alumneId) {
		Alumne alumne = null;
		Session session = null;
				
		try {	
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(Alumne.class);
			List<Alumne> alumnes = criteria.add( Restrictions.eq("idAlumne", alumneId)).list();
			
			if ( alumnes.size() > 0 ) {
				alumne = alumnes.get(0);
			}
			
		} 
		catch ( Exception e) {
			e.printStackTrace();
		}
		
		return alumne;
		
	}
	
	/**
	 * 
	 * @return Llista alumnes que te un determinat tutor
	 */
	@SuppressWarnings("unchecked")
	public static List<Alumne> getAlumnesTutor(String emailAddress) throws Exception {
		List<Tutor> tutors = null;
		List<Alumne> alumneTutor = new ArrayList<Alumne>();
		Alumne alumneValid = null;
		Tutor tutor = null;
		Session session = null;
						
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			Criteria criteriaTutors = session.createCriteria(Tutor.class);
			tutors = criteriaTutors.add( Restrictions.eq("email", emailAddress)).list();
			
			if ( tutors.size() > 0 ) {
				tutor = tutors.get(0);
			}
			
						
			Criteria criteria = session.createCriteria(Alumne.class);
			List<Alumne> alumnesList = criteria.list();
			
			if ( alumnesList.size() > 0 ) {				
				Iterator<Alumne> alumnes = alumnesList.iterator();
				while ( alumnes.hasNext()) {
					alumneValid = alumnes.next();
					
					if ( alumneValid.getIdTutor().getIdTutor() == tutor.getIdTutor() ) {
						alumneTutor.add(alumneValid);
					}
					
				}
			}						
		}
		catch ( Exception e) {
			e.printStackTrace();
		}
		
		return alumneTutor;
	}
	
	
}

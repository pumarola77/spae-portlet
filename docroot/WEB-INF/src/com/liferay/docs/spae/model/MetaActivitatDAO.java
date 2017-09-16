package com.liferay.docs.spae.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.liferay.docs.spae.util.HibernateUtil;

/**
 * Aquesta classe defineix els mètodes que
 * és realitzaran amb les metaActivitats
 * 
 * @author mazinger
 *
 */

public class MetaActivitatDAO {
	
	/**
	 * Afegeix metaactivitat
	 * @param nom nom 
	 * @param descripcio descripcio
	 * @param categoria categoria
	 */
	@SuppressWarnings("unchecked")
	public static void addMetaActivitat(String nom , String descripcio , Integer idCategoria) {
		Session session = null;
		
		session = HibernateUtil.openSession();
		session.beginTransaction();
		
		//Carrega Categoria
		Criteria criteria = session.createCriteria(Categoria.class);	
		List<Categoria> listCategoria = criteria.add( Restrictions.eq("idCategoria", idCategoria)).list();

		MetaActivitat metaActivitat = new MetaActivitat();
		metaActivitat.setNom(nom);
		metaActivitat.setDescripcio(descripcio);
		metaActivitat.setCategoria(listCategoria.get(0));

		session.save(metaActivitat);
		session.getTransaction().commit();
		session.close();
		
	}
	
	@SuppressWarnings("unchecked")
	public static void updateMetaActivitat(Integer metaActivitatId , String nom , String descripcio ) {
		Session session = null;
		
		session = HibernateUtil.openSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(MetaActivitat.class);	
		List<MetaActivitat> listmetaactivitats = criteria.add( Restrictions.eq("idMetaActivitat", metaActivitatId)).list();
				
		
		if ( listmetaactivitats.size() > 0 ) {
			
			for ( MetaActivitat metaactivitat : listmetaactivitats) {
				
				metaactivitat.setNom(nom);
				metaactivitat.setDescripcio(descripcio);
				session.update(metaactivitat);
				
			}
			
			session.getTransaction().commit();
		}
		
		session.close();
		
	}
	
	/**
	 * Retorna les metaactivitats del sistema
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<MetaActivitat> getMetaActivitatsAll() throws Exception {
		Session session = null;
		
		try {
			
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(MetaActivitat.class);
			
			return criteria.list();
		} 
		catch ( Exception e ) {
			throw new Exception(e);
		}
		
		
	}
	
	/**
	 * Retorna una metactivitat a partir del identificador
	 * @param metaActivitatId identicador metaactivitat
	 * @return metaactivitat
	 */
	@SuppressWarnings("unchecked")
	public static MetaActivitat getMetaActivitatId(Integer metaActivitatId) {
		MetaActivitat metaactivitat = null;
		Session session = null;
				
		try {	
			session = HibernateUtil.openSession();
			session.beginTransaction();
			
			Criteria criteria = session.createCriteria(MetaActivitat.class);
			List<MetaActivitat> metaactivitats = criteria.add( Restrictions.eq("idMetaActivitat", metaActivitatId)).list();
			
			if ( metaactivitats.size() > 0 ) {
				metaactivitat = metaactivitats.get(0);
			}
			
		} 
		catch ( Exception e) {
			e.printStackTrace();
		}
		
		return metaactivitat;
		
	}
		
}

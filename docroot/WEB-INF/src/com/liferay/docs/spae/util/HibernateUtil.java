package com.liferay.docs.spae.util;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
	
	private static SessionFactory sessionFactory = init();
	
	private static SessionFactory init() {
		
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		
		return sessionFactory = cfg.buildSessionFactory();
		
	}
	
	public static Session openSession() {
		return sessionFactory.openSession();
	}
	
	
	
	/*
	public static void main(String args[]) {
		//logger.info("Starting main method");
		
		
		//Session session = getSessionFactory().openSession();
		//session.beginTransaction();
		
		
		
		//Configuration cfg = new Configuration();
		//cfg.configure("hibernate.cfg.xml");
		
		//SessionFactory factory = cfg.buildSessionFactory();
		//Session session = factory.openSession();
		
		
		Session session = HibernateUtil.openSession();
		
		session.beginTransaction();
		
		MetaActivitat metaactivitat = new MetaActivitat();
		metaactivitat.setId(2);
		metaactivitat.setNom("AAA");
		metaactivitat.setDescripcio("BBBBBBBB");
		metaactivitat.setCategoria("LLL");
		
		
		session.save(metaactivitat);
		//logger.info("Book saved with id " + metaactivitat.getId());
		session.getTransaction().commit();
		session.close();	
	}	
	*/
	
}

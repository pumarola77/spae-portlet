package com.liferay.docs.spae.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.liferay.docs.spae.util.HibernateUtil;

public class CategoriaDAO {
	
	/**
	 * Categories 
	 * @return Retorna les categories entrades al sistema
	 */
	@SuppressWarnings("unchecked")
	public static List<Categoria> getCategories() {
		
		Session session = null;
		List<Categoria> listCategories = null;
		
		try {
			session = HibernateUtil.openSession();
			session.beginTransaction();	
			
			Criteria criteriaCategoria = session.createCriteria(Categoria.class);
			listCategories = criteriaCategoria.list();
		}
		catch ( Exception e) {
			e.printStackTrace();
		}
		
		return listCategories;
	}

}

package com.liferay.docs.spae.portlet;

import javax.portlet.PortletConfig;
import javax.portlet.PortletException;

import com.liferay.docs.spae.util.HibernateUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class SpaePortlet
 * 
 * @author mazinger
 *
 */

public class SpaePortlet extends MVCPortlet {
	
	/**
	 * Configuració de la base de dades
	 */
	public void init(PortletConfig config) throws PortletException {				
		HibernateUtil.openSession();
	}
 
}

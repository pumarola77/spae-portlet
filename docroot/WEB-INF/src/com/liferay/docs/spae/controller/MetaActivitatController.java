package com.liferay.docs.spae.controller;


import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.docs.spae.model.MetaActivitat;
import com.liferay.docs.spae.model.MetaActivitatDAO;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

public class MetaActivitatController extends MVCPortlet {

		
	/**
	 * Dona alta una metaactivitat
	 * @param request
	 * @param response
	 */
	public void addEntry(ActionRequest request, ActionResponse response) {
		
			
		//PortletPreferences prefs = request.getPreferences();

		/* Captura el valor del addMetaActivitat.jsp*/
		String nom = ParamUtil.getString(request,"Nom");
		String descripcio = ParamUtil.getString(request,"Descripcio");
	    Integer idCategoria = ParamUtil.getInteger(request, "idcategoria");
		
		try {
			MetaActivitatDAO.addMetaActivitat(nom, descripcio, idCategoria);
		} catch(Exception e) {
			SessionErrors.add(request, "error");
		}
		
		SessionMessages.add(request, "success");
	}
	
	/**
	 * Actualitza dades una metaactivitat
	 * @param request
	 * @param response
	 */
	public void updateMetaActivitat(ActionRequest request, ActionResponse response) {
		
		/*Captura el valor de editMetaActivitat.jsp*/
		
		Integer idMetaActivitat = ParamUtil.getInteger(request, "metaActivitatId");
		String nom = ParamUtil.getString(request,"nom");
		String descripcio = ParamUtil.getString(request,"descripcio");
						
		MetaActivitatDAO.updateMetaActivitat(idMetaActivitat, nom, descripcio);
	}
	
	/**
	 * Visualitzacio dades de la pagina
	 */
	public void render (RenderRequest renderRequest, RenderResponse renderResponse ) {
				
		try {
						
			//List<MetaActivitat> metaactivitats = MetaActivitatDAO.getMetaActivitatsAll();
			
			//if ( metaactivitats.size() > 0 ) {
				renderRequest.setAttribute("metaactivitats", MetaActivitatDAO.getMetaActivitatsAll());
			//}
			
			super.render(renderRequest, renderResponse);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
						
			e.printStackTrace();
		}
		
	}
	
}

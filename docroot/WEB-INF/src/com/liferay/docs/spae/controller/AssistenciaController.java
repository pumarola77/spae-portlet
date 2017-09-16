package com.liferay.docs.spae.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.docs.spae.model.Assistencia;
import com.liferay.docs.spae.model.AssistenciaDAO;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

public class AssistenciaController extends MVCPortlet {


	/**
	 * 
	 * @param request
	 * @param response
	 */
	
	@ProcessAction(name="confirmarAssistencia")
	public void confirmarAssistencia(ActionRequest request, ActionResponse response) {
		
		List<Assistencia> listAssistencies = new ArrayList<Assistencia>();
		Assistencia assistenciaActual = null;
		
		try {
			
			DateFormat df = new SimpleDateFormat("dd/mm/yyyy");	
			Date dtAssistencia = ParamUtil.getDate(request, "dtAssistencia", df);
			
			Integer idActivitat = Integer.parseInt(request.getParameter("activitatId"));
			
			listAssistencies = AssistenciaDAO.getAssistenciaNova(idActivitat);
			
			if ( listAssistencies.size() > 0) {
				
				// Recorrer Llista
				Iterator<Assistencia> assistencies = listAssistencies.iterator();
				
				while ( assistencies.hasNext()) {
					assistenciaActual = assistencies.next();
				
					String paramNomAlumne = "nomAlumne"+assistenciaActual.getIdInscripcio().getNumInscripcio();
					String nomAlumne = ParamUtil.getString(request, paramNomAlumne);
										
					String paramCgAlumne = "cgAlumne"+assistenciaActual.getIdInscripcio().getNumInscripcio();
					String cgAlumne = ParamUtil.getString(request, paramCgAlumne);

					String paramAssistir = "assistir"+assistenciaActual.getIdInscripcio().getNumInscripcio();
					String assistir = ParamUtil.getString(request, paramAssistir);
					
					String paramComentari = "comentari"+assistenciaActual.getIdInscripcio().getNumInscripcio();
					String comentari = ParamUtil.getString(request, paramComentari);
					
					Integer idInscripcio = assistenciaActual.getIdInscripcio().getIdInscripcio();
					
					//if ( assistir.compareTo("P") != 0 ) {
						AssistenciaDAO.addAssistencia(idInscripcio, idActivitat, dtAssistencia, assistir , comentari);
					//}
										
				}
				
			}
					
		} catch(Exception e) {
			SessionErrors.add(request, "error");
		}
		
		SessionMessages.add(request, "success");
			
	}
	
	
	/**
	 * 
	 */
	public void render (RenderRequest renderRequest, RenderResponse renderResponse ) {
				
		try {
									
			Integer activitatId = ParamUtil.getInteger(renderRequest, "activitatId");
									
			renderRequest.setAttribute("assistencies", AssistenciaDAO.getAssistenciaNova(activitatId) );
			
			super.render(renderRequest, renderResponse);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
						
			e.printStackTrace();
		}
		
	}
	
}
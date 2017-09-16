package com.liferay.docs.spae.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.docs.spae.model.ActivitatDAO;
import com.liferay.docs.spae.model.Activitat;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

public class ActivitatController extends MVCPortlet {

	
	/**
	 * Dona alta una metaactivitat
	 * @param request
	 * @param response
	 */
	public void addEntry(ActionRequest request, ActionResponse response) {
		
			
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");

		try {
			/* Captura el valor del addMetaActivitat.jsp*/
			String nom = ParamUtil.getString(request,"nomactivitat");
			String descripcio = ParamUtil.getString(request,"descripcio");
			String cursacademic = ParamUtil.getString(request, "cursacademic");
			String cicle = ParamUtil.getString(request, "cicle");
			String trimestre = ParamUtil.getString(request, "trimestre");
			Date dtInici = ParamUtil.getDate(request, "dtInici", df);
			Date dtFinal = ParamUtil.getDate(request, "dtFinal", df);
			String horainici = ParamUtil.getString(request, "horaInici");
			String horafinal = ParamUtil.getString(request, "horaFinal");
			Integer duracio = ParamUtil.getInteger(request, "duracio");
			Integer minAlumnes = ParamUtil.getInteger(request, "minAlumnes");
			Integer maxAlumnes = ParamUtil.getInteger(request, "maxAlumnes");
			Integer idMetaActivitat = ParamUtil.getInteger(request, "idmetaactivitat");
			String dies = ParamUtil.getString(request, "dies");
			String empresaResponsable = ParamUtil.getString(request, "empresaresponsable");
			String monitorResponsable = ParamUtil.getString(request, "monitorresponsable");
	
			
			ActivitatDAO.addActivitat(nom, descripcio, cursacademic , cicle , trimestre , dtInici , dtFinal , horainici , horafinal , 
					 duracio , minAlumnes , maxAlumnes , idMetaActivitat , dies , empresaResponsable , monitorResponsable );
		} catch(Exception e) {
			SessionErrors.add(request, "error");
		}
		
		SessionMessages.add(request, "success");
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void updateActivitat(ActionRequest request, ActionResponse response) {
		
		/*Captura el valor de editActivitat.jsp*/
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		
		Integer idActivitat = ParamUtil.getInteger(request, "idActivitat");
		String descripcio = ParamUtil.getString(request,"descripcio");
		Date dtInici = ParamUtil.getDate(request, "dtInici", df);
		Date dtFinal = ParamUtil.getDate(request, "dtFinal", df);
		String horaInici = ParamUtil.getString(request, "horaInici");
		String horaFinal = ParamUtil.getString(request, "horaFinal");
		Integer duracio = ParamUtil.getInteger(request, "duracio");
		Integer minAlumnes = ParamUtil.getInteger(request, "minAlumnes");
		Integer maxAlumnes = ParamUtil.getInteger(request, "maxAlumnes");
		String dies = ParamUtil.getString(request, "dies");
		String empresaResponsable = ParamUtil.getString(request, "empresaresponsable");
		String monitorResponsable = ParamUtil.getString(request, "monitorresponsable");
				
		ActivitatDAO.updateActivitat(idActivitat, descripcio, dtInici, dtFinal, horaInici, horaFinal, duracio, 
				minAlumnes, maxAlumnes, dies, empresaResponsable, monitorResponsable);
		
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void confirmActivitat(ActionRequest request, ActionResponse response) {
		Integer idActivitat = ParamUtil.getInteger(request, "activitatId");
		
		try {
			ActivitatDAO.confirmaActivitat(idActivitat);
		} catch(Exception e) {
			SessionErrors.add(request, "error");
		}
		
		SessionMessages.add(request, "success");
	}

	public void cancelActivitat(ActionRequest request, ActionResponse response) {
		Integer idActivitat = ParamUtil.getInteger(request, "idActivitat");
		String  cancelMotiu = ParamUtil.getString(request, "motiuCancel");
		
		try {
			ActivitatDAO.cancelActivitat(idActivitat, cancelMotiu);
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
						
			List<Activitat> activitats = ActivitatDAO.getActivitatsAll();
			
			if ( activitats.size() > 0 ) {
				renderRequest.setAttribute("activitats", activitats);
			}
			
			super.render(renderRequest, renderResponse);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
						
			e.printStackTrace();
		}
		
	}
	
	
}

package com.liferay.docs.spae.controller;


import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.liferay.docs.spae.model.InscripcioDAO;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;


public class InscripcionsController extends MVCPortlet {
	
	/**
	 * Dona alta la subscripcio a una activitat
	 * @param request
	 * @param response
	 */
	public void addInscripcio(ActionRequest request, ActionResponse response) {
		
		
		try {
			Integer idActivitat = ParamUtil.getInteger(request,"activitatId");
			Integer idAlumne    = ParamUtil.getInteger(request,"idalumne");
			String comentari = ParamUtil.getString(request, "comentari");
					
			InscripcioDAO.addInscripcio(idAlumne, idActivitat, comentari); 
		
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
	public void confirmInscripcio(ActionRequest request, ActionResponse response) {
		Integer idInscripcio = ParamUtil.getInteger(request, "idInscripcio");
		
		try {
			InscripcioDAO.confirmaInscripcio(idInscripcio);
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
	public void cancelInscripcio(ActionRequest request, ActionResponse response) {
		Integer idInscripcio = ParamUtil.getInteger(request, "idInscripcio");
		String  cancelMotiu  = ParamUtil.getString(request, "motiuCancel");
				
		try {			
			InscripcioDAO.cancelInscripcio(idInscripcio, cancelMotiu);
		} catch(Exception e) {
			SessionErrors.add(request, "error");
		}
		
		SessionMessages.add(request, "success");
	}
	
}

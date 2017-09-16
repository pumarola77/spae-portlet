package com.liferay.docs.spae.controller;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.docs.spae.model.CalculPlanificacio;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

public class PlanificadorController extends MVCPortlet {

	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	
	@ProcessAction(name="propostaPlanificador")
	public void propostaPlanificador(ActionRequest request, ActionResponse response) {
	
	}
	
	/**
	 * 
	 */
	public void render (RenderRequest renderRequest, RenderResponse renderResponse ) {
				
		try {
									
			Integer categoriaId = ParamUtil.getInteger(renderRequest, "categoriaId");
									
			renderRequest.setAttribute("propostaplan", CalculPlanificacio.calculPlanificador(categoriaId) );
			
			super.render(renderRequest, renderResponse);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
						
			e.printStackTrace();
		}
		
	}	
	
}

<%@ include file="/views/init.jsp"%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<%@ page import="com.liferay.docs.spae.model.Assistencia" %>

<%
   String mvcPath = ParamUtil.getString(request, "mvcPath");

   ResultRow row = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
   
   Assistencia assistencia = (Assistencia) row.getObject();
%>

<liferay-ui:icon-menu>
          
	<portlet:actionURL name="confirmarAssistencia" var="confirmarAssistenciaURL">
		<portlet:param name="inscripcioId"
			value="<%=String.valueOf(assistencia.getIdInscripcio().getIdInscripcio())%>" />
		<portlet:param name="activitatId"
			value="<%=String.valueOf(assistencia.getIdActivitat().getIdActivitat()) %>"/>
		<portlet:param name="dataAssistencia"
			value="<%=String.valueOf(assistencia.getDataAssistencia()) %>" />
	</portlet:actionURL>
	
	<portlet:actionURL name="faltaAssistencia" var="noAssistenciaURL">
		<portlet:param name="inscripcioId"
			value="<%=String.valueOf(assistencia.getIdInscripcio().getIdInscripcio()) %>" />
		<portlet:param name="activitatId"
			value="<%=String.valueOf(assistencia.getIdActivitat().getIdActivitat()) %>" />
		<portlet:param name="dataAssistencia"
			value="<%=String.valueOf(assistencia.getDataAssistencia()) %>" />
	</portlet:actionURL>
	
	<portlet:actionURL name="addComentari" var="comentariURL">
		<portlet:param name="inscripcioId"
			value="<%=String.valueOf(assistencia.getIdInscripcio().getIdInscripcio()) %>" />
	</portlet:actionURL>
	
				
	<liferay-ui:icon image="confirm" message="Realitzada" src="/spae-portlet/docroot/icons/index.png" url="<%=confirmarAssistenciaURL.toString() %>" />
	<liferay-ui:icon image="confirm" message="No Realitzada" src="/spae-portlet/docroot/icons/index.png" url="<%=noAssistenciaURL.toString() %>" />
	<liferay-ui:icon image="confirm" message="Afegir Comentari" src="/spae-portlet/docroot/icons/index.png" url="<%=comentariURL.toString() %>" />
</liferay-ui:icon-menu>
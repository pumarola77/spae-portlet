<%@ include file="/views/init.jsp"%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page import="com.liferay.docs.spae.model.Inscripcio" %>

<%
   String mvcPath = ParamUtil.getString(request, "mvcPath");

   ResultRow row = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

   Inscripcio inscripcio = (Inscripcio) row.getObject();
%>

<liferay-ui:icon-menu>
          
    <portlet:actionURL name="confirmInscripcio" var="confirmURL">
		<portlet:param name="idInscripcio"
			value="<%=String.valueOf(inscripcio.getIdInscripcio()) %>" />
	</portlet:actionURL>      
          
	<portlet:renderURL var="cancelar">
		<portlet:param name="idInscripcio"
			value="<%=String.valueOf(inscripcio.getIdInscripcio()) %>" />
		<portlet:param name="mvcPath"
			value="/views/inscripcions/cancelarInscripcio.jsp" />
	</portlet:renderURL>
	
			
	<liferay-ui:icon image="cancelar" message="Cancelar" url="<%=cancelar.toString() %>" />
	<liferay-ui:icon image="confirm" message="Confirmar" src="/spae-portlet/docroot/icons/index.png" url="<%=confirmURL.toString() %>" />
	
</liferay-ui:icon-menu>
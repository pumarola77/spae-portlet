<%@ include file="/views/init.jsp"%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<%
   String mvcPath = ParamUtil.getString(request, "mvcPath");

   ResultRow row = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

   Activitat activitat = (Activitat) row.getObject();
%>

<liferay-ui:icon-menu>
          
	<portlet:renderURL var="editURL">
		<portlet:param name="activitatId"
			value="<%=String.valueOf(activitat.getIdActivitat()) %>" />
		<portlet:param name="mvcPath"
			value="/views/activitats/editActivitat.jsp" />
	</portlet:renderURL>
	
	<portlet:actionURL name="confirmActivitat" var="confirmURL">
		<portlet:param name="activitatId"
			value="<%=String.valueOf(activitat.getIdActivitat()) %>" />
	</portlet:actionURL>
	
	<portlet:renderURL var="cancelURL">
		<portlet:param name="activitatId"
			value="<%=String.valueOf(activitat.getIdActivitat()) %>" />
		<portlet:param name="mvcPath"
			value="/views/activitats/cancelActivitat.jsp" />
	</portlet:renderURL>
		
		
	<liferay-ui:icon image="edit" message="Edit" url="<%=editURL.toString() %>" />
	<liferay-ui:icon image="confirm" message="Confirmar" src="/spae-portlet/docroot/icons/index.png" url="<%=confirmURL.toString() %>" />
	<liferay-ui:icon image="cancel" message="Cancelar" url="<%=cancelURL.toString() %>" />
	
</liferay-ui:icon-menu>
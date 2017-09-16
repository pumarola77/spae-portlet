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
          
	<portlet:renderURL var="validarAssistenciaURL">
		<portlet:param name="activitatId"
			value="<%=String.valueOf(activitat.getIdActivitat()) %>" />
		<portlet:param name="mvcPath"
			value="/views/assistencia/validarAssistencia.jsp" />
	</portlet:renderURL>
				
	<liferay-ui:icon image="edit" message="Validar Assistencia" url="<%= validarAssistenciaURL.toString() %>" />
	
</liferay-ui:icon-menu>
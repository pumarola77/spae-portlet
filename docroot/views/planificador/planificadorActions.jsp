<%@ include file="/views/init.jsp"%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<%@ page import="com.liferay.docs.spae.model.Categoria" %>

<%
   String mvcPath = ParamUtil.getString(request, "mvcPath");

   ResultRow row = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

   Categoria categoria = (Categoria) row.getObject();
%>

<liferay-ui:icon-menu>
          
	<portlet:renderURL var="propostaURL">
		<portlet:param name="categoriaId"
			value="<%=String.valueOf(categoria.getIdCategoria()) %>" />
		<portlet:param name="mvcPath"
			value="/views/planificador/propostaPlan.jsp" />
	</portlet:renderURL>
				
	<liferay-ui:icon image="edit" message="Proposta" url="<%= propostaURL.toString() %>"/>
	
</liferay-ui:icon-menu>


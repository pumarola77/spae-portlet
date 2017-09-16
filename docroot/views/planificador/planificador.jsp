<%@ include file = "/views/init.jsp" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ page import="javax.portlet.PortletURL"%>
<%@ page import="com.liferay.portal.kernel.dao.search.SearchContainer" %>

<%@ page import="com.liferay.docs.spae.model.Categoria" %>
<%@ page import="com.liferay.docs.spae.model.CategoriaDAO" %>
<%@ page import="java.util.List" %>

<portlet:defineObjects />

<%   
   List<Categoria> categories = CategoriaDAO.getCategories();
%>

<liferay-ui:search-container emptyResultsMessage="No existeixen Categories">
	
	<liferay-ui:search-container-results
    	results="<%= categories %>"
	/>
	
	<liferay-ui:search-container-row
        className="com.liferay.docs.spae.model.Categoria"
        modelVar="categoria"
    >
	
	<liferay-ui:search-container-column-text 
		name="Categoria" property="nom" />
		
    <liferay-ui:search-container-column-text 
		name="Descripcio" property="descripcio" />
	
	<liferay-ui:search-container-column-jsp
       	path="/views/planificador/planificadorActions.jsp"
       	align="right" />
	
    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator/>
</liferay-ui:search-container>


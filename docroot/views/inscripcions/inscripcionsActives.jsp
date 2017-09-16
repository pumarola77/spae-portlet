<%@ include file = "/views/init.jsp" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ page import="javax.portlet.PortletURL"%>
<%@ page import="com.liferay.portal.kernel.dao.search.SearchContainer" %>

<%@ page import="com.liferay.docs.spae.model.Inscripcio" %>
<%@ page import="com.liferay.docs.spae.model.InscripcioDAO" %>
<%@ page import="java.util.List" %>

<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>
<%@ page import="com.liferay.portal.model.User" %>
<%@ page import="com.liferay.portal.theme.ThemeDisplay" %>

<portlet:defineObjects />

<liferay-ui:success key="success" message="Inscripcio saved successfully!"/>
<liferay-ui:error key="error" message="Sorry, an error prevented saving your Inscripcio" />


<%

   ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
   User user = themeDisplay.getUser();  
   String emailAddress = user.getEmailAddress();
   
   List<Inscripcio> inscripcions = InscripcioDAO.getInscripcionsActives(emailAddress);
%>

<liferay-ui:search-container emptyResultsMessage="No hi han Inscripcions Actives">
	
    <liferay-ui:search-container-results
    	results="<%= inscripcions %>"
	/>

    <liferay-ui:search-container-row
        className="com.liferay.docs.spae.model.Inscripcio"
        keyProperty="idInscripcio"
        modelVar="inscripcio"
    >

      <liferay-ui:search-container-column-text 
		name="Num. Inscripcio" property="numInscripcio" />
		
      <liferay-ui:search-container-column-text 
		name="Alumne" value="<%= inscripcio.getIdAlumne().getNom() %>" />
		
      <liferay-ui:search-container-column-text 
		name="Activitat" value="<%= inscripcio.getIdActivitat().getNomActivitat() %>" />
			
      <liferay-ui:search-container-column-text 
		name="Activa" property="activa" />

      <liferay-ui:search-container-column-text 
		name="Confirmada" property="confirmada" />

      <liferay-ui:search-container-column-text 
		name="Cancelada" property="cancelada" />
		
	  <liferay-ui:search-container-column-jsp
       	path="/views/inscripcions/inscripcionsActivesActions.jsp"
       	align="right" />
        
    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator/>
</liferay-ui:search-container>

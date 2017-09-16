<%@ include file = "/views/init.jsp" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ page import="javax.portlet.PortletURL"%>
<%@ page import="com.liferay.portal.kernel.dao.search.SearchContainer" %>

<%@ page import="com.liferay.docs.spae.model.Activitat" %>
<%@ page import="com.liferay.docs.spae.model.InscripcioDAO" %>
<%@ page import="java.util.List" %>

<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>
<%@ page import="com.liferay.portal.model.User" %>
<%@ page import="com.liferay.portal.theme.ThemeDisplay" %>

<portlet:defineObjects />

<liferay-ui:success key="success" message="Assistencia saved successfully!"/>
<liferay-ui:error key="error" message="Sorry, an error prevented saving your Assistencia" />

<%

   ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
   User user = themeDisplay.getUser();  
   String emailAddress = user.getEmailAddress();
   
   List<Activitat> activitats = InscripcioDAO.getActivitatsInscripcions();
%>

<liferay-ui:search-container emptyResultsMessage="No hi han activitats Pendents Inscripcio">
	
    <liferay-ui:search-container-results
    	results="<%= activitats %>"
	/>

    <liferay-ui:search-container-row
        className="com.liferay.docs.spae.model.Activitat"
        modelVar="activitat"
    >

      <liferay-ui:search-container-column-text 
		name="Nom" property="nomActivitat" />
		
      <liferay-ui:search-container-column-text 
		name="Descripcio" property="descripcio" />
		
      <liferay-ui:search-container-column-text 
		name="Curs Academic" property="cursAcademic" />
			
      <liferay-ui:search-container-column-text 
		name="Cicle" property="cicle" />

      <liferay-ui:search-container-column-text 
		name="Trimestre" property="trimestre" />
		
     <liferay-ui:search-container-column-text 
		name="Hora Inici" property="horaInici" />
		
		
	  <liferay-ui:search-container-column-jsp
       	path="/views/assistencia/assistenciaActions.jsp"
       	align="right" />
        
    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator/>
</liferay-ui:search-container>

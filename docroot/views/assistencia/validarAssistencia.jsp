<%@ include file = "/views/init.jsp" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ page import="javax.portlet.PortletURL"%>
<%@ page import="com.liferay.portal.kernel.dao.search.SearchContainer" %>
<%@ page import="com.liferay.docs.spae.model.Assistencia" %>
<%@ page import="com.liferay.docs.spae.model.AssistenciaDAO" %>
<%@ page import="java.util.List" %>

<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>
<%@ page import="com.liferay.portal.model.User" %>
<%@ page import="com.liferay.portal.theme.ThemeDisplay" %>
<%@ page import="com.liferay.portal.kernel.dao.search.RowChecker"%>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayPortletMode" %>
<%@ page import="com.liferay.portal.kernel.language.UnicodeLanguageUtil" %>
<%@ page import="javax.portlet.PortletPreferences" %>
<%@ page import="java.util.Date" %>

<jsp:useBean id="assistencies" type="java.util.ArrayList<com.liferay.docs.spae.model.Assistencia>" scope="request"></jsp:useBean>

<portlet:defineObjects />

<liferay-ui:success key="success" message="Assistencia saved successfully!"/>
<liferay-ui:error key="error" message="Sorry, an error prevented saving your Assistencia" />

<%
	String activitatId = ParamUtil.getString( request , "activitatId");
%>

<portlet:renderURL var="viewURL">
    <portlet:param name="mvcPath" value="/views/assistencia/assistencia.jsp"></portlet:param>
</portlet:renderURL>

<portlet:actionURL name="confirmarAssistencia" var="confirmAssistenciaURL">
	<portlet:param name="activitatId" value="<%= activitatId %>"></portlet:param>
</portlet:actionURL>

<aui:form action="<%= confirmAssistenciaURL %>" name="<portlet:namespace />fm">

<aui:input name="dtAssistencia" label="Data Assistencia" value="<%=new Date()%>" disabled="true" />

<liferay-ui:search-container delta="30" 
	emptyResultsMessage="No hi han activitats Pendents Inscripcio"
	headerNames="Nom Alumne,Cognoms Alumne,Assistir,Comentari">

    <liferay-ui:search-container-results results="<%= assistencies %>" />
	
	<liferay-ui:search-container-row
        className="com.liferay.docs.spae.model.Assistencia"
        keyProperty="idAssistencia"
        modelVar="assistencia"
    >
              
    <liferay-ui:search-container-column-text name="Nom Alumne">
    	<aui:input name='<%="nomAlumne"+assistencia.getIdInscripcio().getNumInscripcio()%>'
    		type="text" value='<%= assistencia.getIdInscripcio().getIdAlumne().getNom() %>' label="" disabled="true"/>
    </liferay-ui:search-container-column-text>
    
    <liferay-ui:search-container-column-text name="Cognoms Alumne">
    	<aui:input name='<%="cgAlumne"+assistencia.getIdInscripcio().getNumInscripcio()%>'
    		type="text" value='<%= assistencia.getIdInscripcio().getIdAlumne().getCognoms() %>'  label="" disabled="true"/>
    </liferay-ui:search-container-column-text>
    
    <liferay-ui:search-container-column-text name="Assistir"> 		
    	<aui:select name='<%="assistir"+assistencia.getIdInscripcio().getNumInscripcio()%>' 
    			value="<%= assistencia.getAssistir()%>" label="">
          	<aui:option value="P">Pendent</aui:option>
          	<aui:option value="Y">Confirmat</aui:option>
           	<aui:option value="N">Falta</aui:option>
		</aui:select>	           		
    		
    </liferay-ui:search-container-column-text>
    
    <liferay-ui:search-container-column-text name="Comentari">
    	<aui:input name='<%="comentari"+assistencia.getIdInscripcio().getNumInscripcio()%>'
    		type="textarea" value='<%= assistencia.getComentari() %>' label=""/>
    </liferay-ui:search-container-column-text>
         	
    </liferay-ui:search-container-row>
	<liferay-ui:search-iterator/>
</liferay-ui:search-container>


	<aui:button-row>
		<aui:button type="submit" value="Confirma Assistencia"></aui:button>
		<aui:button type="submit" value="Cancelar" onClick="<%= viewURL %>"></aui:button>	    
	</aui:button-row>


</aui:form>


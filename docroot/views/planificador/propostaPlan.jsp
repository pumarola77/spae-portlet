<%@ include file = "/views/init.jsp" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>

<%@ page import="java.util.Date" %>

<jsp:useBean id="propostaplan" type="java.util.ArrayList<com.liferay.docs.spae.model.Planificador>" scope="request"></jsp:useBean>

<portlet:defineObjects />

<portlet:renderURL var="viewURL">
    <portlet:param name="mvcPath" value="/views/planificador/planificador.jsp"></portlet:param>
</portlet:renderURL>

<portlet:actionURL name="propostaPlanificador" var="propostaURL">
</portlet:actionURL>

<aui:form action="<%= propostaURL %>" name="<portlet:namespace />fm">

	
	<liferay-ui:search-container delta="30" orderByCol="nomMetaActivitat" orderByType="asc"
	emptyResultsMessage="No hi ha proposta de Planificacio"
	>

    	<liferay-ui:search-container-results results="<%= propostaplan %>" />
	
		<liferay-ui:search-container-row
    	    className="com.liferay.docs.spae.model.Planificador"
    	    modelVar="planificador"
   		 >

		 <liferay-ui:search-container-column-text property="nomMetaActivitat" orderable="true" name="MetaActivitat" orderableProperty="nomMetaActivitat" />        
         <liferay-ui:search-container-column-text property="cicle" orderable="true" name="Cicle" orderableProperty="cicle" />
         <liferay-ui:search-container-column-text property="cursAcademic" orderable="true" name="Curs Acadèmic" orderableProperty="cursAcademic" />
         <liferay-ui:search-container-column-text property="indexReferencia" orderable="true" name="Index Referència" orderableProperty="indexReferencia" />
         		
    	</liferay-ui:search-container-row>
		<liferay-ui:search-iterator/>
	</liferay-ui:search-container>

	<aui:button-row>
		<aui:button type="submit" value="Acceptar" onClick="<%= viewURL %>"></aui:button>	    
	</aui:button-row>

</aui:form>
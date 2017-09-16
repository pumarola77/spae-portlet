<%@ include file = "/views/init.jsp" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>

<%@ page import="com.liferay.docs.spae.model.Inscripcio" %>
<%@ page import="com.liferay.docs.spae.model.InscripcioDAO" %>

<%
   Inscripcio inscripcio = null;

   Integer idInscripcio = ParamUtil.getInteger(request, "idInscripcio");

   if ( idInscripcio > 0) {
	  inscripcio = InscripcioDAO.getInscripcioId(idInscripcio);			     
   }
%>

<portlet:renderURL var="viewURL">
        <portlet:param name="mvcPath" value="/views/inscripcions/inscripcionsActives.jsp"></portlet:param>
</portlet:renderURL>

<portlet:actionURL name='<%= "cancelInscripcio" %>' var="cancelInscripcioURL" />

<aui:model-context bean="<%= inscripcio %>" model="<%= Inscripcio.class %>" />

<aui:form action="<%= cancelInscripcioURL %>" name="<portlet:namespace />fm">

	<aui:fieldset column="true">
    <aui:layout>
	<aui:column first="true">
    	<aui:input type="hidden" name="idInscripcio"
                       value='<%= inscripcio == null ? "" : inscripcio.getIdInscripcio() %>' />                   
    </aui:column>
 
 	<aui:column width="10">
 			
 	</aui:column>
 	
 	<aui:column>	
		<aui:select name="motiuCancel" label="Motiu Cancel.lació">
           		<aui:option value="1">Incompatabilitat Activitat</aui:option>
           		<aui:option value="2">Inconformitat Preu</aui:option>
           		<aui:option value="3">No satisfa les expectetives</aui:option>
           		<aui:option value="4">Ha escollit una altra activitat</aui:option>
           		<aui:option value="5">Altres</aui:option>
       	</aui:select>	
	</aui:column>
 	
 	
 	</aui:layout>   		
    </aui:fieldset>


    <aui:button-row>
        <aui:button type="submit" value="Guardar"></aui:button>
        <aui:button type="cancel" value="Cancelar" onClick="<%= viewURL %>"></aui:button>
    </aui:button-row>
</aui:form>


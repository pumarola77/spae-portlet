<%@ include file = "/views/init.jsp" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>

<%@ page import="com.liferay.docs.spae.model.Alumne" %>
<%@ page import="com.liferay.docs.spae.model.InscripcioDAO" %>
<%@ page import="java.util.List" %>

<%@ page import="com.liferay.portal.kernel.util.WebKeys" %>
<%@ page import="com.liferay.portal.model.User" %>
<%@ page import="com.liferay.portal.theme.ThemeDisplay" %>

<portlet:defineObjects />

<%
   Activitat activitat = null;

   Integer activitatId = ParamUtil.getInteger(request, "activitatId");
   ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
   User user = themeDisplay.getUser();
   
   String emailAddress = user.getEmailAddress();
   List<Alumne> listAlumne = InscripcioDAO.getNotInscripcioAlumnes(emailAddress, activitatId);
%>

<portlet:renderURL var="viewURL">
    <portlet:param name="mvcPath" value="/views/inscripcions/inscripcions.jsp"></portlet:param>
</portlet:renderURL>

<portlet:actionURL name="addInscripcio" var="addInscripcioURL"></portlet:actionURL>

<aui:model-context bean="<%= listAlumne %>" model="<%= Alumne.class %>" />

<aui:form action="<%= addInscripcioURL %>" name="<portlet:namespace />fm">

	<aui:fieldset column="true">
	<aui:layout>	
		<aui:column columnWidth="25" first="true">
		    <aui:input type="hidden" name="activitatId"
                       value='<%=activitatId%>' />
                        
       		<aui:select name="idalumne" label="Alumne">
       			<%for (Alumne alumne : listAlumne) {%>
       				<option value="<%=alumne.getIdAlumne() %>"><%=alumne.getNom()%>  <%=alumne.getCognoms() %></option>
       			<%}%>
       		</aui:select>	
                                       		
       		<aui:input name="comentari" id="comentari" type="text" label="Comentari">
       			
       		</aui:input>
		</aui:column>
	
	</aui:layout>
	</aui:fieldset>


    <aui:button-row>

         <aui:button type="submit" value="Guardar"></aui:button>
         <aui:button type="cancel" value="Cancelar" onClick="<%= viewURL.toString() %>"></aui:button>

    </aui:button-row>


</aui:form>
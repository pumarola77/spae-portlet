<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>

<%@ page import="java.util.List" %>
<%@ page import="com.liferay.docs.spae.model.Categoria" %>
<%@ page import="com.liferay.docs.spae.model.CategoriaDAO" %>

<portlet:defineObjects />

<%
	List<Categoria> listCategories = CategoriaDAO.getCategories();
%>

<portlet:renderURL var="viewURL">
    <portlet:param name="mvcPath" value="/views/metaactivitats/metaactivitat.jsp"></portlet:param>
</portlet:renderURL>

<portlet:actionURL name="addEntry" var="addEntryURL"></portlet:actionURL>

<aui:form action="<%= addEntryURL %>" name="<portlet:namespace />fm">

	<aui:fieldset>

        <aui:input name="Nom" id="name" type="text" maxlength="50">
        	<aui:validator name="required" errorMessage="Entra nom de la meta-activitat." />
        </aui:input>
        <aui:input name="Descripcio" id="descripcio" type="text" maxlength="150"></aui:input>
        
        <aui:select name="idcategoria" label="Categoria">
       		<%for (Categoria categoria : listCategories) {%>
       			<option value="<%=categoria.getIdCategoria() %>"><%=categoria.getNom() %></option>
       		<%}%>
       	</aui:select>	

    </aui:fieldset>

    <aui:button-row>

         <aui:button type="submit"></aui:button>
         <aui:button type="cancel" onClick="<%= viewURL.toString() %>"></aui:button>

    </aui:button-row>


</aui:form>



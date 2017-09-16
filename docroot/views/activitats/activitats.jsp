<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<portlet:defineObjects />

<liferay-ui:success key="success" message="Activitat saved successfully!"/>
<liferay-ui:error key="error" message="Sorry, an error prevented saving your Activitat" />

<jsp:useBean id="activitats" class="java.util.ArrayList" scope="request"/>

<aui:button-row cssClass="Activitats-buttons">

	<portlet:renderURL var="addEntryURL">
        <portlet:param name="mvcPath" value="/views/activitats/addActivitat.jsp"></portlet:param>
    </portlet:renderURL>

    <aui:button onClick="<%= addEntryURL.toString() %>" value="Add Entry"></aui:button>
</aui:button-row>

<liferay-ui:search-container>
    <liferay-ui:search-container-results
    	results="<%= activitats %>"
	/>

    <liferay-ui:search-container-row
        className="com.liferay.docs.spae.model.Activitat"
        modelVar="activitat"
    >
        
        <liferay-ui:search-container-column-text property="nomActivitat" />        
        <liferay-ui:search-container-column-text property="descripcio" />
        <liferay-ui:search-container-column-text property="cursAcademic" />
        <liferay-ui:search-container-column-text property="cicle" />
        <liferay-ui:search-container-column-text property="trimestre" />
        <liferay-ui:search-container-column-text property="confirmada" />
        <liferay-ui:search-container-column-text property="cancelada" />
        
        <liferay-ui:search-container-column-jsp
        	path="/views/activitats/activitatActions.jsp"
        	align="right" />
        
    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator />
</liferay-ui:search-container>





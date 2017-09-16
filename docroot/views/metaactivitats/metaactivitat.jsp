<%
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>


<jsp:useBean id="metaactivitats" class="java.util.ArrayList" scope="request"></jsp:useBean>

<portlet:defineObjects />

<liferay-ui:success key="success" message="MetaActivitat saved successfully!"/>
<liferay-ui:error key="error" message="Sorry, an error prevented saving your MetaActivitat" />

<aui:button-row cssClass="metaActivitats-buttons">

	<portlet:renderURL var="addEntryURL">
        <portlet:param name="mvcPath" value="/views/metaactivitats/addMetaActivitat.jsp"></portlet:param>
    </portlet:renderURL>

    <aui:button onClick="<%= addEntryURL.toString() %>" value="Add Entry"></aui:button>
</aui:button-row>

<liferay-ui:search-container emptyResultsMessage="No hi han MetaActivitats">

    <liferay-ui:search-container-results
    	results="<%= metaactivitats %>"
	/>

    <liferay-ui:search-container-row
        className="com.liferay.docs.spae.model.MetaActivitat"
        modelVar="metaactivitat"
    >
        
        <liferay-ui:search-container-column-text property="nom" />        
        <liferay-ui:search-container-column-text property="descripcio" />
        <liferay-ui:search-container-column-text 
        	name="categoria" value="<%=metaactivitat.getCategoria().getNom() %>" />
        
        
        <liferay-ui:search-container-column-jsp
        	path="/views/metaactivitats/metaActivitatActions.jsp"
        	align="right" />
        
    </liferay-ui:search-container-row>

    <liferay-ui:search-iterator />
</liferay-ui:search-container>




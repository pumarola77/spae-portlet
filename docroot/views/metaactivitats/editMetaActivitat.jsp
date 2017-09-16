<%@ include file = "/views/init.jsp" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>

<%@ page import="com.liferay.docs.spae.model.MetaActivitatDAO" %>

<%
   MetaActivitat metaactivitat = null;

   Integer metaActivitatId = ParamUtil.getInteger(request, "metaActivitatId");

   if ( metaActivitatId > 0) {
	   metaactivitat = MetaActivitatDAO.getMetaActivitatId(metaActivitatId);
   }
%>

<portlet:renderURL var="viewURL">
        <portlet:param name="mvcPath" value="/views/metaactivitats/metaactivitat.jsp"></portlet:param>
</portlet:renderURL>

<portlet:actionURL name='<%= "updateMetaActivitat" %>' var="editMetaActivitatURL" />

<aui:model-context bean="<%= metaactivitat %>" model="<%= MetaActivitat.class %>" />

<aui:form action="<%= editMetaActivitatURL %>" name="<portlet:namespace />fm">
        <aui:fieldset>
                        <aui:input type="hidden" name="metaActivitatId"
                                value='<%= metaactivitat == null ? "" : metaactivitat.getIdMetaActivitat() %>' />
                        <aui:input name="nom" id="nom" type="hidden"/>
                        <aui:input name="descripcio" id="descripcio" type="text" maxlength="150"/>
        </aui:fieldset>

        <aui:button-row>
                        <aui:button type="submit"></aui:button>
                        <aui:button type="cancel" onClick="<%= viewURL %>"></aui:button>
        </aui:button-row>
</aui:form>
<%@ include file = "/views/init.jsp" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>

<%
   Activitat activitat = null;

   Integer activitatId = ParamUtil.getInteger(request, "activitatId");

   if ( activitatId > 0) {
	   activitat = ActivitatDAO.getActivitatId(activitatId);
   }
%>

<portlet:renderURL var="viewURL">
        <portlet:param name="mvcPath" value="/views/activitats/activitats.jsp"></portlet:param>
</portlet:renderURL>

<portlet:actionURL name='<%= "cancelActivitat" %>' var="cancelActivitatURL" />

<aui:model-context bean="<%= activitat %>" model="<%= Activitat.class %>" />

<aui:form action="<%= cancelActivitatURL %>" name="<portlet:namespace />fm">

    <aui:fieldset column="true">
    <aui:layout>
	<aui:column first="true">
    	<aui:input type="hidden" name="idActivitat"
                       value='<%= activitat == null ? "" : activitat.getIdActivitat() %>' />
        <aui:input name="nomActivitat" id="nomActivitat" label="Nom Activitat" disabled="true" type="text"
        	value='<%=activitat.getNomActivitat() %>'>
        </aui:input>
		<aui:input name="cursAcademic" id="cursAcademic" label="Curs Academic" disabled="true" type="text" 
			value='<%=activitat.getCursAcademic() %>'/>
		<aui:input name="cicle" id="cicle" label="Cicle" disabled="true" type="text" 
			value='<%=activitat.getCicle() %>'/>
		<aui:input name="trimestre" id="trimestre" label="Trimestre" disabled="true" type="text" 
			value='<%=activitat.getTrimestre() %>'/>
    </aui:column>
 
 	<aui:column width="10">
 			
 	</aui:column>
 
	<aui:column>
		<aui:input name="dtInici" id="dtInici" label="Data Inici" disabled="true" type="text"
			value='<%=activitat.getDtInici() %>'/>
		<aui:input name="horaInici" id="horaInici" label="Hora Inici" disabled="true" type="text" 
			value='<%=activitat.getHoraInici() %>'/>
	
		<aui:select name="motiuCancel" label="Motiu Cancel.lació">
           		<aui:option value="1">Falten alumnes</aui:option>
           		<aui:option value="2">Cancel.lada desprès Inici</aui:option>
           		<aui:option value="3">Canvi de dia o horari</aui:option>
           		<aui:option value="4">Altres</aui:option>
       	</aui:select>	
	</aui:column>
    </aui:layout>   		
    </aui:fieldset>

    <aui:button-row>
        <aui:button type="submit" value="Guardar"></aui:button>
        <aui:button type="cancel" value="Cancelar" onClick="<%= viewURL %>"></aui:button>
    </aui:button-row>
</aui:form>
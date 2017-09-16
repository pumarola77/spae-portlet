<%@ include file = "/views/init.jsp" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>

<%@ page import="com.liferay.docs.spae.model.ActivitatDAO" %>

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

<portlet:actionURL name='<%= "updateActivitat" %>' var="editActivitatURL" />

<aui:model-context bean="<%= activitat %>" model="<%= Activitat.class %>" />

<aui:form action="<%= editActivitatURL %>" name="<portlet:namespace />fm">

    <aui:fieldset column="true">
    <aui:layout>
    	<aui:column columnWidth="25" first="true">
    		<aui:input type="hidden" name="idActivitat"
                       value='<%= activitat == null ? "" : activitat.getIdActivitat() %>' />
            <aui:input name="nomActivitat" id="nomActivitat" type="hidden"/>
            <aui:input name="descripcio" id="descripcio" type="text" maxlength="150" 
            	value='<%=activitat.getDescripcio()%>'>
            </aui:input>
    	</aui:column>
    	
    	<aui:column>
       		<aui:input name="dtInici" id="dtInici" type="date" placeholder="dd/mm/yyyy" label="Data Inici"
       			value='<%=activitat.getDtInici()%>'>
       			<aui:validator name="required" errorMessage="Indicar Inici Activitat"></aui:validator>
       			<aui:validator name="date" errorMessage="Data no valida"></aui:validator>
       		</aui:input>
       		<aui:input name="dtFinal" id="dtFinal" type="date" placeholder="dd/mm/yyyy" label="Data Final"
       			value='<%=activitat.getDtFinal()%>'>
       			<aui:validator name="required" errorMessage="Indicar Final Activitat"></aui:validator>
       			<aui:validator name="date" errorMessage="Data no valida"></aui:validator>
       		</aui:input>
       		<aui:input name="horaInici" id="horaInici" type="text" maxlength="8" placeholder="00:00:00" label="Hora Inici"
       			value='<%=activitat.getHoraInici()%>'>
       			<aui:validator name="required" errorMessage="Indicar hora inici activitat"></aui:validator>
       		</aui:input>
       		<aui:input name="horaFinal" id="horaFinal" type="text" maxlength="8" placeholder="00:00:00" label="Hora Final"
       			value='<%=activitat.getHoraFinal()%>'>
       			<aui:validator name="required" errorMessage="Indicar hora final activitat"></aui:validator>
       		</aui:input>
       		<aui:input name="duracio" id="duracio" type="number" label="Duracio" value='<%=activitat.getDuracio()%>'>
       			<aui:validator name="number"></aui:validator>
       			<aui:validator name="range" errorMessage="Valor entre 1 i 180">[1,180]</aui:validator>
       		</aui:input>
		</aui:column>	

		<aui:column>
			<aui:input name="minAlumnes" id="minalumnes" type="number" label="Min. Alumnes"
				value='<%=activitat.getMinAlumnes()%>'>
       			<aui:validator name="number"></aui:validator>
       			<aui:validator name="required" errorMessage="Indicar el nombre mínim Alumnes Activitat." />
       			<aui:validator name="min" errorMessage="Valor mnim 1">1</aui:validator>
       		</aui:input>
       		<aui:input name="maxAlumnes" id="maxalumnes" type="number" label="Max. Alumnes"
       			value='<%=activitat.getMaxAlumnes()%>'>
       			<aui:validator name="number"></aui:validator>       		
       		</aui:input>	
       		<aui:select name="dies" label="Dies" value="<%=activitat.getDies()%>">
           		<aui:option value="1">Cada dia</aui:option>
           		<aui:option value="2">Cada dilluns,dimecres</aui:option>
           		<aui:option value="3">Cada dimarts,dijous</aui:option>
           		<aui:option value="4">Dilluns</aui:option>
           		<aui:option value="5">Dimarts</aui:option>
           		<aui:option value="6">Dimecres</aui:option>
           		<aui:option value="7">Dijous</aui:option>
           		<aui:option value="8">Divendres</aui:option>           		       			
       		</aui:select>		       			
		</aui:column>
            
        <aui:column>
			<aui:input name="empresaresponsable" id="empresaresponsable" type="text" maxlength="60" label="Empresa"
				value='<%=activitat.getEmpresaResponsable()%>'>
			</aui:input>
			<aui:input name="monitorresponsable" id="monitorresponsable" type="text" maxlength="60" label="Monitor"
				value='<%=activitat.getMonitorResponsable()%>'>
			</aui:input>
		</aui:column>           
       		
    </aui:layout>   		
    </aui:fieldset>

    <aui:button-row>
        <aui:button type="submit" value="Guardar"></aui:button>
        <aui:button type="cancel" value="Cancelar" onClick="<%= viewURL %>"></aui:button>
    </aui:button-row>
</aui:form>
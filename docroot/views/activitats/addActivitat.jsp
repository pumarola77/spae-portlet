<%@ include file = "/views/init.jsp" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://alloy.liferay.com/tld/aui" prefix="aui" %>

<%@ page import="com.liferay.docs.spae.model.MetaActivitat" %>
<%@ page import="com.liferay.docs.spae.model.MetaActivitatDAO" %>
<%@ page import="java.util.List" %>

<portlet:defineObjects />

<%
   List<MetaActivitat> listMetaActivitat = MetaActivitatDAO.getMetaActivitatsAll();
%>

<portlet:renderURL var="viewURL">
    <portlet:param name="mvcPath" value="/views/activitats/activitats.jsp"></portlet:param>
</portlet:renderURL>

<portlet:actionURL name="addEntry" var="addEntryURL"></portlet:actionURL>

<aui:form action="<%= addEntryURL %>" name="<portlet:namespace />fm">
	
	<aui:fieldset column="true">
	<aui:layout>
		<aui:column columnWidth="25" first="true">
			<aui:input name="nomactivitat" id="name" type="text" maxlength="60" label="Nom Activitat">
       			<aui:validator name="required" errorMessage="Entra nom de la activitat." />
       		</aui:input>
       		<aui:input name="descripcio" id="descripcio" type="text" maxlength="250" label="Descripcio"></aui:input>
       		<aui:select name="cursacademic" label="Curs Academic">
       			<aui:option value="2015">2015-2016</aui:option>
         		<aui:option value="2016">2016-2017</aui:option>
       		</aui:select>
       		<aui:select name="cicle" label="Cicle">
           		<aui:option value="P3">Infantil P3</aui:option>
           		<aui:option value="P4-P5">Intanfil P4-P5</aui:option>
           		<aui:option value="Primaria1">Primaria 1</aui:option>
           		<aui:option value="Primaria2">Primaria 2</aui:option>           		
       		</aui:select>
       		<aui:select name="trimestre" label="Trimestre">
           		<aui:option value="Primer">Primer</aui:option>
           		<aui:option value="Segon">Segon</aui:option>
           		<aui:option value="Tercer">Tercer</aui:option>
           		<aui:option value="Anual">Anual</aui:option>           		
       		</aui:select>
		</aui:column>
    	
    	<aui:column>
       		<aui:input name="dtInici" id="dtinici" type="date" placeholder="dd/mm/yyyy" label="Data Inici">
       			<aui:validator name="required" errorMessage="Indicar Inici Activitat"></aui:validator>
       			<aui:validator name="date" errorMessage="Data no valida"></aui:validator>
       		</aui:input>
       		<aui:input name="dtFinal" id="dtfinal" type="date" placeholder="mm/dd/yyyy" label="Data Final">
       			<aui:validator name="required" errorMessage="Indicar Final Activitat"></aui:validator>
       			<aui:validator name="date" errorMessage="Data no valida"></aui:validator>
       		</aui:input>
       		<aui:input name="horaInici" id="horainici" type="text" maxlength="8" placeholder="00:00:00" label="Hora Inici">
       			<aui:validator name="required" errorMessage="Indicar hora inici activitat"></aui:validator>
       		</aui:input>
       		<aui:input name="horaFinal" id="horafinal" type="text" maxlength="8" placeholder="00:00:00" label="Hora Final">
       			<aui:validator name="required" errorMessage="Indicar hora final activitat"></aui:validator>
       		</aui:input>
       		<aui:input name="duracio" id="duracio" type="number" label="Duracio">
       			<aui:validator name="number"></aui:validator>
       			<aui:validator name="range" errorMessage="Valor entre 1 i 180">[1,180]</aui:validator>
       		</aui:input>
		</aui:column>	
		
		<aui:column>
       		<aui:input name="minAlumnes" id="minalumnes" type="number" value="1" label="Min. Alumnes">
       			<aui:validator name="number"></aui:validator>
       			<aui:validator name="required" errorMessage="Indicar el nombre mínim Alumnes Activitat." />
       			<aui:validator name="min" errorMessage="Valor mnim 1">1</aui:validator>
       		</aui:input>
       		<aui:input name="maxAlumnes" id="maxalumnes" type="number" value="0" label="Max. Alumnes">
       			<aui:validator name="number"></aui:validator>       		
       		</aui:input>		
       		<aui:select name="idmetaactivitat" label="MetaActivitat">
       			<%for (MetaActivitat metaactivitat : listMetaActivitat) {%>
       				<option value="<%=metaactivitat.getIdMetaActivitat()%>"><%=metaactivitat.getNom()%></option>
       			<%}%>
       		</aui:select>	
       		<aui:select name="dies" label="Dies">
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
			<aui:input name="empresaresponsable" id="empresaresponsable" type="text" maxlength="60" label="Empresa">
			</aui:input>
			<aui:input name="monitorresponsable" id="monitorresponsable" type="text" maxlength="60" label="Monitor">
			</aui:input>
		</aui:column>
		
		
	</aui:layout>
	</aui:fieldset>
    

    <aui:button-row>

         <aui:button type="submit" value="Guardar"></aui:button>
         <aui:button type="cancel" value="Cancelar" onClick="<%= viewURL.toString() %>"></aui:button>

    </aui:button-row>


</aui:form>

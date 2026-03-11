<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ include file="init.jsp" %>

<portlet:actionURL name="addEmployee" var="addEmployeeURL"/>

<h3>Add Employee</h3>

<aui:form action="<%=addEmployeeURL%>" method="post">

    <aui:input name="employeeName" label="Employee Name" />

    <aui:input name="email" label="Email" />

    <aui:input name="department" label="Department" />

    <aui:input name="salary" label="Salary" />

    <aui:button type="submit" value="Save" cssClass="btn-success"/>

</aui:form>
<%@ include file="init.jsp" %>
<portlet:defineObjects/>

<%@ page import="com.employee.model.Employee" %>
<%@ page import="com.employee.service.EmployeeLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>

<%
    long employeeId = ParamUtil.getLong(request,"employeeId");
    Employee employee = EmployeeLocalServiceUtil.getEmployee(employeeId);
%>

<portlet:actionURL name="updateEmployee" var="updateEmployeeURL"/>

<h3>Edit Employee</h3>

<aui:form action="<%=updateEmployeeURL%>" method="post">

    <aui:input name="employeeId" type="hidden" value="<%=employee.getEmployeeId()%>" />

    <aui:input name="employeeName" value="<%=employee.getEmployeeName()%>" />

    <aui:input name="email" value="<%=employee.getEmail()%>" />

    <aui:input name="department" value="<%=employee.getDepartment()%>" />

    <aui:input name="salary" value="<%=employee.getSalary()%>" />

    <aui:button type="submit" value="Update" cssClass="btn-primary"/>

</aui:form>
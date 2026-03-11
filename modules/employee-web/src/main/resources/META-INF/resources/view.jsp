<%@ include file="/init.jsp" %><%@ page import="java.util.List" %>
<%@ include file="init.jsp" %>
<portlet:defineObjects/>

<%@ page import="java.util.List" %>
<%@ page import="com.employee.model.Employee" %>
<%@ page import="com.employee.service.EmployeeLocalServiceUtil" %>

<%
	List<Employee> employees = EmployeeLocalServiceUtil.getEmployees(-1,-1);
%>

<h2>Employee Management</h2>

<portlet:renderURL var="addURL">
	<portlet:param name="mvcPath" value="/add_employee.jsp"/>
</portlet:renderURL>

<aui:button href="<%=addURL%>" value="Add Employee" cssClass="btn-primary"/>

<br><br>

<table class="table table-bordered table-hover">

	<tr>
		<th>ID</th>
		<th>Name</th>
		<th>Email</th>
		<th>Department</th>
		<th>Salary</th>
		<th>Actions</th>
	</tr>

	<%
		for(Employee emp : employees){
	%>

	<tr>

		<td><%=emp.getEmployeeId()%></td>
		<td><%=emp.getEmployeeName()%></td>
		<td><%=emp.getEmail()%></td>
		<td><%=emp.getDepartment()%></td>
		<td><%=emp.getSalary()%></td>

		<td>

			<portlet:renderURL var="editURL">
				<portlet:param name="mvcPath" value="/edit_employee.jsp"/>
				<portlet:param name="employeeId" value="<%=String.valueOf(emp.getEmployeeId())%>"/>
			</portlet:renderURL>

			<aui:button href="<%=editURL%>" value="Edit" cssClass="btn-warning"/>

			<portlet:actionURL name="deleteEmployee" var="deleteURL">
				<portlet:param name="employeeId" value="<%=String.valueOf(emp.getEmployeeId())%>"/>
			</portlet:actionURL>

			<aui:button href="<%=deleteURL%>" value="Delete" cssClass="btn-danger"/>

		</td>

	</tr>

	<%
		}
	%>

</table>
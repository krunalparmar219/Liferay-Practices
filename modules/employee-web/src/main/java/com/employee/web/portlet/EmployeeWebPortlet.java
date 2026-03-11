package com.employee.web.portlet;

import com.employee.model.Employee;
import com.employee.service.EmployeeLocalService;
import com.employee.web.constants.EmployeeWebPortletKeys;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.ProcessAction;

import com.liferay.portal.kernel.util.ParamUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author ignek
 */
@Component(
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=EmployeeWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + EmployeeWebPortletKeys.EMPLOYEEWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class EmployeeWebPortlet extends MVCPortlet {

	private Log log = LogFactoryUtil.getLog(EmployeeWebPortlet.class);

	@Reference
	EmployeeLocalService employeeLocalService;

	@Reference
	CounterLocalService counterLocalService;

	// ADD EMPLOYEE
	@ProcessAction(name = "addEmployee")
	public void addEmployee(ActionRequest request, ActionResponse response) {

		String employeeName = ParamUtil.getString(request, "employeeName");
		String email = ParamUtil.getString(request, "email");
		String department = ParamUtil.getString(request, "department");
		double salary = ParamUtil.getDouble(request, "salary");

		long employeeId = counterLocalService.increment(Employee.class.getName());

		Employee employee = employeeLocalService.createEmployee(employeeId);

		employee.setEmployeeName(employeeName);
		employee.setEmail(email);
		employee.setDepartment(department);
		employee.setSalary(salary);

		employeeLocalService.addEmployee(employee);

	}

	// UPDATE EMPLOYEE
	@ProcessAction(name = "updateEmployee")
	public void updateEmployee(ActionRequest request, ActionResponse response) {

		long employeeId = ParamUtil.getLong(request, "employeeId");

		String employeeName = ParamUtil.getString(request, "employeeName");
		String email = ParamUtil.getString(request, "email");
		String department = ParamUtil.getString(request, "department");
		double salary = ParamUtil.getDouble(request, "salary");

		try {

			Employee employee = employeeLocalService.getEmployee(employeeId);

			employee.setEmployeeName(employeeName);
			employee.setEmail(email);
			employee.setDepartment(department);
			employee.setSalary(salary);

			employeeLocalService.updateEmployee(employee);

		} catch (Exception e) {
			log.error(e);
		}

	}

	// DELETE EMPLOYEE
	@ProcessAction(name = "deleteEmployee")
	public void deleteEmployee(ActionRequest request, ActionResponse response) {

		long employeeId = ParamUtil.getLong(request, "employeeId");

		try {
			employeeLocalService.deleteEmployee(employeeId);
		} catch (Exception e) {
			log.error(e);
		}

	}

}
/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.employee.service.impl;

import com.employee.model.Employee;
import com.employee.service.base.EmployeeLocalServiceBaseImpl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.exception.PortalException;
import org.osgi.service.component.annotations.Component;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	property = "model.class.name=com.employee.model.Employee",
	service = AopService.class
)
public class EmployeeLocalServiceImpl extends EmployeeLocalServiceBaseImpl {

	public Employee addEmployee(
			long userId,
			String name,
			String email,
			String department,
			double salary) {

		long employeeId = counterLocalService.increment(Employee.class.getName());

		Employee employee = employeePersistence.create(employeeId);

		employee.setEmployeeName(name);
		employee.setEmail(email);
		employee.setDepartment(department);
		employee.setSalary(salary);

		return employeePersistence.update(employee);
	}

	public Employee updateEmployee(
			long employeeId,
			String name,
			String email,
			String department,
			double salary)
			throws PortalException {

		Employee employee = employeePersistence.findByPrimaryKey(employeeId);

		employee.setEmployeeName(name);
		employee.setEmail(email);
		employee.setDepartment(department);
		employee.setSalary(salary);

		return employeePersistence.update(employee);
	}
	public List<Employee> getEmployees() {
		return employeePersistence.findAll();
	}

	public Employee deleteEmployee(long employeeId)
			throws PortalException {

		return employeePersistence.remove(employeeId);
	}
}
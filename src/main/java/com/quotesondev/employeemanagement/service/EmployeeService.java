package com.quotesondev.employeemanagement.service;


import java.util.List;
import java.util.Map;
import com.quotesondev.employeemanagement.model.Employee;

public interface EmployeeService {

	Employee addEmployee(Employee employee);

	Employee updateEmployee(int empId, Employee updateEmployee);
	
	Employee updateEmployeeDepartment(int empId, int deptId);
	
	Employee getEmployeeById(int empId);

	List<Employee> getAllEmployees();

	List<Map<String, Object>> findEmployeeNamesAndIds();

}

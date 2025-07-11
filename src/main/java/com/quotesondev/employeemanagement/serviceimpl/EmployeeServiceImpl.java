package com.quotesondev.employeemanagement.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quotesondev.employeemanagement.exceptions.DepartmentNotFoundException;
import com.quotesondev.employeemanagement.exceptions.EmployeeNotFoundException;
import com.quotesondev.employeemanagement.model.Department;
import com.quotesondev.employeemanagement.model.Employee;
import com.quotesondev.employeemanagement.repository.DepartmentRepository;
import com.quotesondev.employeemanagement.repository.EmployeeRepository;
import com.quotesondev.employeemanagement.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private DepartmentRepository departmentRepo;

	
	@Override
	public Employee addEmployee(Employee employee) {
		// Automatically fetch full department if only deptId is provided
		if(employee.getDepartment() != null && employee.getDepartment().getDeptId() != 0) {
			int deptId = employee.getDepartment().getDeptId();
			Optional<Department> optional = departmentRepo.findById(deptId);
			if(optional.isEmpty()) {
				throw new DepartmentNotFoundException("Department not found with id " + deptId);
			}else {
				Department department = optional.get();
				employee.setDepartment(department);
			}
		}
		
		// Automatically fetch all the reporting manager if only empId is provided
		if(employee.getReportingManager() != null && employee.getReportingManager().getEmpId() != 0) {
			int managerId = employee.getReportingManager().getEmpId();
			Optional<Employee> optional = employeeRepo.findById(managerId);
			if(optional.isEmpty()) {
				throw new EmployeeNotFoundException("Manager not found with id " + managerId);
			}else {
				Employee manager = optional.get();
				employee.setReportingManager(manager);
			}
		}
		return employeeRepo.save(employee);
	}

	
	@Override
	public Employee updateEmployee(int empId, Employee updateEmployee) {
		Optional<Employee> optional = employeeRepo.findById(empId);
		
		if(optional.isEmpty()) {
			throw new EmployeeNotFoundException("Employee not found with id " + empId);
		}else {
			Employee existingEmployee = optional.get();
			updateEmployee.setEmpId(existingEmployee.getEmpId());
			return employeeRepo.save(updateEmployee);
		}
		
	}


	@Override
	public Employee updateEmployeeDepartment(int empId, int deptId) {
		Optional<Employee> optionalEmployee = employeeRepo.findById(empId);
		if(optionalEmployee.isEmpty()) {
			throw new EmployeeNotFoundException("Employee not found with id " + empId);
		}
		
		Optional<Department> optionalDepartment = departmentRepo.findById(deptId);
		if(optionalDepartment.isEmpty()) {
			throw new DepartmentNotFoundException("Department not found with id " + deptId);
		}
		
		Employee employee = optionalEmployee.get();
		Department department = optionalDepartment.get();
		
		employee.setDepartment(department);
		return employeeRepo.save(employee);
	}

	
	@Override
	public Employee getEmployeeById(int empId) {
		Optional<Employee> optional = employeeRepo.findById(empId);
		
		if(optional.isEmpty()) {
			throw new EmployeeNotFoundException("Employee not found with the id " + empId);
		}else {
			Employee employee = optional.get();
			return employee;
		}
	}


	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees = employeeRepo.findAll();
		
		if(employees.isEmpty()) {
			throw new EmployeeNotFoundException("Failed to find employees");
		}else {
			return employees;
		}
	}


	@Override
	public List<Map<String, Object>> findEmployeeNamesAndIds() {
		List<Map<String, Object>> list = new ArrayList<>();
		
		List<Employee> employees = employeeRepo.findAll();
		
		for(Employee emp : employees) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", emp.getEmpId());
			map.put("name", emp.getEmpName());
			list.add(map);
		}
		return list;
	}
	
	

}

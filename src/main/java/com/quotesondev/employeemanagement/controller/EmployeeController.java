package com.quotesondev.employeemanagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quotesondev.employeemanagement.model.Employee;
import com.quotesondev.employeemanagement.service.EmployeeService;
import com.quotesondev.employeemanagement.utility.ResponseStructure;




@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	// Create employee
	@PostMapping
	public ResponseEntity<ResponseStructure<Employee>> addEmployee(@RequestBody Employee employee) {
		Employee emp =  employeeService.addEmployee(employee);
		
		ResponseStructure<Employee> rs = new ResponseStructure<Employee>();
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setMessage("Employee object created successfully");
		rs.setData(emp);
		
		return new ResponseEntity<ResponseStructure<Employee>>(rs, HttpStatus.CREATED);
	}
	
	//Update employee information
	@PutMapping("/id")
	public Employee updateEmployee(@RequestParam int empId, @RequestBody Employee updateEmployee){
		return employeeService.updateEmployee(empId, updateEmployee);
	}
	
	// Update Employee's Department 
	@PutMapping("/id/department")
	public ResponseEntity<ResponseStructure<Employee>> updateEmployeeDepartment(@RequestParam int empId, @RequestParam int deptId) {
		Employee emp =  employeeService.updateEmployeeDepartment(empId, deptId);
		
		ResponseStructure<Employee> rs = new ResponseStructure<Employee>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Employee department updated successfully");
		rs.setData(emp);
		
		return new ResponseEntity<ResponseStructure<Employee>>(rs, HttpStatus.OK);
	}
	
	
	// Get employee details by id
	@GetMapping("/id")
	public ResponseEntity<ResponseStructure<Employee>> getById(@RequestParam int empId) {
		Employee employee = employeeService.getEmployeeById(empId);
		
		ResponseStructure<Employee> rs = new ResponseStructure<Employee>();
		rs.setStatusCode(HttpStatus.FOUND.value());
		rs.setMessage("Employee object found for this id");
		rs.setData(employee);
		
		return new ResponseEntity<ResponseStructure<Employee>>(rs, HttpStatus.FOUND);
		
	}
	
	// List only Employee names and IDs
	@GetMapping("/lookup")
	public ResponseEntity<ResponseStructure<List<Map<String, Object>>>> listEmployeeNamesAndIds(@RequestParam boolean lookup){
		List<Map<String, Object>> list = employeeService.findEmployeeNamesAndIds();
		
		ResponseStructure<List<Map<String, Object>>> rs = new ResponseStructure<List<Map<String,Object>>>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Employee names and ids fetched successfully");
		rs.setData(list);
		
		return new ResponseEntity<ResponseStructure<List<Map<String,Object>>>>(rs, HttpStatus.OK);
	}
	
	// Get all employees
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Employee>>> getAllEmployee(){
		List<Employee> employees = employeeService.getAllEmployees();
		ResponseStructure<List<Employee>> rs = new ResponseStructure<List<Employee>>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Employees fetched successfully");
		rs.setData(employees);
		return new ResponseEntity<ResponseStructure<List<Employee>>>(rs, HttpStatus.OK);
	}
	
	
}

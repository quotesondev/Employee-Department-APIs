package com.quotesondev.employeemanagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quotesondev.employeemanagement.model.Department;
import com.quotesondev.employeemanagement.service.DepartmentService;
import com.quotesondev.employeemanagement.utility.ResponseStructure;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
	
	@Autowired
	private DepartmentService deptService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Department>> addDepartment(@RequestBody Department department){
		Department dept = deptService.addDepartment(department);
		
		ResponseStructure<Department> rs = new ResponseStructure<Department>();
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setMessage("Department object created successfully");
		rs.setData(dept);
		
		return new ResponseEntity<ResponseStructure<Department>>(rs, HttpStatus.CREATED);
	}
	
	@PutMapping("/id")
	public ResponseEntity<ResponseStructure<Department>> updateDepartment(@RequestParam int deptId, @RequestBody Department updateDepartment) {
		Department department = deptService.updateDepartment(deptId, updateDepartment);
		
		ResponseStructure<Department> rs = new ResponseStructure<Department>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Department updated successfully");
		rs.setData(department);
		
		return new ResponseEntity<ResponseStructure<Department>>(rs, HttpStatus.OK);
	}
	
	@DeleteMapping("/id")
	public ResponseEntity<ResponseStructure<String>> deleteDepartment(@RequestParam int deptId){
		deptService.deleteDepartment(deptId);
		ResponseStructure<String> rs = new ResponseStructure<String>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Department deleted successfully");
		rs.setData("Department with ID " + deptId +" has been removed");
		
		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);
	}
	

}

package com.quotesondev.employeemanagement.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.quotesondev.employeemanagement.exceptions.DepartmentNotFoundException;
import com.quotesondev.employeemanagement.exceptions.EmployeeNotFoundException;

@RestControllerAdvice
public class ApplicationHandler {
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> employeeNotFoundHandler(EmployeeNotFoundException ex){
		ResponseStructure<String> rs = new ResponseStructure<String>();
		rs.setStatusCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage(ex.getMessage());
		rs.setData("Employee object with the requested Id is not available in the database.");
		
		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> departmentNotFoundHandler(DepartmentNotFoundException dx){
		ResponseStructure<String> rs = new ResponseStructure<String>();
		rs.setStatusCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage(dx.getMessage());
		rs.setData("Department object with the requested Id is not available in the database.");
		
		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.NOT_FOUND);
	}

}

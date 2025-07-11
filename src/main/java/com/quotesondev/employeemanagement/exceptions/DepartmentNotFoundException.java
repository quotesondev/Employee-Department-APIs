package com.quotesondev.employeemanagement.exceptions;

public class DepartmentNotFoundException extends RuntimeException{
	
	private String message;
	
	public DepartmentNotFoundException(String message) {
		this.message = message;
	}
}

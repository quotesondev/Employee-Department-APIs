package com.quotesondev.employeemanagement.service;

import com.quotesondev.employeemanagement.model.Department;

public interface DepartmentService {

	Department addDepartment(Department department);

	Department updateDepartment(int deptId, Department updateDepartment);

	void deleteDepartment(int deptId);

}

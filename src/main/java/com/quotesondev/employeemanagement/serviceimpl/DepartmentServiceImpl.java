package com.quotesondev.employeemanagement.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quotesondev.employeemanagement.exceptions.DepartmentNotFoundException;
import com.quotesondev.employeemanagement.exceptions.EmployeeNotFoundException;
import com.quotesondev.employeemanagement.model.Department;
import com.quotesondev.employeemanagement.model.Employee;
import com.quotesondev.employeemanagement.repository.DepartmentRepository;
import com.quotesondev.employeemanagement.repository.EmployeeRepository;
import com.quotesondev.employeemanagement.service.DepartmentService;


@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentRepository deptRepo;
	@Autowired
	private EmployeeRepository empRepo;

	@Override
	public Department addDepartment(Department department) {
		if(department.getDepartmentHead() != null && department.getDepartmentHead().getEmpId() != 0) {
			int headId = department.getDepartmentHead().getEmpId();
			Optional<Employee> optional = empRepo.findById(headId);
			if(optional.isEmpty()) {
				throw new EmployeeNotFoundException("Employee not found with id " + headId);
			}else {
				Employee head = optional.get();
				department.setDepartmentHead(head);
			}
		}
		return deptRepo.save(department);
	}

	@Override
	public Department updateDepartment(int deptId, Department updateDepartment) {
		Optional<Department> optional = deptRepo.findById(deptId);
		
		if(optional.isEmpty()) {
			throw new DepartmentNotFoundException("Department not found with id " + deptId);
		}else {
			Department existingDepartment = optional.get();
			updateDepartment.setDeptId(existingDepartment.getDeptId());
			
			return deptRepo.save(updateDepartment);
		}
		
	}

	@Override
	public void deleteDepartment(int deptId) {
		Optional<Department> optional = deptRepo.findById(deptId);
		
		if(optional.isEmpty()) {
			throw new DepartmentNotFoundException("Department not found with id " + deptId) ;
		}else {
			deptRepo.deleteById(deptId);
		}
		
		
	}

}

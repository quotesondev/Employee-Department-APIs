package com.quotesondev.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quotesondev.employeemanagement.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}

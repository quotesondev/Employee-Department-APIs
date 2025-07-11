package com.quotesondev.employeemanagement.model;


import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empId;
	private String empName;
	private LocalDate dob;
	private double salary;
	private String address;
	private String role;
	private LocalDate hireDate;
	private double bonusPercentage;
	
	@ManyToOne
	@JoinColumn(name = "deptId")
	private Department department;
	
	@ManyToOne
	@JoinColumn(name="reporting_manager")
	private Employee reportingManager;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public double getBonusPercentage() {
		return bonusPercentage;
	}

	public void setBonusPercentage(double bonusPercentage) {
		this.bonusPercentage = bonusPercentage;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Employee getReportingManager() {
		return reportingManager;
	}

	public void setReportingManager(Employee reportingManager) {
		this.reportingManager = reportingManager;
	}
	
	
	

	
	
	
}

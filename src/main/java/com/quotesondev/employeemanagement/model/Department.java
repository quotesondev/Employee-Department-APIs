package com.quotesondev.employeemanagement.model;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int deptId;
	private String deptName;
	private LocalDate creationDate;
	
	@OneToOne
	@JoinColumn(name = "head_id")
	private Employee departmentHead;
	


	public int getDeptId() {
		return deptId;
	}


	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}


	public String getDeptName() {
		return deptName;
	}


	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	public LocalDate getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}


	public Employee getDepartmentHead() {
		return departmentHead;
	}


	public void setDepartmentHead(Employee departmentHead) {
		this.departmentHead = departmentHead;
	}	
	

}

package com.ldtech.entity;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
public class EmployeeAllocation {
	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
	 */

    @Id
    private String employeeId;

    private String employeeName;
    private String department;
    private String status = "active";

    @ManyToMany
    @JoinTable(name = "employees_project_master", 
               joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "employeeId"), 
               inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "project_id"))
    private List<ProjectEntity> project;     
    
    @JsonManagedReference
    @OneToMany(mappedBy = "employeeAllocation", cascade = CascadeType.ALL)
    private List<AllocationDate> allDate;

	/*
	 * public Long getId() { return id; }
	 * 
	 * public void setId(Long id) { this.id = id; }
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ProjectEntity> getProject() {
		return project;
	}

	public void setProject(List<ProjectEntity> project) {
		this.project = project;
	}

	public List<AllocationDate> getAllDate() {
		return allDate;
	}

	public void setAllDate(List<AllocationDate> allDate) {
		this.allDate = allDate;
	}
    
    
}
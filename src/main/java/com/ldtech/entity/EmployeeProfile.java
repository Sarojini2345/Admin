package com.ldtech.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
public class EmployeeProfile {
	@Id
	private String employeeId;
	private String employeeName;
	private String address;
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date DOB;
	private String department;
	// private String
	private String status = "active";
	private String gender;
	private String email;
	private String phoneNumber;
	private String designation;
	private String project = "NA";
	
	@ManyToMany
    @JoinTable(name = "employee_role_mapping",
               joinColumns = @JoinColumn(name = "employee_id"),
               inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role_master> roles;
    
    @Column(name = "role_id", insertable = false, updatable = false)
    private String roleId;
}
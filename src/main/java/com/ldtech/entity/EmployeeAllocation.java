package com.ldtech.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class EmployeeAllocation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String employeeId;
	private String employeeName;
	private String department;
	private String project;
	private String projectAllocation;
	private String client;
	private String manager;
	private String allocationStartDate;
	private String allocationEndDate;
	private String status;
}

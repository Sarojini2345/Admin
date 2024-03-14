package com.ldtech.entity;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity

public class AllocationDate {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date all_startDate;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate all_endDate;
	
//	@Column(name="eid",insertable=false, updatable=false)
//	private String eid;
	
	 @ManyToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name = "eid",referencedColumnName = "employeeId")
	 @JsonBackReference
	 private EmployeeAllocation employeeAllocation;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getAll_startDate() {
		return all_startDate;
	}

	public void setAll_startDate(Date all_startDate) {
		this.all_startDate = all_startDate;
	}

	public LocalDate getAll_endDate() {
		return all_endDate;
	}

	public void setAll_endDate(LocalDate currentDate) {
		this.all_endDate = currentDate;
	}

	public EmployeeAllocation getEmployeeAllocation() {
		return employeeAllocation;
	}

	public void setEmployeeAllocation(EmployeeAllocation employeeAllocation) {
		this.employeeAllocation = employeeAllocation;
	}

	@Override
	public String toString() {
		return "AllocationDate [all_startDate=" + all_startDate + ", all_endDate=" + all_endDate + "]";
	}

   
	
	
	
}
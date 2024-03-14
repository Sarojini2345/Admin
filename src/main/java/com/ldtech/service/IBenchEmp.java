package com.ldtech.service;

import java.util.List;

import com.ldtech.entity.EmployeeProfile;

public interface IBenchEmp {
	public EmployeeProfile findEmpById(String empId);

	public List<EmployeeProfile> findByEmpName(String empName);

	public List<EmployeeProfile> findByEmpDesignation(String designation);
}
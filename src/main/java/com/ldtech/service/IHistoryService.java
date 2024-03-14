package com.ldtech.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.ldtech.entity.EmployeeAllocation;

public interface IHistoryService {
//	 List<EmployeeAllocation> searchByEmployeeIdOrName(String searchTerm);
	List<EmployeeAllocation> searchByEmployeeId(String searchTerm);

	List<EmployeeAllocation> searchByEmployeeName(String searchTerm);
	
	//to find the projectEntity by employeeId
	List<EmployeeAllocation> findProjectsByEmployeeId(String employeeId); // New method to fetch projects by employee ID

	
}


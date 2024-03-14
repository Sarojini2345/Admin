package com.ldtech.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ldtech.entity.EmployeeAllocation;
import com.ldtech.entity.ProjectEntity;

//history repository using Employee Allocation Table 
public interface HistoryRepo extends JpaRepository<EmployeeAllocation, String> {

//	List<EmployeeAllocation> findByEmployeeIdOrEmployeeNameContainingIgnoreCase(String searchTerm, String searchTerm2);
	
	List<EmployeeAllocation> findByEmployeeIdContainingIgnoreCase(String searchTerm);

	List<EmployeeAllocation> findByEmployeeNameContainingIgnoreCase(String searchTerm);

    List<EmployeeAllocation> findProjectByEmployeeId(String employeeId); // New method to fetch projects by employee ID


	// Define custom queries if needed
}

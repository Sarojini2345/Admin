package com.ldtech.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.ldtech.entity.EmployeeAllocation;

public interface IHistoryService {
//	 List<EmployeeAllocation> searchByEmployeeIdOrName(String searchTerm);
	List<EmployeeAllocation> searchByEmployeeId(String searchTerm);

	List<EmployeeAllocation> searchByEmployeeName(String searchTerm);

	List<EmployeeAllocation> findByEmployeeNameContainingIgnoreCaseAndProject_ProjectStartdateBetweenAndProject_ProjectEnddateBetween(
			String searchTerm, Date fromDate, Date toDate);

}

package com.ldtech.service;
import java.util.List;

import com.ldtech.entity.AdminLogin;
import com.ldtech.entity.EmployeeAllocation;
import com.ldtech.entity.ProjectEntity;

public interface IDashboardService {
	List<EmployeeAllocation> searchByEmployeeId(String employeeId);
	List<EmployeeAllocation> searchByEmployeeName(String employeeName);
	List<EmployeeAllocation> searchByDepartment(String department);
	List<EmployeeAllocation> searchByClient(String client);
	List<EmployeeAllocation> searchByManager(String manager);
	List<EmployeeAllocation> searchByStatus(String status);
	AdminLogin checkCredentials(String admin_id, String password);
}
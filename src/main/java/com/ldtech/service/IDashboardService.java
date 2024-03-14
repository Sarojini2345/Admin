package com.ldtech.service;
import java.util.List;

import com.ldtech.entity.AdminLogin;
import com.ldtech.entity.EmployeeAllocation;
import com.ldtech.entity.EmployeeProfile;
import com.ldtech.entity.ProjectEntity;

public interface IDashboardService {
	List<EmployeeProfile> searchByEmployeeId(String employeeId);
	List<EmployeeProfile> searchByEmployeeName(String employeeName);
	List<EmployeeProfile> searchByDepartment(String department);
	List<EmployeeAllocation> searchByClient(String client);
	List<EmployeeAllocation> searchByManager(String manager);
	List<EmployeeAllocation> searchByStatus(String status);
	AdminLogin checkCredentials(String admin_id, String password);
	List<EmployeeProfile> searchAllEmployee();
	List<EmployeeAllocation> searchAllEmployees();
	List<EmployeeAllocation> searchByProjectType(String project_type);
	List<EmployeeAllocation> searchAllByDepartment(String str);
	List<EmployeeAllocation> searchByProjectName(String str);
	List<EmployeeAllocation> searchByEmployeeIddetails(String str);
	List<EmployeeAllocation> searchByEmployeeNamedetails(String str);
}


package com.ldtech.service;

import java.util.List;

import com.ldtech.entity.EmployeeAllocation;
import com.ldtech.entity.ProjectEntity;

public interface IEmployeeAllocationPage {
	public EmployeeAllocation findByEmployeeId(String employeeId);
	public List<ProjectEntity> findProjectDetails();
	public ProjectEntity findProjectName(String projectName);
}

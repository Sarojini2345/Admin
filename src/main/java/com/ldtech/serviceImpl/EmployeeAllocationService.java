package com.ldtech.serviceImpl;

import com.ldtech.entity.EmployeeAllocation;
import com.ldtech.entity.EmployeeProfile;
import com.ldtech.entity.ProjectEntity;
import com.ldtech.service.IEmployeeAllocationPage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldtech.dao.DashboardRepository;
import com.ldtech.dao.EmployeeProfileRepo;
import com.ldtech.dao.ProjectRepo;

@Service
public class EmployeeAllocationService implements IEmployeeAllocationPage {
	
	@Autowired
	EmployeeProfileRepo empDao;
	
	@Autowired
	ProjectRepo repo1;

	

	@Override
	public List<ProjectEntity> findProjectDetails() {
		// TODO Auto-generated method stub
		return repo1.findAll();
	}

	@Override
	public ProjectEntity findProjectName(String projectName) {
		// TODO Auto-generated method stub
		return repo1.findByProjectName(projectName);
	}

	@Override
	public EmployeeProfile findByEmployeeId(String employeeId) {
		// TODO Auto-generated method stub
		return empDao.findByEmployeeId(employeeId);
	}
}

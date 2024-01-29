package com.ldtech.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.ldtech.dao.DashboardRepository;
import com.ldtech.dao.EmployeeProfileRepo;
import com.ldtech.entity.EmployeeProfile;
import com.ldtech.service.IHomePage;

@Service
public class HomePageClass implements IHomePage {
	
	@Autowired
	EmployeeProfileRepo repo;

	@Override
	public Long CountTotalEmployee() {
		// TODO Auto-generated method stub
		return repo.count();
	}

	@Override
	public Long countAllocatedEmployee() {
		// TODO Auto-generated method stub
		return repo.findAllocatedEmployee();
	}

	@Override
	public Long countUnAllocatedEmployee() {
		// TODO Auto-generated method stub
		return repo.findUnAllocatedEmployee();
     }	
	
	@Override
	public String RegisterEmployee(EmployeeProfile emp) {
		 String empid=repo.save(emp).getEmployeeId();
		return "Employee registered with Id "+empid;
	} 
}

package com.ldtech.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldtech.dao.DashboardRepository;
import com.ldtech.service.IHomePage;

@Service
public class HomePageClass implements IHomePage {
	
	@Autowired
	DashboardRepository repo;

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
}

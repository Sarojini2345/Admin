package com.ldtech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldtech.dao.DashboardRepository;
import com.ldtech.dao.LoginRepo;
import com.ldtech.entity.AdminLogin;
import com.ldtech.entity.EmployeeAllocation;

@Service
public class DashboardService implements IDashboardService{
	
	@Autowired
	DashboardRepository repo;
	
	@Autowired
	LoginRepo repo2;

	@Override
	public List<EmployeeAllocation> searchByEmployeeId(String employeeId) {
		// TODO Auto-generated method stub
		
		return repo.findByEmployeeIdLike(employeeId);
	}

	@Override
	public List<EmployeeAllocation> searchByEmployeeName(String employeeName) {
		// TODO Auto-generated method stub
		
		return repo.findAllByEmployeeName(employeeName);
	}

	@Override
	public List<EmployeeAllocation> searchByDepartment(String department) {
		// TODO Auto-generated method stub
		
		return repo.findAllByDepartment(department);
	}

	@Override
	public List<EmployeeAllocation> searchByClient(String client) {
		// TODO Auto-generated method stub
		
		return repo.findAllByClient(client);
	}

	@Override
	public List<EmployeeAllocation> searchByManager(String manager) {
		// TODO Auto-generated method stub
		
		return repo.findAllByManager(manager);
	}

	@Override
	public List<EmployeeAllocation> searchByStatus(String status) {
		// TODO Auto-generated method stub
		
		return repo.findAllByStatus(status);
	}

	@Override
	public AdminLogin checkCredentials(String admin_id, String password) {
		// TODO Auto-generated method stub
		return repo2.validateForm(admin_id, password);
	}
   
}

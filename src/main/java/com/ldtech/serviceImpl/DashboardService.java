package com.ldtech.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldtech.dao.DashboardRepository;
import com.ldtech.dao.EmployeeProfileRepo;
import com.ldtech.dao.LoginRepo;
import com.ldtech.dao.ProjectRepo;
import com.ldtech.entity.AdminLogin;
import com.ldtech.entity.EmployeeAllocation;
import com.ldtech.entity.EmployeeProfile;
import com.ldtech.entity.ProjectEntity;
import com.ldtech.service.IDashboardService;

@Service
public class DashboardService implements IDashboardService{
	
	@Autowired
	DashboardRepository repo;
	
	@Autowired
	LoginRepo repo2;
	
	@Autowired
	ProjectRepo repo3;
	
	@Autowired
	EmployeeProfileRepo repo1;

	@Override
	public List<EmployeeProfile> searchByEmployeeId(String employeeId) {
		// TODO Auto-generated method stub
		
//		List<EmployeeProfile> pf= repo1.findByEmployeeIdLike(employeeId);
//		for(EmployeeProfile epf:pf) {
//			String str=epf.getProject();
//			if(str.equals("Allocated")) {
//				return repo.findByEmployeeId(employeeId);
//				//return null ;
//			}
//			else {
//				return repo1.findByEmployeeIdLike(employeeId);
//			}
//		}
		return repo1.findByEmployeeIdLike(employeeId);
	}
	
	public List<EmployeeAllocation> searchByEmployeeIddetails(String employeeId) {
		return repo.findByEmployeeIdLike(employeeId);
	}
	
	public List<EmployeeAllocation> searchByEmployeeNamedetails(String employeeName) {
		return repo.findAllByEmployeeName(employeeName);
	}
	
	@Override
	public List<EmployeeProfile> searchByEmployeeName(String employeeName) {
		// TODO Auto-generated method stub
		
		return repo1.findAllByEmployeeName(employeeName);
	}
	
	@Override
	public List<EmployeeProfile> searchByDepartment(String department) {
		// TODO Auto-generated method stub
		
		return repo1.findAllByDepartment(department);
	}
//
	public List<EmployeeAllocation> searchAllByDepartment(String department) {
		// TODO Auto-generated method stub
		
		return repo.findAllByDepartment(department);
	}
	@Override
	public List<EmployeeAllocation> searchByClient(String client) {
		// TODO Auto-generated method stub
		
		List<EmployeeAllocation> list= repo.findAllByProjectClient(client);
		System.out.println(list);
		for(EmployeeAllocation emp:list) {
			ProjectEntity project=repo3.findById(emp.getProject().getProject_id()).get();
			emp.setProject(project);
		}
		return list;
	}
//
//	
	@Override
	public List<EmployeeAllocation> searchByManager(String manager) {
		// TODO Auto-generated method stub
		
		List<EmployeeAllocation> list=repo.findAllByProjectManager(manager);
		System.out.println(list);
		for(EmployeeAllocation emp:list) {
			ProjectEntity project=repo3.findById(emp.getProject().getProject_id()).get();
			emp.setProject(project);
		}
		return list;
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
	
	public List<EmployeeAllocation> searchByProjectName(String projectName){
		return repo.findAllByProjectProjectName(projectName);
	}
}

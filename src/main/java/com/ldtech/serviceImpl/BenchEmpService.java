package com.ldtech.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldtech.dao.EmployeeProfileRepo;
import com.ldtech.entity.EmployeeProfile;
import com.ldtech.service.IBenchEmp;

@Service
public class BenchEmpService implements IBenchEmp {
	
	@Autowired
	EmployeeProfileRepo repo;

	@Override
	public EmployeeProfile findEmpById(String empId) {
		// TODO Auto-generated method stub
		EmployeeProfile emp=repo.findByEmployeeId(empId);
		if(emp.getProject().equals("NA")) {
			return emp;
		}
		else {
			return null;
		}
	}

	@Override
	public List<EmployeeProfile> findByEmpName(String empName) {
		List<EmployeeProfile> list=null;
		List<EmployeeProfile> emp=repo.findAllByEmployeeName(empName);
		for(EmployeeProfile emp1 : emp) {
           if(emp1.getProject().equals("NA")) {
		      list.add(emp1);
		    }
	}
		return list;
	}

	@Override
	public List<EmployeeProfile> findByEmpDesignation(String designation) {
		// TODO Auto-generated method stub
		List<EmployeeProfile> list=null;
		List<EmployeeProfile> emp=repo.findAllByDesignation(designation);
		for(EmployeeProfile emp1 : emp) {
           if(emp1.getProject().equals("NA")) {
		      list.add(emp1);
		    }
	}
		return list;		
	}	
}

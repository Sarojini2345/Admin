package com.ldtech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ldtech.entity.EmployeeProfile;
import com.ldtech.service.IHomePage;

@RestController
public class HomePageController {
	
	@Autowired
	IHomePage service;

	@GetMapping("/countEmployee")
	public Long getTotalEmployee() {
		return service.CountTotalEmployee();
	} 
	
	@GetMapping("/countAllocated")
	public Long getAllocatedEmployee() {
		return service.countAllocatedEmployee();	
	}
	@GetMapping("/countUnAllocated")
	public Long getUnAllocatedEmployee() {
		return service.countUnAllocatedEmployee();	
	}
	
	@PostMapping("/addEmp")
	public String AddEmployee(@RequestBody EmployeeProfile emp) {
		return service.RegisterEmployee(emp);
    }
}

package com.ldtech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ldtech.entity.AdminLogin;
import com.ldtech.entity.EmployeeAllocation;
import com.ldtech.service.DashboardService;

@RestController
public class DashboardController {
	
	@Autowired
	DashboardService service;
	
	@PostMapping("/check")
	public String checkForm(@RequestParam String admin_id,@RequestParam String password) {
		AdminLogin al=service.checkCredentials(admin_id,password);
		System.out.println(admin_id);
		System.out.println(password);
		System.out.println(al);
		if(al!=null) {
		return "Login suceessfull !";
		}
		else {
			return "Wrong credentials !";
		}
	}
	
	@GetMapping("/searchAllById/{empId}")
	public ResponseEntity<List<EmployeeAllocation>> searchById(@PathVariable("empId") String str){
	     List<EmployeeAllocation> loc=service.searchByEmployeeId(str);
		return ResponseEntity.ok(loc);
	}																	
	
	@GetMapping("/searchAllByName/{empName}")
	public ResponseEntity<List<EmployeeAllocation>> searchByName(@PathVariable("empName") String str){
	     List<EmployeeAllocation> loc=service.searchByEmployeeName(str);
		return ResponseEntity.ok(loc);
	}
	
	@GetMapping("/searchAllByManager/{manager}")
	public ResponseEntity<List<EmployeeAllocation>> searchByManagerName(@PathVariable("manager") String str){
	     List<EmployeeAllocation> loc=service.searchByManager(str);
		return ResponseEntity.ok(loc);
	}
	
	@GetMapping("/searchAllByDept/{dept}")
	public ResponseEntity<List<EmployeeAllocation>> searchByDepartment(@PathVariable("dept") String str){
	     List<EmployeeAllocation> loc=service.searchByDepartment(str);
		return ResponseEntity.ok(loc);
	}
	
	@GetMapping("/searchAllByClient/{client}")
	public ResponseEntity<List<EmployeeAllocation>> searchByClient(String str){
	     List<EmployeeAllocation> loc=service.searchByClient(str);
		return ResponseEntity.ok(loc);
	}
	
	@GetMapping("/searchAllByStatus/{status}")
	public ResponseEntity<List<EmployeeAllocation>> searchByStatusid(String str){
	     List<EmployeeAllocation> loc=service.searchByStatus(str);
		return ResponseEntity.ok(loc);
	}
}
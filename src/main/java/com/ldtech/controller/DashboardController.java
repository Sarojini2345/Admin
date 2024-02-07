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
import com.ldtech.entity.EmployeeProfile;
import com.ldtech.entity.ProjectEntity;
import com.ldtech.service.IDashboardService;
import com.ldtech.serviceImpl.DashboardService;

@RestController
public class DashboardController {
	
	@Autowired
	IDashboardService service;
	
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
	public ResponseEntity<List<EmployeeProfile>> searchById(@PathVariable("empId") String str){
	     List<EmployeeProfile> loc=service.searchByEmployeeId(str);
		return ResponseEntity.ok(loc);
	}																	
	@GetMapping("/getAllocatedEmployee/{empId}")
	public ResponseEntity<List<EmployeeAllocation>> searchByEmpId(@PathVariable("empId") String str){
	     List<EmployeeAllocation> loc=service.searchByEmployeeIddetails(str);
		return ResponseEntity.ok(loc);
	}		
	@GetMapping("/searchAllByName/{empName}")
	public ResponseEntity<List<EmployeeProfile>> searchByName(@PathVariable("empName") String str){
	     List<EmployeeProfile> loc=service.searchByEmployeeName(str);
		return ResponseEntity.ok(loc);
	}
	
	@GetMapping("/searchByNames/{empName}")
	public ResponseEntity<List<EmployeeAllocation>> searchAllByName(@PathVariable("empName") String str){
	     List<EmployeeAllocation> loc=service.searchByEmployeeNamedetails(str);
		return ResponseEntity.ok(loc);
	}
	
	@GetMapping("/searchAllByManager/{manager}")
	public ResponseEntity<List<EmployeeAllocation>> searchByManagerName(@PathVariable("manager") String str){
	     List<EmployeeAllocation> loc=service.searchByManager(str);
		return ResponseEntity.ok(loc);
	}
	@GetMapping("/searchAllByDept/{dept}")
	public ResponseEntity<List<EmployeeProfile>> searchByDepartment(@PathVariable("dept") String str){
	     List<EmployeeProfile> loc=service.searchByDepartment(str);
		return ResponseEntity.ok(loc);
	}
	@GetMapping("/searchAllByDepts/{dept}")
	public ResponseEntity<List<EmployeeAllocation>> searchAllByDepartment(@PathVariable("dept") String str){
	     List<EmployeeAllocation> loc=service.searchAllByDepartment(str);
		return ResponseEntity.ok(loc);
	}
	
	
	@GetMapping("/searchAllByClient/{client}")
	public ResponseEntity<List<EmployeeAllocation>> searchByClient(@PathVariable("client") String str){
	     List<EmployeeAllocation> loc=service.searchByClient(str);
		return ResponseEntity.ok(loc);
	}
	
	@GetMapping("/searchAllByStatus/{status}")
	public ResponseEntity<List<EmployeeAllocation>> searchByStatusid(@PathVariable("status")String str){
	     List<EmployeeAllocation> loc=service.searchByStatus(str);
		return ResponseEntity.ok(loc);
	}
	
	@GetMapping("/searchAllByProject/{project}")
	public ResponseEntity<List<EmployeeAllocation>> searchAllByProjectName(@PathVariable("project") String str){
	     List<EmployeeAllocation> loc=service.searchByProjectName(str);
		return ResponseEntity.ok(loc);
    }
	
	@GetMapping("/searchAll")
	public ResponseEntity<List<EmployeeAllocation>> searchAll(){
		List<EmployeeAllocation> list=service.searchAllEmployees();
		return ResponseEntity.ok(list);		
	}
	
	@GetMapping("/searchAllEmp")
	public ResponseEntity<List<EmployeeProfile>> searchAllEmp(){
		List<EmployeeProfile> list1=service.searchAllEmployee();
		return ResponseEntity.ok(list1);		
	}
	
	@GetMapping("/findByProjectType/{projectType}")
	public ResponseEntity<List<EmployeeAllocation>> findByProjectType(@PathVariable String projectType){
		List<EmployeeAllocation> pj=service.searchByProjectType(projectType);
		return ResponseEntity.ok(pj);
	} 
}
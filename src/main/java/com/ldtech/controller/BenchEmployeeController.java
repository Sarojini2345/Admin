package com.ldtech.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ldtech.dao.DashboardRepository;
import com.ldtech.entity.EmployeeAllocation;
import com.ldtech.entity.EmployeeProfile;

import com.ldtech.service.IBenchEmp;

@RestController
public class BenchEmployeeController {

	@Autowired
	IBenchEmp service;

	@Autowired
	DashboardRepository repo;

	@GetMapping("/benchEmployee/{id}")
	public ResponseEntity<Map<String, Object>> searchByEmployeeId(@PathVariable("id") String employeeId) {
		Map<String, Object> map = repo.benchEmpById(employeeId);
		return ResponseEntity.ok(map);
	}

	@GetMapping("/benchEmployee/name/{employeeName}")
	public ResponseEntity<List<Map<String, Object>>> searchByEmployeeName(@PathVariable String employeeName) {
		List<Map<String, Object>> allByEmployeeName = repo.benchEmpByName(employeeName);
		return ResponseEntity.ok(allByEmployeeName);
	}

	 @GetMapping("/benchEmployees/{dept}") 
	 public ResponseEntity<List<Map<String, Object>>> searchByEmployeeDept(@PathVariable("dept") String department) { //
		 List<Map<String, Object>> allByEmployeeDept=repo.benchEmpByDept(department);
		 
	   return ResponseEntity.ok(allByEmployeeDept); 
	  }
	 

	@GetMapping("/getbenchEmp")
	public ResponseEntity<List<Map<String, Object>>> benchEmp() {
		List<Map<String, Object>> list = repo.benchEmp();
		// repo.benchEmp().forEach(e->System.out.println(e));
		System.out.println();
		return ResponseEntity.ok(list);
	}
}
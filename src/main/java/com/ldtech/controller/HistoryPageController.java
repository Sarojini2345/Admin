package com.ldtech.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ldtech.dao.ProjectRepo;
import com.ldtech.entity.EmployeeAllocation;
import com.ldtech.service.IHistoryService;

@RestController
@RequestMapping("/history")
public class HistoryPageController {

	@Autowired
	IHistoryService service;
	
	@Autowired
	ProjectRepo repo;

	@GetMapping("/search/id/{searchTerm}")
	public List<EmployeeAllocation> searchEmployeeById(@PathVariable String searchTerm) {
	    List<EmployeeAllocation> employees = service.searchByEmployeeId(searchTerm);
//	    for (EmployeeAllocation employee : employees) {
//	        // Fetch projects for each employee if they are associated with multiple projects
//	        if (employee.getProject().size() > 1) {
//	        	List<ProjectEntity> plist = employee.getProject();
//	            System.out.println("plist "+plist);
//	        }
//	    }
	    return employees;
	}

	@GetMapping("/search/name/{searchTerm}")
	public List<EmployeeAllocation> searchEmployeeByName(@PathVariable String searchTerm) {
	    List<EmployeeAllocation> employees = service.searchByEmployeeName(searchTerm);
	   
	    return employees;
	}
	
	@GetMapping("/joker/{searchTerm}")
	public List<EmployeeAllocation> searchProjectbyid(@PathVariable String searchTerm){
		return service.findProjectsByEmployeeId(searchTerm);
	}

}

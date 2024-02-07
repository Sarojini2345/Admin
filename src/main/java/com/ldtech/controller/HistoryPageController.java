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

import com.ldtech.entity.EmployeeAllocation;
import com.ldtech.service.IHistoryService;

@RestController
@RequestMapping("/history")
public class HistoryPageController {

	@Autowired
	IHistoryService service;

	@GetMapping("/search/id/{searchTerm}")
	public List<EmployeeAllocation> searchEmployeeById(@PathVariable String searchTerm) {
		return service.searchByEmployeeId(searchTerm);
	}
	
	@GetMapping("/search/name/{searchTerm}")
	public List<EmployeeAllocation> searchEmployeeByName(@PathVariable String searchTerm) {
		System.out.println("HistoryPageController.searchEmployeeByName()"+ service.searchByEmployeeName(searchTerm));
		return service.searchByEmployeeName(searchTerm);
	}
	
	


	@GetMapping("/search/name/nameDate/{searchTerm}")
    public ResponseEntity<List<EmployeeAllocation>> searchByNameAndDates(
    		
            @PathVariable String searchTerm,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date toDate) {
		
		System.out.println("HistoryPageController.searchByNameAndDates()");
        List<EmployeeAllocation> result = service.findByEmployeeNameContainingIgnoreCaseAndProject_ProjectStartdateBetweenAndProject_ProjectEnddateBetween(searchTerm, fromDate, toDate);
        System.out.println(result);
        return ResponseEntity.ok(result);
        
    }
	


}

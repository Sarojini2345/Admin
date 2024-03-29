package com.ldtech.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ldtech.dao.DashboardRepository;
import com.ldtech.dao.EmployeeProfileRepo;
import com.ldtech.dao.ProjectRepo;
import com.ldtech.entity.EmployeeAllocation;
import com.ldtech.entity.EmployeeProfile;
import com.ldtech.entity.ProjectEntity;
import com.ldtech.service.IEmployeeAllocationPage;

@RestController
public class EmployeeAllocationController {
	
	@Autowired
	ProjectRepo repo;
	
	@Autowired
	DashboardRepository repo1;
	
	@Autowired
	EmployeeProfileRepo repository;
	
	@Autowired
	 IEmployeeAllocationPage service;

	  @GetMapping("/hello/{employeeId}")
	    public ResponseEntity<EmployeeProfile> getEmployeeById(@PathVariable String employeeId) {
	        System.out.println("EmployeeAllocationController.getEmployeeById()");
	        EmployeeProfile employee = this.service.findByEmployeeId(employeeId);
	        if (employee != null) {
	            return ResponseEntity.ok(employee);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	  @PostMapping(value="/insertData", consumes = "application/json")
	    public String createEmployee(@RequestBody EmployeeAllocation employee){ 
		  
		  EmployeeAllocation emp=repo1.findByEmployeeId(employee.getEmployeeId());
		  EmployeeProfile empl=repository.findByEmployeeId(employee.getEmployeeId());
		   System.out.println(emp);
		  
		   ProjectEntity project=employee.getProject();
		  
		  ProjectEntity byProjectName = repo.findByProjectName(project.getProjectName());
//		  ProjectEntity projectEntity = byId.get();
		  
		  
//		  ProjectEntity project1=repo.save(project);
		  if(emp==null) {
		  employee.setProject(byProjectName);
		  EmployeeAllocation emp1=repo1.save(employee); 
		  empl.setProject("Allocated");
		  repository.save(empl);
		  return "Record with employeeId "+emp1.getEmployeeId()+" successfully saved";
		  } 
		   else {
		        emp.setProject(byProjectName);
		        EmployeeAllocation emp2=repo1.save(emp);
		        return "Record with employeeId "+emp2.getEmployeeId()+" successfully updated";
		   }
		  
	    }  
	  @GetMapping("/getDropdown")
	  public ResponseEntity<List<ProjectEntity>> findEmployeeDropDown(){
		     List<ProjectEntity> loc=service.findProjectDetails();
		     System.out.println(loc);
			return ResponseEntity.ok(loc);
		    
		}
	  
	  @GetMapping("/api/{projname}")
	  public ProjectEntity findByProjectName(@PathVariable("projname") String projectName) {
		return service.findProjectName(projectName);		  
	  }
	  
}
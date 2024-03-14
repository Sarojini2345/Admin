package com.ldtech.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
import com.ldtech.dao.EmployeeAllocationRepo;
import com.ldtech.dao.EmployeeProfileRepo;
import com.ldtech.dao.ProjectRepo;
import com.ldtech.entity.AllocationDate;
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
	EmployeeAllocationRepo reposit;

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

	@GetMapping("/allEmployee")
	public ResponseEntity<List<EmployeeAllocation>> getAlldata() {
		return ResponseEntity.ok(repo1.findAll());
	}

	@PostMapping(value = "/insertData/{projectName}", consumes = "application/json")
	public String createEmployee(@RequestBody EmployeeAllocation employee, @PathVariable String projectName) {
		System.out.println("Project name is " + projectName);
		System.out.println(employee);
		
		// Find EmployeeAllocation by employeeId
		EmployeeAllocation emp = repo1.findByEmployeeId(employee.getEmployeeId());
		
		// Find EmployeeProfile by employeeId
		EmployeeProfile empl = repository.findByEmployeeId(employee.getEmployeeId());
				
//	    System.out.println(date);
		// Check if project name is not null or empty
		if (projectName == null || projectName.isEmpty()) {
			// Handle case where no project name is provided
			return "No project name provided";
		}

		// Get the project based on the project name
		ProjectEntity project = repo.findByProjectName(projectName);
		System.out.println(project);
		/*
		 * if (project == null ||project.getSTATUS().equalsIgnoreCase("inactive")) { //
		 * Handle case where project with the given name is not found return
		 * "Project with the name " + projectName + " not found"; }
		 */
		if(emp!=null && emp.getStatus().equalsIgnoreCase("active")) {
			return "This employee is already associated with a project do you want to deallocate ?";
		}
		
		else if(emp!=null && emp.getStatus().equalsIgnoreCase("inactive")) {
			
			emp.setStatus("active");
		}

		else {
			// If employee does not exist, create a new EmployeeAllocation object
			emp = new EmployeeAllocation();
			emp.setEmployeeId(employee.getEmployeeId());
			emp.setEmployeeName(employee.getEmployeeName());
			emp.setDepartment(employee.getDepartment());
			emp.setStatus(employee.getStatus());
//			emp.setAllDate(date);
		}
		List<AllocationDate> date=new ArrayList<>();
	    for(AllocationDate d:employee.getAllDate()) {
	    	 AllocationDate date1 = new AllocationDate();
             date1.setAll_endDate(d.getAll_endDate());
             date1.setAll_startDate(d.getAll_startDate());
             date1.setEmployeeAllocation(emp);
             
             //date1.setEmployeeAllocation(employee);
	    	//AllocationDate d1=reposit.save(date1);
             date.add(date1);
	    }
		
//		if(emp!=null && emp.getStatus().equalsIgnoreCase("active")) {
//			return "This employee is already associated with a project";
//		}
		
		// Check if the employee is already associated with the project
		List<ProjectEntity> existingProjects = emp.getProject();
		if (existingProjects == null || !existingProjects.contains(project)&& !employee.getStatus().contains("inactive")) {
			// If the employee is not already associated with the project, add the
			
			// association
			if (existingProjects == null) {
				existingProjects = new ArrayList<>();
			}
			existingProjects.add(project);
			emp.setProject(existingProjects);
			
			emp.setAllDate(date);

			// Save the updated employee object
			EmployeeAllocation savedEmployee = repo1.save(emp);

			// Update the EmployeeProfile status
			empl.setProject("Allocated");
			
			repository.save(empl);
			System.out.println("EmployeeAllocationController.createEmployee()"+empl);

			return "Record with employeeId " + savedEmployee.getEmployeeId() + " successfully saved/updated";
		} else {
			System.out.println(employee.getStatus()+" emp "+emp.getStatus());
			// If the employee is already associated with the project, return a message
			// indicating so
			return "Employee is already associated with the project";
		}
	}
	
	@PostMapping("/deallocate/{employeeId}")
	public String employeeDeallocation(@PathVariable String employeeId) {
		EmployeeAllocation emp=repo1.findByEmployeeId(employeeId);
		EmployeeProfile prof=repository.findByEmployeeId(employeeId);
		LocalDate currentDate = LocalDate.now();
      //  System.out.println("Current Date: " + currentDate);
		 System.out.println(currentDate);		
			emp.setStatus("inactive");
			prof.setProject("NA");
		    List<AllocationDate> date=emp.getAllDate();
		    int lastIndex = date.size() - 1;
		    AllocationDate lastObject = date.get(lastIndex);
		    System.out.println(lastObject);
		    lastObject.setAll_endDate(currentDate);
		    System.out.println(date); 
		    emp.setAllDate(date);
		    repo1.save(emp);
		    
		return "employee deallocated successfully";		
	}
	
	

	@GetMapping("/getDropdown")
	public ResponseEntity<List<ProjectEntity>> findEmployeeDropDown() {
		List<ProjectEntity> loc = service.findProjectDetails();
		System.out.println(loc);
		return ResponseEntity.ok(loc);
	}

	@GetMapping("/api/{projname}")
	public ProjectEntity findByProjectName(@PathVariable("projname") String projectName) {
		return service.findProjectName(projectName);
	}
}
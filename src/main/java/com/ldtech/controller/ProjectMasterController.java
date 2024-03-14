package com.ldtech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ldtech.dao.DashboardRepository;
import com.ldtech.dao.EmployeeProfileRepo;
import com.ldtech.dao.ProjectRepo;
import com.ldtech.entity.EmployeeAllocation;
import com.ldtech.entity.EmployeeProfile;
import com.ldtech.entity.ProjectEntity;
import com.ldtech.service.IProjectMasterService;

@RestController
public class ProjectMasterController {

	@Autowired
	IProjectMasterService service;

	@Autowired
	ProjectRepo repo;

	@Autowired
	DashboardRepository repo1;

	@Autowired
	EmployeeProfileRepo repo2;

	@GetMapping("/searchById/{id}")
	public ResponseEntity<ProjectEntity> searchByProjectId(@PathVariable("id") Integer projectId) {
		ProjectEntity pj = service.searchByProjectID(projectId);
		return ResponseEntity.ok(pj);
	}

	@GetMapping("/searchByPname/{pname}")
	public ResponseEntity<ProjectEntity> searchByProjectName(@PathVariable("pname") String projectName) {
		ProjectEntity pj = service.searchByProjectTitle(projectName);
		return ResponseEntity.ok(pj);
	}

	@GetMapping("/searchByPclient/{client}")
	public ResponseEntity<List<ProjectEntity>> searchByProjectClient(@PathVariable("client") String client) {
		List<ProjectEntity> pj = service.searchByClient(client);
		return ResponseEntity.ok(pj);
	}

	@GetMapping("/searchByPtype/{ptype}")
	public ResponseEntity<List<ProjectEntity>> searchByProjectType(@PathVariable("ptype") String project_type){
		List<ProjectEntity> pj=repo.findByProjectType(project_type);
		return ResponseEntity.ok(pj);	
	}

	@PostMapping("/addProject")
	public String AddProject(@RequestBody ProjectEntity ent) {
		System.out.println("hi");
		ProjectEntity pj1=repo.findByProjectName(ent.getProjectName());
		System.out.println("hello");
		if(pj1==null) {
		ProjectEntity pj = repo.save(ent);
		return "Project added with " + pj.getProject_id();
		}
		else {
			return "Project With this name already exist";
		}
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateProject(@RequestBody ProjectEntity projectEntity) {
		try {
			// Call your service method to update the project
			service.updateProject(projectEntity);
			return new ResponseEntity<>("Project updated successfully", HttpStatus.OK);
		} catch (Exception e) {
			// Handle exceptions and return an appropriate response
			return new ResponseEntity<>("Error updating project: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/deleteProject/{pid}")
	public String deleteProject(@PathVariable("pid") Integer Id) {

		ProjectEntity project = repo.findById(Id).get();
		if (project.getSTATUS().equalsIgnoreCase("active")) {
			project.setSTATUS("inactive");
			repo.save(project);
			List<String> list = repo1.findEmployeeIdsByProjectId(Id);
			
			for (String emp : list) {
				List<EmployeeAllocation> allocationList = repo1.findAllByEmployeeId(emp);
				for (EmployeeAllocation all : allocationList) {
					all.setStatus("inactive");
					repo1.save(all);
					EmployeeProfile employee = repo2.findByEmployeeId(all.getEmployeeId());
					employee.setProject("NA");
					System.out.println("employee id deleted " + employee);
					repo2.save(employee);
				}
			}
			return "Project Deleted successfully";
		} else {
			return "Project doesnot exist";
		}

	}

	@GetMapping("/check/{pid}")
	public List<String> checkProject(@PathVariable("pid") Integer Id){
		return repo1.findEmployeeIdsByProjectId(Id);
	}
}
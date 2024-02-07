package com.ldtech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ldtech.entity.ProjectEntity;
import com.ldtech.service.IProjectMasterService;
import com.ldtech.dao.ProjectRepo;

@RestController
public class ProjectMasterController {
   
	@Autowired
	IProjectMasterService service;
	
	@Autowired
	ProjectRepo repo;
	
	@GetMapping("/searchById/{id}")
	public ResponseEntity<ProjectEntity> searchByProjectId(@PathVariable("id") Integer projectId){
		ProjectEntity pj=service.searchByProjectID(projectId);
		return ResponseEntity.ok(pj);
	}
	
	@GetMapping("/searchByPname/{pname}")
	public ResponseEntity<ProjectEntity> searchByProjectName(@PathVariable("pname") String projectName){
		ProjectEntity pj=service.searchByProjectTitle(projectName);
		return ResponseEntity.ok(pj);
	}
	
	@GetMapping("/searchByPclient/{client}")
	public ResponseEntity<List<ProjectEntity>> searchByProjectClient(@PathVariable("client") String client){
		List<ProjectEntity> pj=service.searchByClient(client);
		return ResponseEntity.ok(pj);
	}
	
//	@GetMapping("/searchByPtype/{ptype}")
//	public ResponseEntity<List<ProjectEntity>> searchByProjectType(@PathVariable("ptype") String project_type){
//		List<ProjectEntity> pj=service.searchByProjecttypeProject(project_type);
//		return ResponseEntity.ok(pj);
//	}
	
	@PostMapping("/addProject")
	public String AddProject(@RequestBody ProjectEntity ent) {
		ProjectEntity pj=repo.save(ent);
		return "Project added with "+pj.getProject_id();
		
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
	
	
}
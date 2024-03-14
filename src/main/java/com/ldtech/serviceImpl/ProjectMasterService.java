package com.ldtech.serviceImpl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ldtech.dao.ProjectRepo;
import com.ldtech.entity.ProjectEntity;
import com.ldtech.service.IProjectMasterService;

@Service
public class ProjectMasterService implements IProjectMasterService {
	
	@Autowired
	ProjectRepo repo;

	@Override
	public ProjectEntity searchByProjectID(Integer projectId) {
		// TODO Auto-generated method stub
		Optional<ProjectEntity> pj= repo.findById(projectId);
		if (pj.isPresent()) {
		  return pj.get();
		    // Do something with the value
		} else {
		    // Handle the case when the optional is empty
		  return null;
		}
	}

	@Override
	public ProjectEntity searchByProjectTitle(String projectName) {
		// TODO Auto-generated method stub
		return repo.findByProjectName(projectName);
	}

	@Override
	public List<ProjectEntity> searchByClient(String client) {
		// TODO Auto-generated method stub
		return repo.findByClient(client);
	}

//	@Override
//	public List<ProjectEntity> searchByProjecttypeProject(String project_type) {
//		return repo.findByProjectType(project_type);
//		// TODO Auto-generated method stub
//	 
//	}

	 public ProjectEntity updateProject(ProjectEntity projectEntity) {
		 System.out.println(projectEntity);
	        // Implement the logic to update the project in the repository
	        // You may need to convert the ProjectRequest to your entity class

	        // Example:
		// ProjectEntity eproj=repo.findByProjectName(projectEntity.getProjectName());
		 ProjectEntity projectToUpdate;
		 try {
		     projectToUpdate = repo.findById(projectEntity.getProject_id()).get();
		 } catch (NoSuchElementException e) {
		     throw new RuntimeException("Project not found");
		 }


	        projectToUpdate.setProjectName(projectEntity.getProjectName());
	        projectToUpdate.setProjectType(projectEntity.getProjectType());
	        projectToUpdate.setProjectDescription(projectEntity.getProjectDescription());
	        projectToUpdate.setClient(projectEntity.getClient());
	        projectToUpdate.setProject_startdate(projectEntity.getProject_startdate());
	        projectToUpdate.setProject_enddate(projectEntity.getProject_enddate());
	        projectToUpdate.setManager(projectEntity.getManager());

	        return repo.save(projectToUpdate);
	    }


	
}
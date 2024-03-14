package com.ldtech.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ldtech.entity.ProjectEntity;

public interface IProjectMasterService {
	ProjectEntity searchByProjectID(Integer projectId); 
	ProjectEntity searchByProjectTitle(String projectName);
	List<ProjectEntity> searchByClient(String client);
	//List<ProjectEntity> searchByProjecttypeProject(String project_type);
	ProjectEntity updateProject(ProjectEntity projectEntity);
	//List<ProjectEntity> searchByProjectType(String project_type);
}

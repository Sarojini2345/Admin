package com.ldtech.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ldtech.entity.EmployeeAllocation;
import com.ldtech.entity.ProjectEntity;


public interface ProjectRepo extends JpaRepository<ProjectEntity,Integer>{
	//List<ProjectEntity> findAllByManager(String manager);
	ProjectEntity findByProjectName(String projectName);
}

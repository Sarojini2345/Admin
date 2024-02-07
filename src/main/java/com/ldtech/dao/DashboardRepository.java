package com.ldtech.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ldtech.entity.EmployeeAllocation;
import com.ldtech.entity.EmployeeProfile;

import java.util.Date;
import java.util.List;

public interface DashboardRepository extends JpaRepository<EmployeeAllocation, Long>{
	@Query("SELECT e FROM EmployeeAllocation e WHERE e.employeeId LIKE :idPattern")
    List<EmployeeAllocation> findByEmployeeIdLike(@Param("idPattern") String idPattern);
	List<EmployeeAllocation> findAllByEmployeeId(String employeeId);
	List<EmployeeAllocation> findAllByEmployeeName(String employeeName);
	List<EmployeeAllocation> findAllByDepartment(String department);
	List<EmployeeAllocation> findAllByProjectProjectName(String project);
	List<EmployeeAllocation> findAllByProjectClient(String client);
	List<EmployeeAllocation> findAllByProjectProjectType(String project_type);
	List<EmployeeAllocation> findAllByProjectManager(String manager);
	EmployeeAllocation findByEmployeeId(String employeeName);
	List<EmployeeAllocation> findAllByStatus(String status);
	List<EmployeeAllocation> findByEmployeeIdContainingIgnoreCase(String searchTerm);

	List<EmployeeAllocation> findByEmployeeNameContainingIgnoreCase(String searchTerm);

	 @Query("SELECT ea FROM EmployeeAllocation ea WHERE ea.employeeName LIKE %:searchTerm% " +
	           "AND ea.project.project_startdate BETWEEN :fromDate AND :toDate " +
	           "AND ea.project.project_enddate BETWEEN :fromDate AND :toDate")
	    List<EmployeeAllocation> findByEmployeeNameAndProjectDates(
	            @Param("searchTerm") String searchTerm,
	            @Param("fromDate") Date fromDate,
	            @Param("toDate") Date toDate);
}
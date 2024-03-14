package com.ldtech.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ldtech.entity.EmployeeAllocation;
import com.ldtech.entity.EmployeeProfile;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface DashboardRepository extends JpaRepository<EmployeeAllocation, String> {
	/*
	 * @Query("SELECT e FROM EmployeeAllocation e WHERE e.employeeId LIKE :idPattern"
	 * ) List<EmployeeAllocation> findByEmployeeIdLike(@Param("idPattern") String
	 * idPattern); List<EmployeeAllocation> findAllByEmployeeId(String employeeId);
	 * List<EmployeeAllocation> findAllByEmployeeName(String employeeName);
	 * List<EmployeeAllocation> findAllByDepartment(String department);
	 * List<EmployeeAllocation> findAllByProjectProjectName(String project);
	 * List<EmployeeAllocation> findAllByProjectClient(String client);
	 * 
	 * List<EmployeeAllocation> findAllByProjectManager(String manager);
	 * EmployeeAllocation findByEmployeeId(String employeeName);
	 * List<EmployeeAllocation> findAllByStatus(String status);
	 * List<EmployeeAllocation> findByEmployeeIdContainingIgnoreCase(String
	 * searchTerm);
	 * 
	 * List<EmployeeAllocation> findByEmployeeNameContainingIgnoreCase(String
	 * searchTerm);
	 * 
	 * @Query("SELECT ea FROM EmployeeAllocation ea WHERE ea.employeeName LIKE %:searchTerm% "
	 * + "AND ea.project.project_startdate BETWEEN :fromDate AND :toDate " +
	 * "AND ea.project.project_enddate BETWEEN :fromDate AND :toDate")
	 * List<EmployeeAllocation> findByEmployeeNameAndProjectDates(
	 * 
	 * @Param("searchTerm") String searchTerm,
	 * 
	 * @Param("fromDate") Date fromDate,
	 * 
	 * @Param("toDate") Date toDate);
	 */
	@Query("SELECT e FROM EmployeeAllocation e WHERE e.employeeId LIKE :idPattern")
	List<EmployeeAllocation> findByEmployeeIdLike(@Param("idPattern") String idPattern);

	List<EmployeeAllocation> findAllByEmployeeId(String employeeId);

	List<EmployeeAllocation> findAllByEmployeeName(String employeeName);

	List<EmployeeAllocation> findAllByDepartment(String department);

	List<EmployeeAllocation> findAllByProjectProjectName(String project);
	
	List<EmployeeAllocation> findAllByProjectProjectType(String project);

	List<EmployeeAllocation> findAllByProjectClient(String client);

	List<EmployeeAllocation> findAllByProjectManager(String manager);

	EmployeeAllocation findByEmployeeId(String employeeName);

	List<EmployeeAllocation> findAllByStatus(String status);

	@Query(value = "select * from employee_allocation where pid=:id ", nativeQuery = true)
	List<EmployeeAllocation> findByEmpProject(@Param("id") Integer projectId);

	@Query("SELECT emp.employeeId FROM EmployeeAllocation emp JOIN emp.project p WHERE p.project_id = :projectId")
	List<String> findEmployeeIdsByProjectId(@Param("projectId") Integer projectId);
	
	
	@Query(value="SELECT  b.employee_id, "
            + "                b.employee_name, "
            + "                e.designation, "
            + "                a.all_end_date "
            + "FROM employee_profile as e "
            + "JOIN allocation_date as a ON e.employee_id = a.eid "
            + "JOIN employee_allocation as b ON b.employee_id = a.eid "
            + "WHERE e.project = 'NA'",nativeQuery = true)
    public List<Map<String, Object>> benchEmp();

	
	@Query(value="SELECT  b.employee_id, "
            + "                b.employee_name, "
            + "                e.designation, "
            + "                a.all_end_date "
            + "FROM employee_profile as e "
            + "JOIN allocation_date as a ON e.employee_id = a.eid "
            + "JOIN employee_allocation as b ON b.employee_id = a.eid "
            + "WHERE e.status = 'inactive' and b.employee_id=:employeeId",nativeQuery = true)
    public Map<String, Object> benchEmpById(@Param(value = "employeeId") String employeeId);
	
	@Query(value="SELECT  b.employee_id, "
            + "                b.employee_name, "
            + "                e.designation, "
            + "                a.all_end_date "
            + "FROM employee_profile as e "
            + "JOIN allocation_date as a ON e.employee_id = a.eid "
            + "JOIN employee_allocation as b ON b.employee_id = a.eid "
            + "WHERE b.status = 'inactive' and b.employee_name=:employeeName",nativeQuery = true)
    public List<Map<String, Object>> benchEmpByName(@Param(value = "employeeName") String employeeName);
	
	@Query(value="SELECT  b.employee_id, "
            + "                b.employee_name, "
            + "                e.designation, "
            + "                a.all_end_date "
            + "FROM employee_profile as e "
            + "JOIN allocation_date as a ON e.employee_id = a.eid "
            + "JOIN employee_allocation as b ON b.employee_id = a.eid "
            + "WHERE b.status = 'inactive' and b.department=:department",nativeQuery = true)
    public List<Map<String, Object>> benchEmpByDept(@Param(value = "department") String department);
	
}
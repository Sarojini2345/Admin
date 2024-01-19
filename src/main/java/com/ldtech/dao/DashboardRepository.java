package com.ldtech.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ldtech.entity.EmployeeAllocation;
import java.util.List;

public interface DashboardRepository extends JpaRepository<EmployeeAllocation, Long>{
	@Query("SELECT e FROM EmployeeAllocation e WHERE e.employeeId LIKE :idPattern")
    List<EmployeeAllocation> findByEmployeeIdLike(@Param("idPattern") String idPattern);
	//List<EmployeeAllocation> findAllByEmployeeId(String employeeId);
	List<EmployeeAllocation> findAllByEmployeeName(String employeeName);
	List<EmployeeAllocation> findAllByDepartment(String department);
	List<EmployeeAllocation> findAllByProject(String project);
	List<EmployeeAllocation> findAllByClient(String client);
	List<EmployeeAllocation> findAllByManager(String manager);
	List<EmployeeAllocation> findAllByStatus(String status);
}
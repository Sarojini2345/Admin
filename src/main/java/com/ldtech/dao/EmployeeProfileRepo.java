package com.ldtech.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ldtech.entity.EmployeeAllocation;
import com.ldtech.entity.EmployeeProfile;

public interface EmployeeProfileRepo extends JpaRepository<EmployeeProfile,Long>{
	EmployeeProfile findByEmployeeId(String employeeId);
	@Query(value="select count(*) from employee_profile where project='Allocated'",nativeQuery=true)
	Long findAllocatedEmployee();
	@Query(value="select count(*) from employee_profile where project='NA'and department='IT'",nativeQuery=true)
	Long findUnAllocatedEmployee();
	@Query("SELECT e FROM EmployeeProfile e WHERE e.employeeId LIKE :idPattern")
    List<EmployeeProfile> findByEmployeeIdLike(@Param("idPattern") String idPattern);
	List<EmployeeProfile> findAllByEmployeeId(String employeeId);
	List<EmployeeProfile> findAllByEmployeeName(String employeeName);
	List<EmployeeProfile> findAllByDepartment(String department);
	List<EmployeeProfile> findAllByStatus(String status);
}
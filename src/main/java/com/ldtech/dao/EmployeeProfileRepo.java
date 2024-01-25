package com.ldtech.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ldtech.entity.EmployeeProfile;

public interface EmployeeProfileRepo extends JpaRepository<EmployeeProfile,Long>{
	EmployeeProfile findByEmployeeId(String employeeId);
}

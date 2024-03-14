package com.ldtech.service;

import com.ldtech.entity.EmployeeProfile;

public interface IHomePage {
	
    public Long CountTotalEmployee();
    public Long countAllocatedEmployee();
    public Long countUnAllocatedEmployee();
   // public Long countUnAllocatedEmployee();
	public String RegisterEmployee(EmployeeProfile emp);
}

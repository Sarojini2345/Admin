package com.ldtech.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ldtech.entity.AllocationDate;

public interface EmployeeAllocationRepo extends JpaRepository<AllocationDate, Integer> {
    
}

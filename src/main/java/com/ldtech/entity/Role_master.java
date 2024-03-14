package com.ldtech.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Role_master {
	@Id
   private String roleId;
   private String roleName;
   
   
   @ManyToMany(mappedBy = "roles")
   private List<EmployeeProfile> employeeProfiles;
}

package com.ldtech.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class EmployeeAllocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String employeeId;

    private String employeeName;
    private String department;
    private String status = "active";

    @Column(name = "pid",insertable=false, updatable=false)
    private int pid;

    @ManyToOne
    @JoinColumn(name = "pid", referencedColumnName = "project_id")
    private ProjectEntity project;
}

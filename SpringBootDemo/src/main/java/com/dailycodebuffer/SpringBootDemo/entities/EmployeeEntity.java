package com.dailycodebuffer.SpringBootDemo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tbl_employee")
@Data
public class EmployeeEntity {
    @Id
    private String employeeId;
    private String firstName;
    private String lastName;
    private String emailId;
    private String department;
}

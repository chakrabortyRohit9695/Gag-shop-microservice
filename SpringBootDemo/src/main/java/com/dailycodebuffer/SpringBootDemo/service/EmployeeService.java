package com.dailycodebuffer.SpringBootDemo.service;

import com.dailycodebuffer.SpringBootDemo.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee save(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(String id);

    String deleteEmployeeById(String id);
}

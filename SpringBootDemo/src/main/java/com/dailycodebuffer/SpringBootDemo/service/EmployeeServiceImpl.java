package com.dailycodebuffer.SpringBootDemo.service;

import com.dailycodebuffer.SpringBootDemo.exceptions.EmployeeNotFoundException;
import com.dailycodebuffer.SpringBootDemo.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    List<Employee> employees = new ArrayList<>();

    @Override
    public Employee save(Employee employee) {
        //vaidation is employeeID is present, if not then create one
        if(employee.getEmployeeId() == null || employee.getEmployeeId().isEmpty()) {
            employee.setEmployeeId(UUID.randomUUID().toString());
            employees.add(employee);
            return employee;
        }
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employees;
    }

    @Override
    public Employee getEmployeeById(String id) {
        return employees.stream()
                .filter(emp -> emp.getEmployeeId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: "+ id));
    }

    @Override
    public String deleteEmployeeById(String id) {
        Employee employee =  employees.stream()
                .filter(emp -> emp.getEmployeeId().equals(id))
                .findFirst()
                .get();
        employees.remove(employee);
        return "Employee with " + id + " has been removed";
    }
}

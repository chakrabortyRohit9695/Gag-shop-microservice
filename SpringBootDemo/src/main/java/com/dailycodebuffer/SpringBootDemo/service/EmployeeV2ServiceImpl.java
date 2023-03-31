package com.dailycodebuffer.SpringBootDemo.service;

import com.dailycodebuffer.SpringBootDemo.entities.EmployeeEntity;
import com.dailycodebuffer.SpringBootDemo.model.Employee;
import com.dailycodebuffer.SpringBootDemo.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeV2ServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee save(Employee employee) {
        //vaidation is employeeID is present, if not then create one
        if(employee.getEmployeeId() == null || employee.getEmployeeId().isEmpty()) {
            employee.setEmployeeId(UUID.randomUUID().toString());
        }
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, employeeEntity);
        employeeRepository.save(employeeEntity);
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        List<Employee> employees = employeeEntities.stream()
                .map(employeeEntity -> {
                    Employee employee = new Employee();
                    BeanUtils.copyProperties(employeeEntity, employee);
                    return employee;
                }).collect(Collectors.toList());

        return employees;
    }

    @Override
    public Employee getEmployeeById(String id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeEntity, employee);
        return employee;
    }

    @Override
    public String deleteEmployeeById(String id) {
        employeeRepository.deleteById(id);
        return "Employee deleted with the id " + id;
    }
}

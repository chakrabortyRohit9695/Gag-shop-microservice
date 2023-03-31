package com.dailycodebuffer.SpringBootDemo.repository;

import com.dailycodebuffer.SpringBootDemo.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {
}

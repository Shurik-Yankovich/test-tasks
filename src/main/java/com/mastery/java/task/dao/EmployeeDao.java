package com.mastery.java.task.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mastery.java.task.dto.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long> {
}

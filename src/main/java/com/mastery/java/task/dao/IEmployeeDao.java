package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;

import java.util.Collection;
import java.util.Optional;

public interface IEmployeeDao {
    Long create(Employee employee);
    Employee read(Long id);
    boolean update(Long id, Employee employee);
    int delete(Long id);
    Collection<Employee> readAll();
}

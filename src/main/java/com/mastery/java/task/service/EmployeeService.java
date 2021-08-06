package com.mastery.java.task.service;

import com.mastery.java.task.dto.Employee;

import java.util.Collection;

public interface EmployeeService {
    Employee create(Employee employee);
    Employee read(Long id);
    Employee update(Employee employee);
    void delete(Long id);
    Collection<Employee> readAll();
}

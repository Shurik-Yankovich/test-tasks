package com.mastery.java.task.service;

import com.mastery.java.task.dto.Employee;

import java.util.Collection;

public interface IEmployeeService {
    Employee create(Employee employee);
    Employee read(Long id);
    boolean update(Long id, Employee employee);
    int delete(Long id);
    Collection<Employee> readAll();
}

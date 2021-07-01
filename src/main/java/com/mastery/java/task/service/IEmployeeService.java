package com.mastery.java.task.service;

import com.mastery.java.task.dto.Employee;

import java.util.Collection;

public interface IEmployeeService {
    Employee create(Employee employee);
    Employee read(Long pk);
    boolean update(Employee employee);
    boolean delete(Long pk);
    Collection<Employee> readAll();
}

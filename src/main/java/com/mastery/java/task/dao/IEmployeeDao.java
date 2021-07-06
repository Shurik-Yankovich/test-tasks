package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;

import java.util.Collection;
import java.util.Optional;

public interface IEmployeeDao {
    Long create(Employee employee);
    Employee read(Long pk);
    boolean update(Employee employee);
    int delete(Long pk);
    Collection<Employee> readAll();
}

package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;

import java.util.Collection;

public interface IEmployeeDao {
    Long create(Employee employee);
    Employee read(Long pk);
    Employee update(Employee employee);
    boolean delete(Long pk);
    Collection<Employee> readAll();
}

package com.mastery.java.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mastery.java.task.dao.IEmployeeDao;
import com.mastery.java.task.dto.Employee;

import java.util.Collection;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private IEmployeeDao employeeDao;

    public Employee create(Employee employee) {
        Long employeeId = employeeDao.create(employee);
        return employeeDao.read(employeeId);
    }

    public Employee read(Long pk) {
        return employeeDao.read(pk);
    }

    public Employee update(Employee employee) {
        return employeeDao.update(employee);
    }

    public boolean delete(Long pk) {
        return employeeDao.delete(pk);
    }

    public Collection<Employee> readAll() {
        return employeeDao.readAll();
    }
}

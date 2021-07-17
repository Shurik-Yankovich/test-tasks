package com.mastery.java.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mastery.java.task.dao.IEmployeeDao;
import com.mastery.java.task.dto.Employee;

import java.util.Collection;

@Service
public class EmployeeService implements IEmployeeService {

    private IEmployeeDao employeeDao;

    @Autowired
    public EmployeeService(IEmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public Employee create(Employee employee) {
        Long employeeId = employeeDao.create(employee);
        return employeeId != 0 ? employeeDao.read(employeeId) : null;
    }

    public Employee read(Long id) {
        return employeeDao.read(id);
    }

    public boolean update(Long id, Employee employee) {
        return employeeDao.update(id, employee);
    }

    public int delete(Long id) {
        return employeeDao.delete(id);
    }

    public Collection<Employee> readAll() {
        return employeeDao.readAll();
    }
}

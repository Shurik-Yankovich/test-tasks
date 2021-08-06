package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mastery.java.task.dto.Employee;

import java.util.Collection;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public Employee create(Employee employee) {
        return employeeDao.saveAndFlush(employee);
    }

    public Employee read(Long id) {
        return employeeDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Not found Tutorial with id = " + id));
    }

    public Employee update(Employee employee) {
        return employeeDao.saveAndFlush(employee);
    }

    public void delete(Long id) {
        employeeDao.deleteById(id);
    }

    public Collection<Employee> readAll() {
        return employeeDao.findAll();
    }
}

package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.create(employee);
        log.info("Employee with id = {} created", createdEmployee.getEmployeeId());
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Employee> read(@PathVariable Long id) {
        Employee employee = employeeService.read(id);
        log.info("Employee with id = {} read", id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.read(id);

        updatedEmployee.setFirstName(employee.getFirstName());
        updatedEmployee.setLastName(employee.getLastName());
        updatedEmployee.setDepartmentId(employee.getDepartmentId());
        updatedEmployee.setJobTitle(employee.getJobTitle());
        updatedEmployee.setGender(employee.getGender());
        updatedEmployee.setDateOfBirth(employee.getDateOfBirth());

        log.info("Employee with id = {} updated", id);
        return new ResponseEntity<>(employeeService.update(updatedEmployee), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        employeeService.delete(id);
        log.info("Employee with id = {} deleted", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/")
    public ResponseEntity<?> readAll() {
        log.info("All employee read");
        return new ResponseEntity<>(employeeService.readAll(), HttpStatus.OK);
    }
}

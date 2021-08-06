package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/")
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.create(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Employee> read(@PathVariable Long id) {
        Employee employee = employeeService.read(id);
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

        return new ResponseEntity<>(employeeService.update(updatedEmployee), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/")
    public ResponseEntity<?> readAll() {
        return new ResponseEntity<>(employeeService.readAll(), HttpStatus.OK);
    }
}

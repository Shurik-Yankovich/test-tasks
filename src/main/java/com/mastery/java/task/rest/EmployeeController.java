package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @PostMapping(value = "/employee")
    public ResponseEntity<?> create(@RequestBody Employee employee) {
        employee = employeeService.create(employee);
        return employee != null ?
                new ResponseEntity<>(employee, HttpStatus.OK) :
                new ResponseEntity<>("Incorrect data entered", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/employee/{id}")
    public ResponseEntity<?> read(@PathVariable(name = "id") Long pk) {
        Employee employee = employeeService.read(pk);
        return employee != null ?
                new ResponseEntity<>(employee, HttpStatus.OK) :
                new ResponseEntity<>("This employee ID does not exist", HttpStatus.BAD_GATEWAY);
    }

    @PutMapping(value = "/employee")
    public ResponseEntity<String> update(@RequestBody Employee employee) {
        return employeeService.update(employee) ?
                new ResponseEntity<>("Updating of the employee was successful", HttpStatus.OK) :
                new ResponseEntity<>("This employee does not exist", HttpStatus.OK);
    }

    @DeleteMapping(value = "/employee/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long pk) {
        int deletedRow = employeeService.delete(pk);
        return new ResponseEntity<>(String.format("Deleted records is %s", deletedRow), HttpStatus.OK);
    }

    @GetMapping(value = "/employee")
    public ResponseEntity<?> readAll() {
        return new ResponseEntity<>(employeeService.readAll(), HttpStatus.OK);
    }
}

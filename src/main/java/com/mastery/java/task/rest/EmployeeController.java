package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @PostMapping(value = "/")
    public ResponseEntity<?> create(@RequestBody Employee employee) {
        employee = employeeService.create(employee);
        return employee != null ?
                new ResponseEntity<>(employee, HttpStatus.OK) :
                new ResponseEntity<>("Incorrect data entered", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> read(@PathVariable Long id) {
        Employee employee = employeeService.read(id);
        return employee != null ?
                new ResponseEntity<>(employee, HttpStatus.OK) :
                new ResponseEntity<>("This employee ID does not exist", HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.update(id, employee) ?
                new ResponseEntity<>("Updating of the employee was successful", HttpStatus.OK) :
                new ResponseEntity<>("This employee does not exist", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        int deletedRow = employeeService.delete(id);
        return new ResponseEntity<>(String.format("Deleted records is %s", deletedRow), HttpStatus.OK);
    }

    @GetMapping(value = "/")
    public ResponseEntity<?> readAll() {
        return new ResponseEntity<>(employeeService.readAll(), HttpStatus.OK);
    }
}

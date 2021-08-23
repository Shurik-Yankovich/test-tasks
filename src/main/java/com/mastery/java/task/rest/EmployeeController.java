package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Validated
@RestController
@RequestMapping(value = "/employee")
@Tag(name = "Employees", description = "crud operation with employee")
public class EmployeeController {

    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    @Operation(summary = "Create employee", description = "Allows to create an employee")
    public ResponseEntity<Employee> create(@Valid @RequestBody Employee employee) {
        Employee createdEmployee = employeeService.create(employee);
        log.info("Employee with id = {} created", createdEmployee.getEmployeeId());
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Read employee", description = "Allows to read employee information by id")
    public ResponseEntity<Employee> read(@PathVariable @Min(1) Long id) {
        Employee employee = employeeService.read(id);
        log.info("Employee with id = {} read", id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Update employee", description = "Allows to update employee information")
    public ResponseEntity<?> update(@PathVariable @Min(1) Long id, @Valid @RequestBody Employee employee) {
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
    @Operation(summary = "Delete employee", description = "Allows to delete employee by id")
    public HttpStatus delete(@PathVariable @Min(1) Long id) {
        employeeService.delete(id);
        log.info("Employee with id = {} deleted", id);
        return HttpStatus.NO_CONTENT;
    }

    @GetMapping
    @Operation(summary = "Read all employee", description = "Allows to read information about all employee")
    public ResponseEntity<?> readAll() {
        log.info("All employee read");
        return new ResponseEntity<>(employeeService.readAll(), HttpStatus.OK);
    }
}

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
        return new ResponseEntity<>(employeeService.create(employee), HttpStatus.OK);
    }

    @GetMapping(value = "/employee/{id}")
    public ResponseEntity<?> read(@PathVariable(name = "id") Long pk) {
        return new ResponseEntity<>(employeeService.read(pk), HttpStatus.OK);
    }

    @PutMapping(value = "/employee")
    public ResponseEntity<?> update(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.update(employee), HttpStatus.OK);
    }

    @DeleteMapping(value = "/employee/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long pk) {
        return employeeService.delete(pk) ?
                new ResponseEntity<>("Removal of the employee was successful", HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
    }

    @GetMapping(value = "/employee")
    public ResponseEntity<?> readAll() {
        return new ResponseEntity<>(employeeService.readAll(), HttpStatus.OK);
    }
}

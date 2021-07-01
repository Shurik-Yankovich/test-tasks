package com.mastery.java.task.service;

import com.mastery.java.task.configuration.EmployeeServiceTestContextConfiguration;
import com.mastery.java.task.dao.IEmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = EmployeeServiceTestContextConfiguration.class)
public class EmployeeServiceTest {

    @Autowired
    private IEmployeeDao employeeDao;
    @Autowired
    private IEmployeeService employeeService;

    private static Employee EXPECTED_EMPLOYEE;

    @BeforeAll
    public static void init() {
        EXPECTED_EMPLOYEE = new Employee(1, "Test", "Test",
                2, "Test", Gender.MALE, LocalDate.of(1990, 1, 1));
    }

    @Test
    public void createEmployee_whenParametersAsNull_thenGetNull() {
        Employee employee = new Employee();

        Mockito.doReturn(0L).when(employeeDao).create(employee);
        Mockito.doReturn(null).when(employeeDao).read(0L);

        assertNull(employeeService.create(employee));
    }

    @Test
    public void createEmployee_whenCorrectEmployeeDate_thenReturnThisEmployeeWithId() {
        Employee employee = new Employee(0, "Test", "Test",
                2, "Test", Gender.MALE, LocalDate.of(1990, 1, 1));

        Mockito.doReturn(1L).when(employeeDao).create(employee);
        Mockito.doReturn(new Employee(1, "Test", "Test",
                2, "Test", Gender.MALE, LocalDate.of(1990, 1, 1)))
                .when(employeeDao).read(1L);

        Employee actualEmployee = employeeService.create(employee);
        assertEquals(EXPECTED_EMPLOYEE, actualEmployee);
    }

    @Test
    public void readEmployee_whenEmployeeIdIsCorrect_thenGetEmployeeDate() {
        Long employeeId = 1L;

        Mockito.doReturn(new Employee(1, "Test", "Test",
                2, "Test", Gender.MALE, LocalDate.of(1990, 1, 1)))
                .when(employeeDao).read(employeeId);

        Employee actualEmployee = employeeService.read(employeeId);
        assertEquals(EXPECTED_EMPLOYEE, actualEmployee);
    }

    @Test
    public void readEmployee_whenEmployeeIdIsNotCorrect_thenGetNull() {
        Long employeeId = 4L;

        Mockito.doReturn(null).when(employeeDao).read(employeeId);

        Employee actualEmployee = employeeService.read(employeeId);
        assertNull(actualEmployee);
    }

    @Test
    public void updateEmployee_whenEmployeeExistAndHaveCorrectDate_thenGetTrue() {
        Employee employee = new Employee(1, "Test", "Test",
                2, "Test", Gender.MALE, LocalDate.of(1990, 1, 1));

        Mockito.doReturn(true).when(employeeDao).update(employee);

        assertTrue(employeeService.update(employee));
    }

    @Test
    public void deleteEmployee_whenEmployeeIdIsNotCorrect_thenGetTrue() {
        Long employeeId = 1L;

        Mockito.doReturn(true).when(employeeDao).delete(employeeId);

        assertTrue(employeeService.delete(employeeId));
    }

    @Test
    public void readAllEmployees_whenDataBaseNotEmpty_thenReturnCorrectEmployeesList() {
        List<Employee> expectedEmployeeList = new ArrayList<>();
        expectedEmployeeList.add(EXPECTED_EMPLOYEE);

        List<Employee> actualEmployeeList = new ArrayList<>();
        actualEmployeeList.add(new Employee(1, "Test", "Test",
                2, "Test", Gender.MALE, LocalDate.of(1990, 1, 1)));

        Mockito.doReturn(actualEmployeeList).when(employeeDao).readAll();

        assertEquals(expectedEmployeeList, actualEmployeeList);
    }
}

package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import com.mastery.java.task.dto.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeDao employeeDao;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private static final Employee EXPECTED_EMPLOYEE = new Employee(1, "Test", "Test",
            2, "Test", Gender.MALE, LocalDate.of(1990, 1, 1));

    @Test
    public void createEmployee_whenParametersAsNull_thenGetNull() {
        Employee employee = new Employee();

        Mockito.doReturn(null).when(employeeDao).saveAndFlush(employee);

        assertNull(employeeService.create(employee));
    }

    @Test
    public void createEmployee_whenCorrectEmployeeDate_thenReturnThisEmployeeWithId() {
        Employee employee = new Employee(0, "Test", "Test",
                2, "Test", Gender.MALE, LocalDate.of(1990, 1, 1));

        Mockito.doReturn(new Employee(1, "Test", "Test",
                2, "Test", Gender.MALE, LocalDate.of(1990, 1, 1)))
                .when(employeeDao).saveAndFlush(employee);

        Employee actualEmployee = employeeService.create(employee);
        assertEquals(EXPECTED_EMPLOYEE, actualEmployee);
    }

    @Test
    public void readEmployee_whenEmployeeIdIsCorrect_thenGetEmployeeDate() {
        Long employeeId = 1L;
        Employee employee = new Employee(1, "Test", "Test",
                2, "Test", Gender.MALE, LocalDate.of(1990, 1, 1));
        Optional<Employee> optionalEmployee = Optional.of(employee);

        Mockito.doReturn(optionalEmployee).when(employeeDao).findById(employeeId);

        Employee actualEmployee = employeeService.read(employeeId);
        assertEquals(EXPECTED_EMPLOYEE, actualEmployee);
    }

    @Test
    public void readEmployee_whenEmployeeIdIsNotCorrect_thenGetNull() {
        Long employeeId = 4L;
        Optional<Employee> optionalEmployee = Optional.empty();

        Mockito.doReturn(optionalEmployee).when(employeeDao).findById(employeeId);

        assertThrows(ResourceNotFoundException.class, () -> employeeService.read(employeeId));
    }

    @Test
    public void updateEmployee_whenEmployeeExistAndHaveCorrectDate_thenGetTrue() {
        Employee employee = new Employee(1, "Test", "Test",
                2, "Test", Gender.MALE, LocalDate.of(1990, 1, 1));

        Mockito.doReturn(employee).when(employeeDao).saveAndFlush(employee);

        Employee actualEmployee = employeeService.update(employee);
        assertEquals(EXPECTED_EMPLOYEE, actualEmployee);
    }

    @Test
    public void readAllEmployees_whenDataBaseNotEmpty_thenReturnCorrectEmployeesList() {
        List<Employee> expectedEmployeeList = new ArrayList<>();
        expectedEmployeeList.add(EXPECTED_EMPLOYEE);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(1, "Test", "Test",
                2, "Test", Gender.MALE, LocalDate.of(1990, 1, 1)));

        Mockito.doReturn(employeeList).when(employeeDao).findAll();

        Collection<Employee> actualEmployeeCollection = employeeService.readAll();

        assertEquals(expectedEmployeeList, actualEmployeeCollection);
    }
}

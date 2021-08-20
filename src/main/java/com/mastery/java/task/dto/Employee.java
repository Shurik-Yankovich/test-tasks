package com.mastery.java.task.dto;

import com.mastery.java.task.utils.valid.AdultAge;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "employee")
@Schema(description = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Идентификатор", example = "1")
    private long employeeId;
    @NotBlank
    @Schema(example = "John")
    private String firstName;
    @NotBlank
    @Schema(example = "Smith")
    private String lastName;
    @NotNull
    @Schema(description = "Идентификатор департамента", example = "1111")
    private long departmentId;
    @NotBlank
    @Schema(description = "Название должности", example = "Java developer")
    private String jobTitle;
    @Enumerated(EnumType.STRING)
    @Schema(description = "Пол сотрудника")
    private Gender gender;
    @AdultAge
    @Schema(description = "Дата рождения сотрудника", example = "2000-05-25")
    private LocalDate dateOfBirth;

    public Employee() {
    }

    public Employee(long employeeId, String firstName, String lastName, long departmentId, String jobTitle, Gender gender, LocalDate dateOfBirth) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
        this.jobTitle = jobTitle;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return employeeId == employee.employeeId &&
                departmentId == employee.departmentId &&
                firstName.equals(employee.firstName) &&
                lastName.equals(employee.lastName) &&
                jobTitle.equals(employee.jobTitle) &&
                gender == employee.gender &&
                dateOfBirth.equals(employee.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, firstName, lastName, departmentId, jobTitle, gender, dateOfBirth);
    }
}

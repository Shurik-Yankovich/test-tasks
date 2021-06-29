package com.mastery.java.task.dao;

import com.mastery.java.task.utils.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import com.mastery.java.task.dto.Employee;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Collection;

@Repository
@Transactional
public class EmployeeDao extends JdbcDaoSupport implements IEmployeeDao {

    @Autowired
    public EmployeeDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public Long create(Employee employee) {
        String query = "INSERT INTO employee (employee_id, first_name, last_name, department_id, job_title, gender, date_of_birth)" +
                " VALUES (DEFAULT, ?, ?, ?, ?, ?, ?) RETURNING employee_id";
        Long id = this.getJdbcTemplate().queryForObject(query,
                new Object[]{employee.getFirstName(),
                        employee.getLastName(), employee.getDepartmentId(), employee.getJobTitle(),
                        employee.getGender().name(), employee.getDateOfBirth()},
                (rs, i) -> rs.getLong("employee_id"));
        return id;
    }

    public Employee read(Long pk) {
        String query = "SELECT * FROM employee WHERE employee_id = ?";
        Employee employee = this.getJdbcTemplate().queryForObject(
                query, new Object[]{pk}, new EmployeeMapper());
        return employee;
    }

    public Employee update(Employee employee) {
        String query = "UPDATE employee SET first_name = ?, last_name = ?, department_id = ?, " +
                "job_title = ?, gender = ?, date_of_birth = ? WHERE employee_id = ?";
        int answer = this.getJdbcTemplate().update(query, employee.getFirstName(),
                employee.getLastName(), employee.getDepartmentId(), employee.getJobTitle(),
                employee.getGender().name(), employee.getDateOfBirth(), employee.getEmployeeId());
        return employee;
    }

    public boolean delete(Long pk) {
        String query = "DELETE FROM employee WHERE employee_id = ?";
        return this.getJdbcTemplate().update(query, pk) == 1;
    }

    public Collection<Employee> readAll() {
        String query = "SELECT * FROM employee";
        Collection<Employee> employees = this.getJdbcTemplate().query(query, new EmployeeMapper());
        return employees;
    }
}

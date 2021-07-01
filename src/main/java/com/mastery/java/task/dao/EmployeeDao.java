package com.mastery.java.task.dao;

import com.mastery.java.task.utils.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
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

        Long employee_id;
        try {
            employee_id = this.getJdbcTemplate().queryForObject(query,
                    new Object[]{employee.getFirstName(),
                            employee.getLastName(), employee.getDepartmentId(), employee.getJobTitle(),
                            employee.getGender().name(), employee.getDateOfBirth()},
                    (rs, i) -> rs.getLong("employee_id"));
        } catch (DataAccessException e) {
            employee_id = 0L;
        }
        return employee_id;
    }

    public Employee read(Long pk) {
        String query = "SELECT * FROM employee WHERE employee_id = ?";

        Employee employee;
        try {
            employee = this.getJdbcTemplate().queryForObject(
                    query, new Object[]{pk}, new EmployeeMapper());
        } catch (EmptyResultDataAccessException e) {
            employee = null;
        }
        return employee;
    }

    public boolean update(Employee employee) {
        String query = "UPDATE employee SET first_name = ?, last_name = ?, department_id = ?, " +
                "job_title = ?, gender = ?, date_of_birth = ? WHERE employee_id = ?";

        int answer;
        try {
        answer = this.getJdbcTemplate().update(query, employee.getFirstName(),
                employee.getLastName(), employee.getDepartmentId(), employee.getJobTitle(),
                employee.getGender().name(), employee.getDateOfBirth(), employee.getEmployeeId());
        } catch (DataAccessException e) {
            answer = 0;
        }
        return answer == 1;
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

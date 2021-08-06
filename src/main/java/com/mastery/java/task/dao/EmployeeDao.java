package com.mastery.java.task.dao;

import com.mastery.java.task.utils.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.mastery.java.task.dto.Employee;

import java.util.Collection;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Long> {

//    @Query("insert ba from BankAccount ba where ba.user.id in :ids")
//    Employee findByUserIds(@Param("ids") List<Long> ids);

//    private static final String CREATE_QUERY = "INSERT INTO employee (employee_id, first_name, last_name, department_id, job_title, gender, date_of_birth)" +
//            " VALUES (DEFAULT, ?, ?, ?, ?, ?, ?) RETURNING employee_id";
//    private static final String READ_QUERY = "SELECT * FROM employee WHERE employee_id = ?";
//    private static final String UPDATE_QUERY = "UPDATE employee SET first_name = ?, last_name = ?, department_id = ?, " +
//            "job_title = ?, gender = ?, date_of_birth = ? WHERE employee_id = ?";
//    private static final String DELETE_QUERY = "DELETE FROM employee WHERE employee_id = ?";
//    private static final String READ_ALL_QUERY = "SELECT * FROM employee";
//
//    private JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public EmployeeDao(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public Long create(Employee employee) {
//        Long employee_id;
//        try {
//            employee_id = jdbcTemplate.queryForObject(CREATE_QUERY,
//                    new Object[]{employee.getFirstName(),
//                            employee.getLastName(), employee.getDepartmentId(), employee.getJobTitle(),
//                            employee.getGender().name(), employee.getDateOfBirth()},
//                    (rs, i) -> rs.getLong("employee_id"));
//        } catch (DataAccessException e) {
//            employee_id = 0L;
//        }
//        return employee_id;
//    }
//
//    public Employee read(Long id) {
//        Employee employee;
//        try {
//            employee = jdbcTemplate.queryForObject(READ_QUERY, new Object[]{id}, new EmployeeMapper());
//        } catch (EmptyResultDataAccessException e) {
//            employee = null;
//        }
//        return employee;
//    }
//
//    public boolean update(Long id, Employee employee) {
//        int answer;
//        try {
//        answer = jdbcTemplate.update(UPDATE_QUERY, employee.getFirstName(),
//                employee.getLastName(), employee.getDepartmentId(), employee.getJobTitle(),
//                employee.getGender().name(), employee.getDateOfBirth(), id);
//        } catch (DataAccessException e) {
//            answer = 0;
//        }
//        return answer == 1;
//    }
//
//    public int delete(Long id) {
//        return jdbcTemplate.update(DELETE_QUERY, id);
//    }
//
//    public Collection<Employee> readAll() {
//        return jdbcTemplate.query(READ_ALL_QUERY, new EmployeeMapper());
//    }
}

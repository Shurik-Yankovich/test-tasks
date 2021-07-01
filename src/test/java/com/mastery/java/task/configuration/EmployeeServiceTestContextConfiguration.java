package com.mastery.java.task.configuration;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dao.IEmployeeDao;
import com.mastery.java.task.service.EmployeeService;
import com.mastery.java.task.service.IEmployeeService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeServiceTestContextConfiguration {

    @Bean
    public IEmployeeService employeeService() {
        return new EmployeeService(employeeDao());
    }

    @Bean
    public IEmployeeDao employeeDao() {
        return Mockito.mock(EmployeeDao.class);
    }
}

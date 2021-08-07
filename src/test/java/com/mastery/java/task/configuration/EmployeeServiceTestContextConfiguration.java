package com.mastery.java.task.configuration;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.service.EmployeeServiceImpl;
import com.mastery.java.task.service.EmployeeService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeServiceTestContextConfiguration {

    @Bean
    public EmployeeService employeeService() {
        return new EmployeeServiceImpl(employeeDao());
    }

    @Bean
    public EmployeeDao employeeDao() {
        return Mockito.mock(EmployeeDao.class);
    }
}

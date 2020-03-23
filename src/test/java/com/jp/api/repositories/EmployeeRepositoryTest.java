package com.jp.api.repositories;

import com.jp.api.configuration.RepositoryConfiguration;
import com.jp.api.models.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RepositoryConfiguration.class})
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void save() {
        Employee employee = new Employee();
        employee.setName("maria");
        employee.setEmail("maria@gmail.com");
        employee.setPhone("913333333");
        assertNull(employee.getId());
        employeeRepository.save(employee);
        assertNotNull(employee.getId());
    }
}
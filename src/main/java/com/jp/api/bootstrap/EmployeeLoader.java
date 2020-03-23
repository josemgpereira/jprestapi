package com.jp.api.bootstrap;

import com.jp.api.models.Employee;
import com.jp.api.models.User;
import com.jp.api.repositories.EmployeeRepository;
import com.jp.api.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EmployeeLoader implements ApplicationListener<ContextRefreshedEvent> {

    private EmployeeRepository employeeRepository;
    private UserRepository userRepository;

    private Logger log = LogManager.getLogger(EmployeeLoader.class);

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //Employee employee = new Employee(1L, "jose", "jose@gmail.com", "912222222");
        Employee employee = new Employee();
        employee.setName("jose");
        employee.setEmail("joseBootstrap@gmail.com");
        employee.setPhone("915555555");
        employeeRepository.save(employee);
        log.info("Saved Employee - ID: " + employee.getId());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        log.info("Password for 123: " + encoder.encode("123"));

        /*
        User user = new User();
        user.setName("manuel");
        user.setEmail("manuel@gmail.com");
        user.setLogin("manuel");
        user.setPassword("$2a$10$EM/vx7EVFQiWIfPsn.jNH.3nXxKtsSXUkt.VLqGGeFunz.6VY6oiG");
        userRepository.save(user);
        log.info("Saved User - ID: " + user.getId());
        */
    }
}
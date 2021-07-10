package com.example.springjpa;

import com.example.springjpa.model.Employee;
import com.example.springjpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;

@Configuration
public class EmployeeConfiguration {

    @Autowired
    EmployeeRepository employeeRepository;

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
//            employeeRepository.addNewEmployee(
//                    new Employee(1, "ABC", Date.valueOf("1990-2-23"))
//            );
        };
    }
}

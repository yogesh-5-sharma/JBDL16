package com.example.springjpa;

import com.example.springjpa.model.Employee;
import com.example.springjpa.model.LEVELS;
import com.example.springjpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import java.sql.Date;
import java.util.List;

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

            employeeRepository.save(new Employee("ABC", Date.valueOf("1998-12-13"), 1000, LEVELS.SDE1));
            employeeRepository.save(new Employee("ABD", Date.valueOf("1990-12-13"), 2000, LEVELS.SDE2));
            employeeRepository.save(new Employee("ABE", Date.valueOf("1993-12-13"), 2000, LEVELS.SDE2));
            employeeRepository.save(new Employee("ACE", Date.valueOf("1993-12-13"), 2000, LEVELS.SDE2));
            employeeRepository.save(new Employee("XYZ", Date.valueOf("1988-12-13"), 3000, LEVELS.SDE3));

//            List<Employee> employees = employeeRepository.findByNameAndDob("ABC", Date.valueOf("1990-12-13"));
//            List<Employee> employees = employeeRepository.findFirst2ByName("ABC", Sort.by("dob"));
            List<Employee> employees = employeeRepository.findByNameStartingWith("AB");

            System.out.println(employees);

        };
    }
}

package com.example.springjpa.service;

import com.example.springjpa.exceptions.EmployeeNotFoundException;
import com.example.springjpa.model.Employee;
import com.example.springjpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
//        return employeeRepository.findAllEmployees();
        return employeeRepository.findAll();
    }

    public void insertEmployee(Employee employee) {
//        employeeRepository.addNewEmployee(employee);
        employeeRepository.save(employee);
    }

    public Employee getEmployee(int id) {
//        return employeeRepository.findEmployee(id);
//        return employeeRepository.getById(id);
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }
}

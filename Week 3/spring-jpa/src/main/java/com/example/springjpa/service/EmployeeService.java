package com.example.springjpa.service;

import com.example.springjpa.exceptions.EmployeeNotFoundException;
import com.example.springjpa.model.Company;
import com.example.springjpa.model.Employee;
import com.example.springjpa.repository.CompanyRepository;
import com.example.springjpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CompanyRepository companyRepository;

    public List<Employee> getAllEmployees() {
//        return employeeRepository.findAllEmployees();
        return employeeRepository.findAll(Sort.by(Sort.Direction.DESC, "salary"));
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

    public void insertCompany(Company company) {
        companyRepository.save(company);
    }

    public void removeEmployee(int id) {
        Employee employee = getEmployee(id);
        employeeRepository.delete(employee);
    }

    @Transactional
    public void updateEmployee(int id, Employee employee) {
        Employee oldEmployee = getEmployee(id);

        if(employee.getName() != null) {
            oldEmployee.setName(employee.getName());
        }
        if(employee.getDob() != null) {
            oldEmployee.setDob(employee.getDob());
        }

        employeeRepository.save(oldEmployee);
        System.out.println(oldEmployee);

        System.exit(2);

        if(employee.getSalary() != null) {
            oldEmployee.setSalary(employee.getSalary());
        }
        if(employee.getLevel() != null) {
            oldEmployee.setLevel(employee.getLevel());
        }

        employeeRepository.save(oldEmployee);
    }

    public List<Employee> getByName(String employeeName) {
        return employeeRepository.findByEmployeeNameUsingJPQL(employeeName);
    }
}

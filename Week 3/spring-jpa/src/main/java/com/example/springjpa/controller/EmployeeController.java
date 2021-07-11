package com.example.springjpa.controller;

import com.example.springjpa.model.Company;
import com.example.springjpa.model.Employee;
import com.example.springjpa.model.LEVELS;
import com.example.springjpa.service.EmployeeService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public void addEmployee(@RequestBody Employee employee) {
        employeeService.insertEmployee(employee);
    }

    @PostMapping("/company")
    public void addCompany(@RequestBody Company company) {
        employeeService.insertCompany(company);
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable int id) {
        return employeeService.getEmployee(id);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id) {
        employeeService.removeEmployee(id);
    }

    @PutMapping("/{id}")
    public void updateEmployee(@RequestBody Employee employee, @PathVariable int id) {
        employeeService.updateEmployee(id, employee);
    }

    @GetMapping("/name/{employeeName}")
    public List<Employee> getEmployeeByName(@PathVariable String employeeName) {
        return employeeService.getByName(employeeName);
    }

    @GetMapping(value = "/printEmployee", consumes = "application/json", produces = "application/xml")
    public Employee printEmployee(@RequestBody Employee employee) {
        System.out.println(employee);
        return employee;
    }

    @GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@PathVariable int id) throws IOException {

//        File file = new File("006.png");
//        BufferedImage bufferedImage = ImageIO.read(file);
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        ImageIO.write(bufferedImage, "png", baos);
//        byte[] image = baos.toByteArray();
//
//        return image;

        RestTemplate restTemplate = new RestTemplate();

        byte[] image = restTemplate.getForObject(String.format("https://picsum.photos/id/%d/200/300", id), byte[].class);

        return image;
    }

    @PostMapping("/csv")
    public void addEmployeeThroughCSV(HttpServletRequest request) throws IOException, ServletException {
        Part file = request.getPart("myfile");

        InputStream inputStream = file.getInputStream();

        Reader reader = new InputStreamReader(inputStream);

        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

        for(CSVRecord record: csvParser) {
            String name = record.get(0);
            Date dob = Date.valueOf(record.get(1));
            Integer salary = Integer.parseInt(record.get(2));
            LEVELS level = LEVELS.valueOf(record.get(3));

            Employee employee = new Employee(name, dob, salary, level);

            employeeService.insertEmployee(employee);
        }
    }
}

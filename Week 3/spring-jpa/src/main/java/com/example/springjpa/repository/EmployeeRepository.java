package com.example.springjpa.repository;

import com.example.springjpa.model.CompositePrimaryKey;
import com.example.springjpa.model.Employee;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("FROM Employee e WHERE e.name = :myname")
    List<Employee> findByEmployeeNameUsingJPQL(String myname);

    @Query(value = "SELECT * FROM my_employee WHERE name= ?1", nativeQuery = true)
    List<Employee> findByEmployeeNameUsingNativeSQL(String myname);

    List<Employee> findByNameAndDob(String name, Date dob);

    List<Employee> findFirst2ByName(String name, Sort sort);

    List<Employee> findDistinctByName(String name);

    List<Employee> findByNameStartingWith(String prefix);

    @Transactional
    @Modifying
    @Query(value = "update my_employee set salary = :newSalary where id = :eid", nativeQuery = true)
    void updateSalary(Integer newSalary, Integer eid);
}

// Core Hibernate

//@Repository
//public class EmployeeRepository {
//    EntityManagerFactory factory = Persistence.createEntityManagerFactory("MyEmployee");
//
//    public void addNewEmployee(Employee employee) {
//        EntityManager em = factory.createEntityManager();
//
//        EntityTransaction et = em.getTransaction();
//
//        try {
//            et.begin();
//            em.persist(employee);
//            et.commit();
//        } catch (Exception ex) {
//            et.rollback();
//            ex.printStackTrace();
//        }
//
//        em.close();
//        factory.close();
//    }
//
//    public List<Employee> findAllEmployees() {
//        EntityManager em = factory.createEntityManager();
//
//        Query query = em.createQuery("FROM Employee");
//
//        List<Employee> employees = query.getResultList();
//
//        return employees;
//    }
//
//    public Employee findEmployee(int id) {
//        EntityManager em = factory.createEntityManager();
//
////        Query query = em.createQuery("FROM Employee where id= :a");
////        query.setParameter("a", id);
////        return (Employee)query.getSingleResult();
//
//        Employee employee = em.find(Employee.class, id);
//
//        return employee;
//    }
//}

// JDBC

//@Repository
//public class EmployeeRepository {
//
//    Connection connection;
//
//    PreparedStatement getQuery;
//
//    PreparedStatement getEmployee;
//
//    private static final Logger logger = LoggerFactory.getLogger(EmployeeRepository.class);
//
//    public EmployeeRepository() throws SQLException {
////        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jbdl16", "user", "password");
//
//        connection = DriverManager.getConnection("jdbc:h2:mem:jdbl16", "sa", "");
//
//        createTable();
//
//        getQuery = connection.prepareStatement(
//                "SELECT * FROM employee"
//        );
//
//        getEmployee = connection.prepareStatement(
//                "SELECT * FROM employee where id= ?"
//        );
//    }
//
//    private void createTable() throws SQLException {
//        String sqlQuery = "CREATE TABLE If NOT EXISTS employee(id int PRIMARY KEY, name VARCHAR(40), dob DATE)";
//
//        Statement statement = connection.createStatement();
//        statement.executeUpdate(sqlQuery);
//    }
//
//    public List<Employee> findAllEmployees() {
//
//        List<Employee> employees = new ArrayList<>();
//        try {
//            ResultSet resultSet = getQuery.executeQuery();
//
//            while(resultSet.next()) {
//                Employee employee = new Employee(
//                        resultSet.getInt(1),
//                        resultSet.getString(2),
//                        resultSet.getDate(3)
//                );
//
//                employees.add(employee);
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//
//        return employees;
//    }
//
//    public void addNewEmployee(Employee employee) {
//
//        String insertQuery = String.format("INSERT INTO employee VALUES(%d,'%s','%s')", employee.getId(), employee.getName(), employee.getDob());
//
//        try(Statement statement = connection.createStatement();) {
//            int rowsAdded = statement.executeUpdate(insertQuery);
//            logger.info("{} has been added", rowsAdded);
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public Employee findEmployee(int id) {
//
//        Employee employee = null;
//
//        try {
//            getEmployee.setInt(1, id);
////            getEmployee.setString(2, "ABC");
//
//            ResultSet resultSet = getEmployee.executeQuery();
//
//            while(resultSet.next()) {
//                employee = new Employee(
//                        resultSet.getInt("id"),
//                        resultSet.getString("name"),
//                        resultSet.getDate("dob")
//                );
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//
//        return employee;
//    }
//}

package com.example.springjpa.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "my_employee")
//@SecondaryTable(name = "child")
//@IdClass(value = CompositePrimaryKey.class)
public class Employee {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mygenerator")
//    @SequenceGenerator(name = "mygenerator", initialValue = 10, allocationSize = 5)
//    @GenericGenerator(name = "mygenerator", strategy = "uuid")
    @GeneratedValue
    private Integer id;

//    @Column(name = "fullname", unique = false, nullable = true)
    private String name;

//    @Column(insertable = false, updatable = false)
    private Date dob;

//    @Column(columnDefinition = "INT CHECK (salary>5000)")
    private Integer salary;

//    @Column(table = "child")
//    private String emailId;

    @Enumerated(EnumType.STRING)
    private LEVELS level;

    @Getter(AccessLevel.NONE)
    @Transient
    private int age;

    public Employee(String name, Date dob, Integer salary, LEVELS level) {
        this.name = name;
        this.dob = dob;
        this.salary = salary;
        this.level = level;
    }

    public int getAge() {
        return Period.between(this.dob.toLocalDate(), LocalDate.now()).getYears();
    }

    public void setAge(int age) {
        this.age = age;
    }
}

package com.example.collections;

import java.util.Objects;

public class Person implements Comparable<Person> {
    int age;
    String name;
    int amount;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
//        System.out.println("I am getting used");
        Person person = (Person) o;
        return
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }


    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }


    @Override
    public int compareTo(Person o) {
        if(this.age < o.age) {
            return -1;
        } else if(this.age > o.age) {
            return 1;
        }
        return this.name.compareTo(o.name);
    }
}

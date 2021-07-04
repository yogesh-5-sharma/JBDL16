package com.example.springbeansdemo.di;

import org.springframework.stereotype.Component;

@Component("car")
public class Car implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Driving a Car");
    }
}

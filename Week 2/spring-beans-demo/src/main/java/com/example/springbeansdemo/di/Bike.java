package com.example.springbeansdemo.di;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("bike")
@Primary
public class Bike implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Driving a Bike");
    }
}

package com.example.springbeansdemo.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Main {

    @Autowired
    Vehicle vehicle;

    @GetMapping("/drive")
    public void drive() {
        vehicle.drive();
    }
}

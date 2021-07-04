package com.example.springbeansdemo.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Main2 {
    @Autowired
    @Qualifier("car")
    Vehicle vehicle;

    @GetMapping("/drive2")
    public void drive() {
        vehicle.drive();
    }


}

package com.example.springbeansdemo.controller;

import com.example.springbeansdemo.di.Bike;
import com.example.springbeansdemo.di.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class MyConfig {

    @Bean
//    @Scope("prototype")
    public Utils getUtils() {
        System.out.println("In getUtils, creating a utils object");
        Utils utils = new Utils();
        System.out.println("MYConfig: " + utils);
        return utils;
    }

    @Bean
    public int getInteger() {
        return 5;
    }
}
//com.example.springbeansdemo.controller.Utils@38f96c04
//com.example.springbeansdemo.controller.Utils@38f96c04
//com.example.springbeansdemo.controller.Utils@38f96c04

//com.example.springbeansdemo.controller.Utils@54c116a7 - bye
//com.example.springbeansdemo.controller.Utils@5160a886 - hello
//com.example.springbeansdemo.controller.Utils@5997e365 - hello2
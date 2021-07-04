package com.example.springbeansdemo.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MyConfig2 {

    @Bean
//    @Scope("prototype")
    public Utils getUtils() {
        System.out.println("In getUtils2, creating a utils object");
        Utils utils = new Utils();
        System.out.println("MYConfig2: " + utils);
        return utils;
    }
}

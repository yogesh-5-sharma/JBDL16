package com.example.springbeansdemo.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component
//@Scope("prototype")
public class Utils {

    int count;

    Utils() {
        System.out.println("In utils constructor");
        count=0;
    }
}

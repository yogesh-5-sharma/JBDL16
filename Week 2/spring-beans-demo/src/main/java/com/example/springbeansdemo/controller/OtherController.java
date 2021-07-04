package com.example.springbeansdemo.controller;

import com.sun.net.httpserver.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerRequest;

import java.util.Map;

@RestController
public class OtherController {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    Utils utils;

    @GetMapping("/bye")
    public User bye(@RequestHeader Map<String, String> headers) {
//        Utils utils = myConfig.getUtils();
//        System.out.println("In OtherController");
//        System.out.println(utils);
//        System.out.println(utils.count);

        System.out.println("In OtherController");
        System.out.println(utils);
//        System.out.println(headers);
//
        return new User("ABC", 10);
    }

    @GetMapping("/printbeans")
    public String[] printBeans() {
        String[] beans = applicationContext.getBeanDefinitionNames();
        for(String bean: beans) {
            System.out.println(bean);
        }
        return beans;
    }
}

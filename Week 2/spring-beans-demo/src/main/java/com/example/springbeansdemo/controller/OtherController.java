package com.example.springbeansdemo.controller;

import com.sun.net.httpserver.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerRequest;

import javax.print.attribute.standard.MediaSize;
import java.util.Map;

@RestController
public class OtherController {

//    @Autowired
    ApplicationContext applicationContext;

//    @Autowired
    Utils utils;

    int b;

//    public OtherController() {}

    @Autowired
    public OtherController(ApplicationContext applicationContext, Utils utils, Integer a) {
        this.applicationContext = applicationContext;
        this.utils = utils;
        this.b=a;
        System.out.println(b);
    }

//    @Autowired
    public OtherController(ApplicationContext applicationContext, Utils utils) {
        this.applicationContext = applicationContext;
        this.utils = utils;
        this.b=10;
    }

    @GetMapping("/bye")
    public ResponseEntity<User> bye(@RequestHeader Map<String, String> headers) {
//        Utils utils = myConfig.getUtils();
//        System.out.println("In OtherController");
//        System.out.println(utils);
//        System.out.println(utils.count);

        System.out.println("In OtherController");
        System.out.println(utils);
//        System.out.println(headers);
//
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("BYE-HEADER", "in other controller");

        return new ResponseEntity<>(new User("ABC", 10), responseHeaders, HttpStatus.FORBIDDEN);
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

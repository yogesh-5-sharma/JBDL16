package com.example.springbeansdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    Logger logger = LoggerFactory.getLogger(MyController.class);

//    @Autowired
    MyConfig myConfig;

    static String hobby = "ABC";

//    @Value("${myapp.cost}")
    static Integer cost = 5;

//    @Value("${myapp.name}")
    static String name;

    MyController(@Autowired MyConfig myConfig, @Value("${myapp.cost}") Integer cost1, @Value("${myapp.name}") String name1) {
        this.myConfig = myConfig;
        cost = cost1;
        name = name1;
    }

    @GetMapping("/hello")
    public User getUser() {

//        utils = new Utils();

        Utils utils = myConfig.getUtils();
        System.out.println("In MyController");
        System.out.println("hello : " + utils);
        utils.count+=1;
        System.out.println(utils.count);
//
        return new User("ABC", 50);
    }

    @GetMapping("/hello2")
    public void hello2() {

//        utils = new Utils();
        Utils utils = myConfig.getUtils();
        System.out.println("hello2 : " + utils);
    }

    @GetMapping("/cost")
    public static void getCost() {
        System.out.println(name);
        System.out.println(cost);
    }

    @GetMapping("/hobby")
    public static String getHobby() {
        return hobby;
    }

    @GetMapping("/logs")
    public void logs(@RequestParam String name) {
        logger.error("Error log {}", name);
        logger.warn("Warn log {}", name);
        logger.info("Info log {}", name);
        logger.debug("Debug log {}", name);
        logger.trace("Trace log {}", name);
    }
}

//com.example.springbeansdemo.controller.Utils@957839d
//com.example.springbeansdemo.controller.Utils@957839d

//com.example.springbeansdemo.controller.Utils@54b6ebea
//com.example.springbeansdemo.controller.Utils@54b6ebea
//com.example.springbeansdemo.controller.Utils@54b6ebea
//com.example.springbeansdemo.controller.Utils@7dd1aa4f
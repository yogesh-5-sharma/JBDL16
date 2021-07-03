package com.example.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// 1. @RequestParam  api?q=
// 2. @PathVariable  api/{}
// 3. @RequestBody  from headers

@RestController
public class MyController {

    @GetMapping("/greet")
    public User greet() {

        User user = new User(50, "ABC");

        return user;

    }
}

package com.example.userservice.controller;

import com.example.userservice.models.User;
import com.example.userservice.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public void createNewUser(@RequestBody User user) throws JsonProcessingException {
        userService.createUser(user);
    }

    @GetMapping("/user/{userID}")
    public User getUser(@PathVariable String userId) {
        return userService.getUserByUserid(userId);
    }
}

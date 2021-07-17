package com.example.vaccinebookingapp.controller;

import com.example.vaccinebookingapp.models.User;
import com.example.vaccinebookingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable int userId) {
        return userService.getUserById(userId);
    }

    @PostMapping
    public void insertUser(@RequestBody User user) {
        userService.createOrUpdate(user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userService.deleteUserById(userId);
    }
}

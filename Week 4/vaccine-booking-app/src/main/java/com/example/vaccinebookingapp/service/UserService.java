package com.example.vaccinebookingapp.service;

import com.example.vaccinebookingapp.models.User;
import com.example.vaccinebookingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int userId) {
        return userRepository.findById(userId)
                .orElseThrow();
    }

    public void createOrUpdate(User user) {
        userRepository.save(user);
    }

    public void deleteUserById(int userId) {
        userRepository.deleteById(userId);
    }
}

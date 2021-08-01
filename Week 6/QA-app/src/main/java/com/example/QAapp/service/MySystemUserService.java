package com.example.QAapp.service;

import com.example.QAapp.repository.SystemUserRepository;
import com.example.QAapp.security.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MySystemUserService {

    @Autowired
    SystemUserRepository systemUserRepository;

    public SystemUser findSystemUserByUsername(String username) {
        return systemUserRepository.findByUsername(username)
                .orElseThrow();
    }

    public void createSystemUser(SystemUser systemUser) {
        systemUserRepository.save(systemUser);
    }
}

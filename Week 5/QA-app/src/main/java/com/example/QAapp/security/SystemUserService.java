package com.example.QAapp.security;

import com.example.QAapp.repository.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SystemUserService implements UserDetailsService {

    @Autowired
    SystemUserRepository systemUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return systemUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with username: %s not found", username)));
    }
}

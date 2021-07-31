package com.example.QAapp;

import com.example.QAapp.repository.SystemUserRepository;
import com.example.QAapp.security.SystemUser;
import com.example.QAapp.security.SystemUserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Configuration
public class SystemUserConfig {

    @Autowired
    SystemUserRepository systemUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {

            SystemUser user1 = SystemUser.builder()
                    .username("ajay")
                    .password(passwordEncoder.encode("ajay_pwd"))
                    .role(SystemUserRoles.ADMIN)
                    .isAccountNonExpired(true)
                    .isAccountNonLocked(true)
                    .isCredentialsNonExpired(true)
                    .isEnabled(true)
                    .build();

            SystemUser user2 = SystemUser.builder()
                    .username("raj")
                    .password(passwordEncoder.encode("raj_pwd"))
                    .role(SystemUserRoles.ASSISTANT)
                    .isAccountNonExpired(true)
                    .isAccountNonLocked(true)
                    .isCredentialsNonExpired(true)
                    .isEnabled(true)
                    .build();

            SystemUser user3 = SystemUser.builder()
                    .username("shubham")
                    .password(passwordEncoder.encode("shubham_pwd"))
                    .role(SystemUserRoles.Author)
                    .isAccountNonExpired(true)
                    .isAccountNonLocked(true)
                    .isCredentialsNonExpired(true)
                    .isEnabled(true)
                    .build();

            systemUserRepository.saveAll(Arrays.asList(user1, user2, user3));
        };
    }
}

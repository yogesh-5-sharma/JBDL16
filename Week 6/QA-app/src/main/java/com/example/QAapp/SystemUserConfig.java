package com.example.QAapp;

import com.example.QAapp.models.Author;
import com.example.QAapp.models.Question;
import com.example.QAapp.models.QuestionStatus;
import com.example.QAapp.repository.AuthorRepository;
import com.example.QAapp.repository.QuestionRepository;
import com.example.QAapp.repository.SystemUserRepository;
import com.example.QAapp.security.SystemUser;
import com.example.QAapp.security.SystemUserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;
import java.util.Arrays;

@Configuration
public class SystemUserConfig {

    @Autowired
    SystemUserRepository systemUserRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    QuestionRepository questionRepository;

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
                    .role(SystemUserRoles.AUTHOR)
                    .isAccountNonExpired(true)
                    .isAccountNonLocked(true)
                    .isCredentialsNonExpired(true)
                    .isEnabled(true)
                    .build();

            SystemUser user4 = SystemUser.builder()
                    .username("priya")
                    .password(passwordEncoder.encode("priya_pwd"))
                    .role(SystemUserRoles.AUTHOR)
                    .isAccountNonExpired(true)
                    .isAccountNonLocked(true)
                    .isCredentialsNonExpired(true)
                    .isEnabled(true)
                    .build();

            systemUserRepository.saveAll(Arrays.asList(user1, user2, user3, user4));

            Author author1 = Author.builder()
                    .name("Shubham")
                    .address("India")
                    .dob(Date.valueOf("1990-05-17"))
                    .systemUser(user3)
                    .build();

            Author author2 = Author.builder()
                    .name("Priya")
                    .address("India")
                    .dob(Date.valueOf("1990-05-17"))
                    .systemUser(user4)
                    .build();

            authorRepository.saveAll(Arrays.asList(author1, author2));

            Question question1 = Question.builder()
                    .title("Error in Spring Security")
                    .text("Not Working")
                    .tags("JAva")
                    .status(QuestionStatus.OPEN)
                    .author(author1)
                    .build();

            Question question2 = Question.builder()
                    .title("Error in Spring Redis")
                    .text("Not Working Also")
                    .tags("JAva, Redis")
                    .status(QuestionStatus.OPEN)
                    .author(author2)
                    .build();

            questionRepository.saveAll(Arrays.asList(question1, question2));
        };
    }
}

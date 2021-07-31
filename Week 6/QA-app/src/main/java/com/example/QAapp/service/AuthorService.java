package com.example.QAapp.service;

import com.example.QAapp.models.AuthProvider;
import com.example.QAapp.models.Author;
import com.example.QAapp.repository.AuthorRepository;
import com.example.QAapp.request.CreateAuthorRequest;
import com.example.QAapp.security.SystemUser;
import com.example.QAapp.security.SystemUserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    MySystemUserService systemUserService;

    @Autowired
    PasswordEncoder passwordEncoder;


    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(String username) {
        SystemUser systemUser = systemUserService.findSystemUserByUsername(username);

        return authorRepository.findBySystemUser(systemUser)
                .orElseThrow();
    }


    @Transactional
    public void insertNewUser(CreateAuthorRequest createAuthorRequest) {
        SystemUser systemUser = SystemUser.builder()
                .username(createAuthorRequest.getUsername())
                .password(passwordEncoder.encode(createAuthorRequest.getPassword()))
                .provider(AuthProvider.local)
                .role(SystemUserRoles.Author)
                .isEnabled(true)
                .isCredentialsNonExpired(true)
                .isAccountNonLocked(true)
                .isAccountNonExpired(true)
                .build();

        Author author = Author.builder()
                .name(createAuthorRequest.getName())
                .dob(createAuthorRequest.getDob())
                .address(createAuthorRequest.getAddress())
                .systemUser(systemUser)
                .build();

        authorRepository.save(author);
    }

    @Transactional
    public void deleteAuthorById(String username) {
        SystemUser systemUser = systemUserService.findSystemUserByUsername(username);

        authorRepository.deleteBySystemUser(systemUser);
    }
}

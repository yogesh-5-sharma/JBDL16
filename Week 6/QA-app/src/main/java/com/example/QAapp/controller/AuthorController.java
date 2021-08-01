package com.example.QAapp.controller;

import com.example.QAapp.models.Author;
import com.example.QAapp.request.CreateAuthorRequest;
import com.example.QAapp.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/profile")
    public Author getAuthorById(Principal principal) {
        String username = principal.getName();
        return authorService.getAuthorByUsername(username);
    }

    @PostMapping
    public void createUser(@RequestBody CreateAuthorRequest createAuthorRequest) {
        authorService.insertNewUser(createAuthorRequest);
    }

    @DeleteMapping
    public void deleteAuthor(Principal principal) {
        String username = principal.getName();
        authorService.deleteAuthorById(username);
    }
}

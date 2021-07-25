package com.example.QAapp.controller;

import com.example.QAapp.models.Question;
import com.example.QAapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping
    public List<Question> getQuestionsList(@RequestHeader Map<String, String> headers) {
        System.out.println(headers);
        return questionService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionGivenId(@PathVariable int id, Authentication authentication) {
        System.out.println(authentication);
        return new ResponseEntity<>(questionService.getQuestionById(id), HttpStatus.ACCEPTED);
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = principal.getName();
//        System.out.println(username);
//        Question question = questionService.getQuestionById(id);
//        if (username != question.getAuthorname()) {
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
//        return new ResponseEntity<>(question, HttpStatus.ACCEPTED);
    }
}

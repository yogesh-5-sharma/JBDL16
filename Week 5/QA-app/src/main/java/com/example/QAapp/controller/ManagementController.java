package com.example.QAapp.controller;

import com.example.QAapp.models.Question;
import com.example.QAapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/management")
public class ManagementController {

    @Autowired
    QuestionService questionService;

    @GetMapping("questions")
    public List<Question> getQuestionsList(@RequestHeader Map<String, String> headers) {
        System.out.println(headers);
        return questionService.getAllQuestions();
    }

    @GetMapping("/answer")
    public void getAnswers() {
        System.out.println("Inside Answer");
    }

    @PostMapping("/questions")
    public void postQuestion(@RequestHeader Map<String, String> headers) {
        System.out.println("Inside postmapping of management/questions");
        System.out.println(headers);
    }
}

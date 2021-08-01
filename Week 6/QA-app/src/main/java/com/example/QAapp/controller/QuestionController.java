package com.example.QAapp.controller;

import com.example.QAapp.models.Question;
import com.example.QAapp.models.QuestionStatus;
import com.example.QAapp.request.CreateQuestionRequest;
import com.example.QAapp.request.FlagQuestionRequest;
import com.example.QAapp.request.UpdateQuestionRequest;
import com.example.QAapp.service.QuestionService;
import com.example.QAapp.validators.UpdateQuestionRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.mail.Flags;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping
    public List<Question> getQuestionsList() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public Question getQuestionGivenId(@PathVariable int id) {
        return questionService.getQuestionById(id);
    }

    @PostMapping
    public void postQuestion(@RequestBody Question question, Principal principal) {
        String username = principal.getName();
        questionService.createQuestion(question, username);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable int id, Principal principal) {
        String username = principal.getName();
        questionService.deleteQuestionById(id, username);
    }

    @PutMapping("/{id}")
    public void updateQuestion(@PathVariable int id,
                               @RequestBody Question question,
                               Principal principal) {

        UpdateQuestionRequestValidator.validate(question);

        String username = principal.getName();

        questionService.updateQuestion(id, question, username);

    }

    @PutMapping("/{id}/flag")
    public void flagQuestion(@PathVariable int id, @RequestBody Question question) {
        questionService.flagQuestion(id, question);
    }
}

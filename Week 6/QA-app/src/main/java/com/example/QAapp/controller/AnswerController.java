package com.example.QAapp.controller;

import com.example.QAapp.models.Answer;
import com.example.QAapp.request.CreateAnswerRequest;
import com.example.QAapp.request.UpdateAnswerRequest;
import com.example.QAapp.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @GetMapping("/{id}")
    public Answer getAnswerById(@PathVariable int id) {
        return answerService.getAnswerGivenId(id);
    }

    @PostMapping
    public void postAnswer(@RequestBody CreateAnswerRequest createAnswerRequest, Principal principal) {
        String username = principal.getName();

        answerService.createAnswer(createAnswerRequest, username);
    }

    @PutMapping("/{answerId}")
    public void updateAnswer(@PathVariable int answerId, @RequestBody Answer answer, Principal principal) {
        String username = principal.getName();
        answerService.updateAnswer(answerId, answer, username);
    }

    @DeleteMapping("/{answerId}")
    public void deleteAnswer(@PathVariable int answerId, Principal principal) {
        String username = principal.getName();
        answerService.deleteAnswer(answerId, username);
    }
}

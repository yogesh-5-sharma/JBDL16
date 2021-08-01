package com.example.QAapp.service;

import com.example.QAapp.exceptions.AccessForbiddenException;
import com.example.QAapp.models.Answer;
import com.example.QAapp.models.Author;
import com.example.QAapp.models.Question;
import com.example.QAapp.repository.AnswerRepository;
import com.example.QAapp.request.CreateAnswerRequest;
import com.example.QAapp.request.UpdateAnswerRequest;
import com.example.QAapp.security.SystemUser;
import com.example.QAapp.security.SystemUserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    AuthorService authorService;

    @Autowired
    MySystemUserService mySystemUserService;

    @Autowired
    QuestionService questionService;

    public Answer getAnswerGivenId(int id) {
        return answerRepository.findById(id)
                .orElseThrow();
    }

    public void createAnswer(CreateAnswerRequest createAnswerRequest, String username) {

        Author author = authorService.getAuthorByUsername(username);

        Question question = questionService.getQuestionById(createAnswerRequest.getQuestionId());

        Answer newAnswer = Answer.builder()
                .text(createAnswerRequest.getText())
                .question(question)
                .author(author)
                .build();

        answerRepository.save(newAnswer);
    }

    public void updateAnswer(int answerId, Answer updatedAnswer, String username) {

        Answer answer = getAnswerGivenId(answerId);

        if(!username.equals(answer.getAuthor().getSystemUser().getUsername())) {
            throw new AccessForbiddenException("Username " + username + " is not allowed to update others answer");
        }

        if(updatedAnswer.getText() != null) {
            answer.setText(updatedAnswer.getText());
            answerRepository.save(answer);
        }
    }


    public void deleteAnswer(int answerId, String username) {

        SystemUser systemUser = mySystemUserService.findSystemUserByUsername(username);
        Answer answer = getAnswerGivenId(answerId);

        if(!SystemUserRoles.ADMIN.equals(systemUser.getRole())
                && !    username.equals(answer.getAuthor().getSystemUser().getUsername())) {
            throw new AccessForbiddenException("Username " + username + " is not allowed to deleted others answer");
        }

        answerRepository.deleteById(answerId);
    }
}

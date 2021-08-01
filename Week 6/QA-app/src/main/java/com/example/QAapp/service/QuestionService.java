package com.example.QAapp.service;

import com.example.QAapp.exceptions.AccessForbiddenException;
import com.example.QAapp.models.Author;
import com.example.QAapp.models.Question;
import com.example.QAapp.models.QuestionStatus;
import com.example.QAapp.repository.AuthorRepository;
import com.example.QAapp.repository.QuestionRepository;
import com.example.QAapp.request.CreateQuestionRequest;
import com.example.QAapp.request.FlagQuestionRequest;
import com.example.QAapp.request.UpdateQuestionRequest;
import com.example.QAapp.security.SystemUser;
import com.example.QAapp.security.SystemUserRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;
import java.util.List;

@Service
public class QuestionService {

    private static final Logger logger = LoggerFactory.getLogger(QuestionService.class);

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AuthorService authorService;

    @Autowired
    MySystemUserService mySystemUserService;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question getQuestionById(int id) {
        return questionRepository.findById(id)
                .orElseThrow();
    }

    public void createQuestion(Question question, String username) {

        Author author = authorService.getAuthorByUsername(username);

        Question newQuestion = Question.builder()
                .title(question.getTitle())
                .text(question.getText())
                .tags(question.getTags())
                .author(author)
                .status(QuestionStatus.OPEN)
                .build();

        questionRepository.save(newQuestion);
    }

    public void deleteQuestionById(int id, String username) {

        SystemUser systemUser = mySystemUserService.findSystemUserByUsername(username);

        Question question = getQuestionById(id);

        if(!SystemUserRoles.ADMIN.equals(systemUser.getRole())
                && !username.equals(question.getAuthor().getSystemUser().getUsername())) {
            throw new AccessForbiddenException("Username " + username + " can't update others question");
        }

        questionRepository.deleteById(id);
    }

    public void updateQuestion(int id,
                               Question updatedQuestion,
                               String username) {

        Author author = authorService.getAuthorByUsername(username);

        Question question = getQuestionById(id);

        if(!username.equals(question.getAuthor().getSystemUser().getUsername())) {
            throw new AccessForbiddenException("Username " + username + " can't delete others question");
        }

        question.setTitle(updatedQuestion.getTitle());
        question.setText(updatedQuestion.getText());
        question.setTags(updatedQuestion.getTags());

        questionRepository.save(question);
    }

    public void flagQuestion(int id, Question questionFlag) {
        Question question = getQuestionById(id);
        if(questionFlag.getStatus() != null) {
            question.setStatus(questionFlag.getStatus());
            questionRepository.save(question);
        }
    }
}

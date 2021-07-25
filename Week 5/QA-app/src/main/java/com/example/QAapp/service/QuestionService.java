package com.example.QAapp.service;

import com.example.QAapp.models.Question;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class QuestionService {

    private List<Question> questionList = Arrays.asList(
            new Question(1, "How JVM works", "ajay"),
            new Question(2, "How Python works", "raj"),
            new Question(3, "How C++ execute", "ajay"),
            new Question(4, "How login workds", "shubham")

    );

    public List<Question> getAllQuestions() {
        return questionList;
    }

    public Question getQuestionById(int id) {
        return questionList.stream()
                .filter(question -> question.getId() == id)
                .findFirst()
                .orElseThrow();
    }
}

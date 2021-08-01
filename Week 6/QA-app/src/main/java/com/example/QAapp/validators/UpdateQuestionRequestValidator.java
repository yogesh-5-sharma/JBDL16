package com.example.QAapp.validators;

import com.example.QAapp.models.Question;

public class UpdateQuestionRequestValidator {

    public static void validate(Question request) {
        if(request.getTitle() == null
                || request.getTags() == null
                || request.getText() == null
        ) {
            throw new IllegalArgumentException();
        }
    }
}

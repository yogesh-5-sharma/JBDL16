package com.example.QAapp.request;

import com.example.QAapp.models.QuestionStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlagQuestionRequest {
    private String questionStatus;
}

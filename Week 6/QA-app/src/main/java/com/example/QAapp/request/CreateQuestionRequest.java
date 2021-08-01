package com.example.QAapp.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateQuestionRequest {
    private String text;

    private String title;

    private String tags;
}

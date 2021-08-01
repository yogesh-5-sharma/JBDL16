package com.example.QAapp.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAnswerRequest {

    private String text;

    private Integer questionId;
}

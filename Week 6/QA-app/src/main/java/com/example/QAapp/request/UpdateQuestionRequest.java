package com.example.QAapp.request;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateQuestionRequest {

    @NotNull
    private String text;

    @NotNull
    private String title;

    @NotNull
    private String tags;
}

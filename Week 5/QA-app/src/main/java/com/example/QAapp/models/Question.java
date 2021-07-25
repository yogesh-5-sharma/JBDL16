package com.example.QAapp.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Question {

    private Integer id;
    private String question;
    private String authorname;
}

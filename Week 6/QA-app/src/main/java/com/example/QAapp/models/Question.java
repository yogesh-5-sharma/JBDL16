package com.example.QAapp.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Question {

    @Id
    @GeneratedValue
    private Integer questionId;

    private String text;

    @ManyToOne
    private Author author;

    private String title;

    private String tags;

    @Enumerated(EnumType.STRING)
    private QuestionStatus status;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answerList;
}

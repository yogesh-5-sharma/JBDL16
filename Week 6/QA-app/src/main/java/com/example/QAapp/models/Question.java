package com.example.QAapp.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "questionId"
//)
public class Question {

    @Id
    @GeneratedValue
    private Integer questionId;

    private String text;

    @ManyToOne
    @JsonIgnoreProperties({"authorId", "address", "dob", "systemUser", "questionList", "answerList"})
    private Author author;

    private String title;

    private String tags;

    @Enumerated(EnumType.STRING)
    private QuestionStatus status;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answerList;
}

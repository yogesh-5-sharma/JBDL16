package com.example.QAapp.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Answer {

    @Id
    @GeneratedValue
    private int id;

    private String text;

    @ManyToOne
    private Question question;

    @ManyToOne
    private Author author;
}

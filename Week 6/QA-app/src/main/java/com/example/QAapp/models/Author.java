package com.example.QAapp.models;

import com.example.QAapp.security.SystemUser;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Author {

    @Id
    @GeneratedValue
    private int authorId;

    private String name;

    private String address;

    private Date dob;

    @OneToOne
    private SystemUser systemUser;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Question> questionList;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Answer> answerList;
}

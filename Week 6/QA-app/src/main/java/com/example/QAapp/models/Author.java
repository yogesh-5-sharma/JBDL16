package com.example.QAapp.models;

import com.example.QAapp.security.SystemUser;
import lombok.*;

import javax.persistence.*;
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

    @OneToOne(cascade = CascadeType.ALL)
    private SystemUser systemUser;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Question> questionList;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Answer> answerList;
}

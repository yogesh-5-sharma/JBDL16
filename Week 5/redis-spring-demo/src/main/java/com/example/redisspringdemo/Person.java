package com.example.redisspringdemo;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person implements Serializable {

    private int id;
    private String name;
    private String country;
}

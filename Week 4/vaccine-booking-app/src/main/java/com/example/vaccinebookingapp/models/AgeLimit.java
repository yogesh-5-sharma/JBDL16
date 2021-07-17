package com.example.vaccinebookingapp.models;

import lombok.Getter;

@Getter
public enum AgeLimit {

    AGE0to18(0, 18),
    AGE18to45(18, 45),
    AGE45to60(45, 60),
    AGE0toAbove(0, null),
    AGE60toAbove(60, null),
    AGE18toAbove(18, null),
    AGE45toAbove(45, null);


    Integer minAge; // included
    Integer maxAge; // excluded

    AgeLimit(Integer minAge, Integer maxAge) {
        this.minAge = minAge;
        this.maxAge = maxAge;
    }
}

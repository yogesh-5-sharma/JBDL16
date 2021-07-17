package com.example.vaccinebookingapp.models;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum Vaccine {
    COVISHIELD("Covishield", 100.0, 2, 56),
    COVAXIN("Covaxin", 150.0, 2, 56),
    SPUTNIKV("Sputnik V", 500.0, 1, null);

    String name;
    Double cost;
    Integer doses;
    Integer minDaysOfDifference;

    Vaccine(String name, Double cost, Integer doses, Integer minDaysOfDifference) {
        this.name = name;
        this.cost = cost;
        this.doses = doses;
        this.minDaysOfDifference = minDaysOfDifference;
    }
}

package com.example.vaccinebookingapp.models;

public enum  CompletionStatus {
    PENDING, // future appointment
    COMPLETED, // user came and got the vaccine
    CANCELLED // user booked but didn't came
}

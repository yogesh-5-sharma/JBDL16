package com.example.exceptionhandling;

public class MyException extends ArithmeticException {
    public MyException() {
    }

    public MyException(String message) {
        super(message);
    }
}

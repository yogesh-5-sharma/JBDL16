package org.example.calculator;

public class Calculator {

    int c;

    public int sum(int a, int b) {
        return a+b;
    }

    public int minus(int a, int b) {
        return a-b;
    }

    public int multiply(int a, int b) {
        return a*b;
    }

    public int divide(int a, int b) {
        return a/b;
    }

    public static void main(String[] args) {
        Calculator cal = new Calculator();
        System.out.println(cal.sum(1, 2));
    }
}

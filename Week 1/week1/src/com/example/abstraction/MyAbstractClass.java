package com.example.abstraction;

public abstract class MyAbstractClass {

    public int a=5;
    private int b=10;

    void func1() {
        b =7;
        System.out.println("Abstract Class func1");
    }

    abstract void func2();
}

package com.example.abstraction;

public interface MyInterface {

    // 1. by default public
    // 2. its static
    // 3. its final
    int var1=1;

    void func1();

    void func2();

    default void printSomething() {
        System.out.println("In MyInterface printSomething method");
    }

    // can't override, can't call obj1.func3(), can only only MyInterface.func3()
    static void func3() {
        System.out.println("in func3");
    }
}

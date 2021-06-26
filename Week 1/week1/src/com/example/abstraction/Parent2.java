package com.example.abstraction;

public interface Parent2 {
    void func1();
    default void func5() {
        System.out.println("Parent2 func5");
    }
}

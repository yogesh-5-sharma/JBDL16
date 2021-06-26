package com.example.abstraction;

public interface Parent1 {
    void func1();
    default void func5() {
        System.out.println("Parent1 func5");
    }

    private static void func6() {
        System.out.println("In func6");
    }
}

package com.example.lambdaandstreams;

import java.util.List;
import java.util.function.Consumer;

interface MyInterface {
//    void printSomething(String s, int a);

    int giveSomething(int a, int b);
}

public class LambdaExample {
    public static void main(String[] args) {
//        MyInterface obj = new MyInterface() {
//            @Override
//            public void printSomething(String s) {
//                System.out.println(s + " Hello");
//            }
//        };

        MyInterface obj2 = (abc, s) -> abc+s;

        System.out.println(obj2.getClass());

        System.out.println(obj2.giveSomething(5,6));
    }
}

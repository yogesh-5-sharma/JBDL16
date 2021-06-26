package com.example.singleton;

public class Main {
    public static void main(String[] args) {

//        SingletonClass obj1 = new SingletonClass();
//        SingletonClass obj2 = new SingletonClass();

        SingletonClass obj1 = SingletonClass.getInstance();
        SingletonClass obj2 = SingletonClass.getInstance();

        System.out.println(obj1);
        System.out.println(obj2);
    }
}


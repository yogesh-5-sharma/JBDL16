package com.example.singleton;

public class SingletonClass {
    public int a = 5;

    static SingletonClass instance = null;

    private SingletonClass() {

    }

    public static SingletonClass getInstance() {
        if(instance==null) {
            instance = new SingletonClass();
        }

        return instance;
    }
}

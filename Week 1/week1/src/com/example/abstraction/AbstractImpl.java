package com.example.abstraction;

public class AbstractImpl extends MyAbstractClass {
    @Override
    void func2() {
        System.out.println("func2 Impl");
    }

    public static void main(String[] args) {
        AbstractImpl obj = new AbstractImpl();
        obj.func1();
        obj.func2();
        obj.a=1;
    }
}

// extends one class in Abstract, multiple interfaces
// default variable in interface have special properties, not the case in abstract class
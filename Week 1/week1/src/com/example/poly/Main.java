package com.example.poly;

class A {
    public void func1() {
        System.out.println("In a");
    }
}

class B extends A {
    @Override
    public void func1() {
        System.out.println("In b");
    }
}

class C extends A {
    @Override
    public void func1() {
        System.out.println("In c");
    }
}

public class Main {
    public static void main(String[] args) {
        A ref;

        A a = new A();
        B b = new B();
        C c = new C();

        ref = a;
        ref.func1();

        ref=b;
        ref.func1();

        ref=c;
        ref.func1();
    }
}

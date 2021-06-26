package com.example.inheritance;

public class ParentClass {
    public int var1=1;
    private int var2;
    ParentClass parentObject;

    public ParentClass(int a, int b) {
        var1=a;
        var2=b;
        System.out.println("In ParentClass Constructor");
    }

    public void func1() {
        System.out.println("ParentClass func1");
    }

    private void func2() {
        System.out.println("ParentClass func2");
    }
}

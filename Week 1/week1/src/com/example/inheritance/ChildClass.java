package com.example.inheritance;

public class ChildClass extends ParentClass {
    int var3;
    public int var1;

    public ChildClass(int a, int b) {
        // 2. to call parent class constructor
        super(a,b);
        this.var1=10;
        System.out.println("In ChildClass constructor");
    }

    @Override
    public void func1() {
        // 1 call the parent class methods/variables;
        super.var1=1;
//        super.func1();
        System.out.println("ChildClass func1");
    }

    public void func3() {
//        parentObject.var1=1;
        System.out.println("ChildClass func3");
    }

    public void printSomething() {
        System.out.println(var1);
        System.out.println(super.var1);
    }
}

package com.example.finalkeyword;

// 1. variable : declaring, constructor, initializer block
// 2. method : can;t override this method
// 3. class : any other class can't extend this final class

public class ParentClass {
    final int a;

//    static {
//        a=7;
//    }

//    {
//        a =6;
//    }

    public ParentClass(int a) {
        this.a = a;
    }

    final public void print() {
        System.out.println("in print of parent");
    }

    public void print2() {
        System.out.println("in print of parent");
    }

    public static void main(String[] args) {
        ParentClass obj = new ParentClass(5);
//        obj.a = 6;
    }
}

class ChildClass extends ParentClass {

    @Override
    public void print2() {
        super.print2();
    }
//    @Override
//    public void print() {
//        System.out.println("something");
//    }


    public ChildClass(int a) {
        super(a);
    }
}
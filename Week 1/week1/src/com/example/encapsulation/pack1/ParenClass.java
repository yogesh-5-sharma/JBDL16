package com.example.encapsulation.pack1;

public class ParenClass {
    private int var1; // inside ParentClass
    int var2; // inside same package
    protected int var3; // defalut + class is inheriting the ParentClass
    public int var4; // everywhere

    public static void main(String[] args) {
        ParenClass obj1 = new ParenClass();

        obj1.var1=1;
        obj1.var2=1;
        obj1.var3=1;
        obj1.var4=1;
    }
}

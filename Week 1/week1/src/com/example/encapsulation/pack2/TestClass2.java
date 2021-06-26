package com.example.encapsulation.pack2;

import com.example.encapsulation.pack1.ParenClass;

public class TestClass2 extends ParenClass {
    public static void main(String[] args) {
        ParenClass obj = new ParenClass();

//        obj.var2=1;

//        obj.var3;

        TestClass2 obj2 = new TestClass2();
//        obj2.var2;
        obj2.var3=5;
    }
}

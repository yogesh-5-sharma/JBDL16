package com.example.poly;

public class TestClass {

    public int sum(int a,int b){
        return a+b;
    }


//    public String sum(int a,int b){
//        return "abc";
//    }

    public int sum(int a,int b, int c){
        return a+b;
    }

    public void print(int a,int b){
        System.out.println(a+ " " + b);
    }

    public void print(int a,String b){
        System.out.println(a+ " " + b);
    }

    public static void main(String[] args) {
        TestClass obj = new TestClass();
        obj.print(5,6);
        obj.print(5,"abc");
    }
}

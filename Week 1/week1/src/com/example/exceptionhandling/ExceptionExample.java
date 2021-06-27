package com.example.exceptionhandling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExceptionExample {
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Operations 1");

//        func1();
//        divide(2);

//        try {
//            sum(1, 2);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        sum(1,2);

        tryWithResources();

//        System.gc();

        System.out.println("Operations 2");
    }

    public static void tryWithResources() throws Exception {
//        UserResource userResource = new UserResource();

//        try {
//
//        } catch (Exception e) {
//
//        } finally {
//
//        }


//        try(
//                UserResource userResource = new UserResource();
////                FileInputStream fileInputStream = new FileInputStream("sample.txt");
//                ) {
//
//            userResource.driver();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        UserResource userResource = new UserResource();
        System.gc();
        userResource.driver();

//        try(UserResource userResource = new UserResource();
//        FileInputStream fis = new FileInputStream("sample.txt")) {
//            userResource.driver();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public static void func1() {
        UserResource userResource = new UserResource();

        try {
            return;
//            int a =  100/0;
//            return;
//            userResource.driver();
        } catch (ArithmeticException e) {
//            throw e;
            e.printStackTrace();
            System.out.println("In catch block");
        } finally {
            userResource = null;
            System.out.println("In finally");
        }

//        System.out.println("in func1" + userResource);
    }

    //throw simply throw-back an exception to calling method
    public static void sum(int a, int b) throws IOException {
        int num = a+b;
        throw new MyException("Random Exception");
    }

    public static void divide(int a) {

//        try {
//            File file = null;
//            file.createNewFile();
//        } catch () {



        try{
            int num = 100 / a;
            System.out.println(num);
        } catch (ArithmeticException e) {
            e.printStackTrace();
            throw new NullPointerException();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}

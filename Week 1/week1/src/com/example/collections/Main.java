package com.example.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {
//        ArrayList<String> arr = new ArrayList<>();
//
//        arr.add("ABC");
//        arr.add("DEF");
//        arr.add("ABC");
//        arr.add("XYZ");
//
//        System.out.println(arr);
//
//        arr.add(3, "PQR");
//        System.out.println(arr);
//
//        arr.remove("ABC");
//        System.out.println(arr);
//
//        arr.remove(2);
//        System.out.println(arr);

        ArrayList<Integer> arr = new ArrayList<>();

        arr.add(1);
        arr.add(2);
        arr.add(3);

        arr.remove(new Integer(2));

//        System.out.println(arr);
//
//        ListIterator<Integer> it = arr.listIterator(arr.size());
//
//        while(it.hasPrevious()) {
//            System.out.println(it.previous());
//        }

        arr.forEach(System.out::println);

    }
}

package com.example.multithreading;

import java.util.stream.IntStream;

public class Thread1 extends Thread {
    @Override
    public void run() {
        IntStream.range(0,10)
                .forEach(i -> {
                    System.out.println("In thread1");
                });

//        Thread thread2 = new Thread(new Thread2());
//        thread2.
    }
}

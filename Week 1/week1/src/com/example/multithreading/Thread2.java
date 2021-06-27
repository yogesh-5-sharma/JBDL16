package com.example.multithreading;

import java.util.stream.IntStream;

public class Thread2 implements Runnable {
    @Override
    public void run() {
        IntStream.range(0,10)
                .forEach(i -> {
                    System.out.println("In thread2");
                });
    }
}

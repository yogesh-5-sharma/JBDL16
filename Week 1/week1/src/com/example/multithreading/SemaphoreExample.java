package com.example.multithreading;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SemaphoreExample {
    public static void main(String[] args) {
        Thread[] threads = new Thread[10];

        IntStream.range(0,10)
                .forEach(i -> {
                    threads[i] = new ReadThread();
                });

        Arrays.stream(threads).forEach(thread -> thread.start());

        Arrays.stream(threads).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


    }
}

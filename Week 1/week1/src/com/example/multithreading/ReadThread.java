package com.example.multithreading;

public class ReadThread extends Thread {
    @Override
    public void run() {
        try {
            SharedSemaphore.semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(this.getName() + " is reading the shared resource");
        System.out.println(Shared.arr.length);

        SharedSemaphore.semaphore.release();
    }
}

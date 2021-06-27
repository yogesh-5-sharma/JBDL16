package com.example.multithreading;

import java.util.concurrent.Semaphore;

public class SharedSemaphore {
    public static Semaphore semaphore = new Semaphore(3);
}

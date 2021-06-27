package com.example.multithreading;

import java.security.Principal;

public class OddEvenPrinting {
    public static void main(String[] args) throws InterruptedException {
        Printer printer = new Printer();

        Thread oddThread = new OddEvenThread(printer, 5, "ODD");
        Thread evenThread = new OddEvenThread(printer, 5, "EVEN");

        oddThread.start();
        evenThread.start();

        oddThread.join();
        evenThread.join();
    }
}

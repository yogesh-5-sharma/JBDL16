package com.example.multithreading;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        BankAccount account1 = new BankAccount("ABC", 350);
        BankAccount account2 = new BankAccount("DEF", 350);


        BankAccountThread[] threads = new BankAccountThread[6];

        threads[0] = new BankAccountThread(account1, 100);
        threads[1] = new BankAccountThread(account1, 50);
        threads[2] = new BankAccountThread(account1, 10);

        threads[3] = new BankAccountThread(account2, 100);
        threads[4] = new BankAccountThread(account2, 50);
        threads[5] = new BankAccountThread(account2, 10);

        Arrays.stream(threads)
                .forEach(thread -> thread.start());

        Arrays.stream(threads)
                .forEach(thread -> {
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });

        // 510
        System.out.println(account1.getBalance());
        System.out.println(account2.getBalance());


//        Thread thread1 = new Thread1();
//
//        Thread thread2 = new Thread(new Thread2());

//        System.out.println(thread1.getId());
//        System.out.println(thread1.getName());
//        System.out.println(thread1.getPriority());

//        thread1.setPriority(6);
//        thread1.setName("my own thread");

//        System.out.println(thread1.getId());
//        System.out.println(thread1.getName());
//        System.out.println(thread1.getPriority());

//        System.out.println(Thread.currentThread());
//        thread1.start();
//        thread2.start();
//
//        System.out.println(thread1.getState());
//
//        thread1.join(100);
//        thread2.join();
//
//        System.out.println(thread1.getState());
//
////        Thread.yield();
//
//        System.out.println("In main");
    }
}

package com.example.multithreading;

public class Printer {

    private boolean isOddPrinted;

    public Printer() {
        this.isOddPrinted = false;
    }

    public synchronized void printOdd(int num, String name) {
        if(isOddPrinted==true) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(name + " is printing " + num);
        isOddPrinted=true;
        notify();
    }

    public synchronized void printEven(int num, String name) {
        if(isOddPrinted==false) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(name + " is printing " + num);
        isOddPrinted=false;
        notify();
    }
}

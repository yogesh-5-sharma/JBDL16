package com.example.multithreading;

public class OddEvenThread extends Thread {

    private final Printer printer;
    private final int count;
    private final String threadType;

    public OddEvenThread(Printer printer, int count, String threadType) {
        this.printer = printer;
        this.count = count;
        this.threadType = threadType;
    }

    @Override
    public void run() {
        for(int i=0;i<count;++i) {
            if(threadType.equals("ODD")) {
                printer.printOdd(2*i+1, threadType);
            } else {
                printer.printEven(2*i+2, threadType);
            }
        }
    }
}

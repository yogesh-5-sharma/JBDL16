package com.example.multithreading;

public class BankAccountThread extends Thread {

    private final BankAccount account;
    private final int amount;

    public BankAccountThread(BankAccount account, int amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void run() {
        bankTransfer();
    }

    private void bankTransfer() {
        synchronized (BankAccount.class) {
            System.out.println(account.getName() + " " + this.getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account.setBalance(account.getBalance() + amount);
        }
    }
}

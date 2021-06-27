package com.example.exceptionhandling;

public class UserResource implements AutoCloseable {

    public void driver() {
        System.out.println("In UserResource Driver");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("In finalize");
        super.finalize();
    }

    @Override
    public void close() throws Exception {
        System.out.println("In close method");
    }
}

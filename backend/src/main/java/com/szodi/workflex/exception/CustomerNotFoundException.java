package com.szodi.workflex.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException() {
        super("Customer not found!");
    }

    public CustomerNotFoundException(String msg) {
        super(msg);
    }

    public CustomerNotFoundException(String msg, Throwable e) {
        super(msg, e);
    }
}

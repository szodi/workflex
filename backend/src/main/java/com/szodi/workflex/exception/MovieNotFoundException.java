package com.szodi.workflex.exception;

public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException() {
        super("Movie not found!");
    }

    public MovieNotFoundException(String msg) {
        super(msg);
    }

    public MovieNotFoundException(String msg, Throwable e) {
        super(msg, e);
    }
}

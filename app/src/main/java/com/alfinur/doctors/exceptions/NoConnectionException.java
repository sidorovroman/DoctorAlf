package com.alfinur.doctors.exceptions;

public class NoConnectionException extends Exception {

    public NoConnectionException() {

    }

    public NoConnectionException(String message) {
        super(message);
    }

    public NoConnectionException(Throwable throwable) {
        super(throwable);
    }
}
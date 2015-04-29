package com.alfinur.doctors.exceptions;

public class UnknownServerException extends Exception {

    public UnknownServerException() {

    }

    public UnknownServerException(String message) {
        super(message);
    }

    public UnknownServerException(Throwable throwable) {
        super(throwable);
    }
}
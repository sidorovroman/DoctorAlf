package com.alfinur.doctors.exceptions;

public class NoHttpResponseException extends Exception {

    public NoHttpResponseException() {

    }

    public NoHttpResponseException(String message) {
        super(message);
    }

    public NoHttpResponseException(Throwable throwable) {
        super(throwable);
    }
}
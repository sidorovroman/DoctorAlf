package com.alfinur.doctors.exceptions;

public class AccessDeniedException extends Exception {

    public AccessDeniedException() {

    }

    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(Throwable throwable) {
        super(throwable);
    }
}
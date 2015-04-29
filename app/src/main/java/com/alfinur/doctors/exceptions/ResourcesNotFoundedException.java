package com.alfinur.doctors.exceptions;

public class ResourcesNotFoundedException extends Exception {

    public ResourcesNotFoundedException() {

    }

    public ResourcesNotFoundedException(String message) {
        super(message);
    }

    public ResourcesNotFoundedException(Throwable throwable) {
        super(throwable);
    }
}
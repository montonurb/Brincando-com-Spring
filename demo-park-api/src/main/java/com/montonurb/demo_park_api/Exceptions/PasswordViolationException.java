package com.montonurb.demo_park_api.Exceptions;

public class PasswordViolationException extends RuntimeException {
    public PasswordViolationException(String message) {
        super(message);
    }
}

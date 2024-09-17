package com.montonurb.demo_park_api.Exceptions;

public class UsernameUniqueViolationException extends RuntimeException {
    public UsernameUniqueViolationException(String message) {
        super(message);
    }
}

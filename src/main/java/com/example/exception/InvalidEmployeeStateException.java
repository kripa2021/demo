package com.example.exception;

public class InvalidEmployeeStateException extends RuntimeException {

    public InvalidEmployeeStateException(String message) {
        super(message);
    }
}

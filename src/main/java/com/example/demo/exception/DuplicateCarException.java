package com.example.demo.exception;

public class DuplicateCarException extends RuntimeException {

    public DuplicateCarException(String message) {
        super(message);
    }
}

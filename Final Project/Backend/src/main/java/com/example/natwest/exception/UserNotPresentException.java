package com.example.natwest.exception;

public class UserNotPresentException extends Exception {
    public UserNotPresentException(String message) {
        super(message);
    }
}

package com.example.natwest.exception;

public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException(String message){
        super(message);
    }
}

package com.stackroute.jwtdemo.exception;

public class UserAlreadyExistsException  extends Exception{
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}

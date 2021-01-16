package com.stackroute.springmongodb.exception;

public class EmployeeAlreadyExistsException extends Exception {

    public EmployeeAlreadyExistsException(String message) {
        super(message);
    }
}

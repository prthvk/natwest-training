package com.stackroute.employeemongo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code= HttpStatus.CONFLICT, reason ="Employee Already Exists")
public class EmployeeExistsException  extends Exception{

    public EmployeeExistsException(String msg) {
        super(msg);
    }
}

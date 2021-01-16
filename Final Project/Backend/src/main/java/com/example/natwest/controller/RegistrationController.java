package com.example.natwest.controller;

import com.example.natwest.domain.User;
import com.example.natwest.exception.UserAlreadyExistsException;
import com.example.natwest.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/registrationservice")
public class RegistrationController {

    private IUserService userService;
    private ResponseEntity responseEntity;

    @Autowired
    public RegistrationController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        try {
            User createdUser = userService.saveUser(user);
            responseEntity = new ResponseEntity(createdUser, HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            System.out.println(e);
            responseEntity = new ResponseEntity("Some Internal Error Try after some Time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}

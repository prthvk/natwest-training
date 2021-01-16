package com.stackroute.jwtdemo.service;

import com.stackroute.jwtdemo.entity.User;
import com.stackroute.jwtdemo.exception.UserAlreadyExistsException;
import com.stackroute.jwtdemo.exception.UserNotFoundException;

import java.util.List;

public interface IUserService {

    List<User> getAllUsers();

    boolean validateUser(String username, String password) throws UserNotFoundException;

    User CreateUser(User user) throws UserAlreadyExistsException;
}
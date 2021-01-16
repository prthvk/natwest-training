package com.stackroute.jwtdemo.controller;


import com.stackroute.jwtdemo.entity.User;
import com.stackroute.jwtdemo.exception.UserAlreadyExistsException;
import com.stackroute.jwtdemo.service.IUserService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController

public class UserController {

    IUserService userService;

    ResponseEntity responseEntity;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity saveUser(@RequestBody User user) {

        try {
            User createdUser = userService.CreateUser(user);
            responseEntity = new ResponseEntity(createdUser , HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity(e.getMessage() , HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("/api/v1/userservice/users")
    public ResponseEntity getAllUsers(HttpServletRequest request){

      String authheader=  request.getHeader("Authorization");
     String token =  authheader.substring(7);
      String username =   Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();

      String username2 = (String) request.getAttribute("username");
      //movie.setuser(username2);
        //movieService.saveMovie (username2 , movie)
      List<User> list =  userService.getAllUsers();
      responseEntity = new ResponseEntity(list,HttpStatus.OK);
      return responseEntity;

    }
}

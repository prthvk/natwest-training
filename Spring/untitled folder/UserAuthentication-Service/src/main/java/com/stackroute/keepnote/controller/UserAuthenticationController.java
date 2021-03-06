package com.stackroute.keepnote.controller;


import com.stackroute.keepnote.model.User;
import com.stackroute.keepnote.service.UserAuthenticationService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
 * As in this assignment, we are working on creating RESTful web service, hence annotate
 * the class with @RestController annotation. A class annotated with the @Controller annotation
 * has handler methods which return a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized
 * format. Starting from Spring 4 and above, we can use @RestController annotation which
 * is equivalent to using @Controller and @ResposeBody annotation
 */

@RestController
//@RequestMapping("/authService")
public class UserAuthenticationController {

    /*
     * Autowiring should be implemented for the UserAuthenticationService. (Use Constructor-based
     * autowiring) Please note that we should not create an object using the new
     * keyword
     */

    private Map<String, String> map = new HashMap<>();
    private UserAuthenticationService userAuthenticationService;

    @Autowired
    public UserAuthenticationController(UserAuthenticationService authicationService) {
        this.userAuthenticationService = authicationService;
    }

    /*
     * Define a handler method which will create a specific user by reading the
     * Serialized object from request body and save the user details in the
     * database. This handler method should return any one of the status messages
     * basis on different situations:
     * 1. 201(CREATED) - If the user created successfully.
     * 2. 409(CONFLICT) - If the userId conflicts with any existing user
     *
     * This handler method should map to the URL "/api/v1/auth/register" using HTTP POST method
     */

    @PostMapping("/api/v1/auth/register")
    public ResponseEntity userRegister(@RequestBody User user) {
        try {
            String jwtToken = getToken1(user);
            map.put("message", "User successfully logged in");
            map.put("token", jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", e.getMessage());
            map.put("token", null);
            return new ResponseEntity(map, HttpStatus.CONFLICT);
        }
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    /* Define a handler method which will authenticate a user by reading the Serialized user
     * object from request body containing the username and password. The username and password should be validated
     * before proceeding ahead with JWT token generation. The user credentials will be validated against the database entries.
     * The error should be return if validation is not successful. If credentials are validated successfully, then JWT
     * token will be generated. The token should be returned back to the caller along with the API response.
     * This handler method should return any one of the status messages basis on different
     * situations:
     * 1. 200(OK) - If login is successful
     * 2. 401(UNAUTHORIZED) - If login is not successful
     *
     * This handler method should map to the URL "/api/v1/auth/login" using HTTP POST method
     */

    @PostMapping("/api/v1/auth/login")
    public ResponseEntity userLogin(@RequestBody User user) {
        try {
            String jwtToken = getToken2(user.getUserId(), user.getUserPassword());
            map.put("message", "User successfully logged in");
            map.put("token", jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", e.getMessage());
            map.put("token", null);
            return new ResponseEntity(map, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(map, HttpStatus.OK);
    }

    // Generate JWT token
    public String getToken2(String userId, String password) throws Exception {
        String jwtToken = "";
        if (userId == null || password == null) {
            throw new Exception("Please send valid username or password");
        }
        User user = userAuthenticationService.findByUserIdAndPassword(userId, password);
        if (user == null) {
            throw new Exception("Invalid credentials");
        } else {
            jwtToken = Jwts.builder()
                    .setSubject(userId)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 300000))
                    .signWith(SignatureAlgorithm.HS256, "secretkey")
                    .compact();
        }
        return jwtToken;
    }

    public String getToken1(User user) throws Exception {
        String jwtToken = "";
        if (user == null) {
            throw new Exception("Please send valid username or password");
        }
        try {
            Boolean flag = userAuthenticationService.saveUser(user);
            jwtToken = Jwts.builder()
                    .setSubject(user.getUserId())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 300000))
                    .signWith(SignatureAlgorithm.HS256, "secretkey")
                    .compact();
            return jwtToken;
        } catch (Exception e) {
            throw new Exception("Invalid credentials");
        }
    }
}

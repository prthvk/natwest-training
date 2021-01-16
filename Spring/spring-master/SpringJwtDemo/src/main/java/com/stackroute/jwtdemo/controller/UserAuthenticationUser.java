package com.stackroute.jwtdemo.controller;


import com.stackroute.jwtdemo.entity.User;
import com.stackroute.jwtdemo.service.IUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/authService")
public class UserAuthenticationUser {

    IUserService userService;
    private Map<String, String> map = new HashMap<>();

    @Autowired
    public UserAuthenticationUser(IUserService userService) {
        this.userService = userService;
    }


    @PostMapping("/login")
    public ResponseEntity userLogin(@RequestBody User user) {
        try {
            String jwtToken = generateToken(user.getUsername(), user.getPassword());
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

    private String generateToken(String username, String password) throws Exception {
        String jwtToken = "";
        if (username == null || password == null) {
            throw new Exception("Please send valid username or password");
        }
        boolean flag = userService.validateUser(username, password);
        if (!flag) {
            throw new Exception("Invalid credentials");
        } else {
            jwtToken = Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 300000))
                    .signWith(SignatureAlgorithm.HS256, "secretkey")
                    .compact();
        }
        return jwtToken;
    }
}

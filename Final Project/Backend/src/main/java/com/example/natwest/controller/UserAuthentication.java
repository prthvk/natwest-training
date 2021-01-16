package com.example.natwest.controller;

import com.example.natwest.domain.User;
import com.example.natwest.service.IUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/authService")

public class UserAuthentication {
    IUserService userService;
    private Map<String,String> map  =  new HashMap<>();

    @Autowired
    public UserAuthentication(IUserService userService){
        this.userService=userService;
    }

    @PostMapping("/login")
    public ResponseEntity userLogin(@RequestBody User user){

        try {
            String jwtToken = generateToken(user.getUsername(),user.getUserpassword());
            map.put("message","User Successfully Logged In");
            map.put("token",jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message",e.getMessage());
            map.put("token",null);
            return new ResponseEntity(map, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(map,HttpStatus.OK);
    }

    private String generateToken(String username,String password) throws Exception {
        String jwtToken="";
        if(username==null || password==null){
            throw new Exception("Please send valid username and password");
        }
        boolean flag = userService.validateUser(username,password);
        if(!flag){
            throw new Exception("Invalid Credentials");
        }else{
            jwtToken = Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+300000))
                    .signWith(SignatureAlgorithm.HS256,"secretkey").compact();
        }
        return jwtToken;
    }

}

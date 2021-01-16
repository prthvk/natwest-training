package com.example.natwest.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class User {

    @Id
    private String userId;
    private String username;
    private String userpassword;
    private List<Card> userCardsList;

    public User() {
    }

    public User(String userId, String username, String userpassword, List<Card> userCardsList) {
        this.userId = userId;
        this.username = username;
        this.userpassword = userpassword;
        this.userCardsList = userCardsList;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + username + '\'' +
                ", userPassword='" + userpassword + '\'' +
                ", userCardsList=" + userCardsList +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public List<Card> getUserCardsList() {
        return userCardsList;
    }

    public void setUserCardsList(List<Card> userCardsList) {
        this.userCardsList = userCardsList;
    }

}

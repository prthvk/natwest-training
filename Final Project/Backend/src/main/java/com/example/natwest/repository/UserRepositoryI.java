package com.example.natwest.repository;

import com.example.natwest.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface UserRepositoryI extends MongoRepository<User,String> {
    public User findByUsernameAndUserpassword(String username,String password);
    public Optional<User> findByUsername(String username);
}

package com.stackroute.jwtdemo.service;

import com.stackroute.jwtdemo.entity.User;
import com.stackroute.jwtdemo.exception.UserAlreadyExistsException;
import com.stackroute.jwtdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {


    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean validateUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            return true;
        }
        return false;
    }

    @Override
    public User CreateUser(User user) throws UserAlreadyExistsException {
        Optional optional = userRepository.findById(user.getId());

        User user1 = null;
        if (optional.isPresent()) {
            throw new UserAlreadyExistsException("User Already exixts");
        } else {
            user1 = userRepository.save(user);
        }
        return user1;

    }
}

package com.example.natwest.service;

import com.example.natwest.domain.Card;
import com.example.natwest.domain.Transaction;
import com.example.natwest.domain.User;
import com.example.natwest.exception.CardNotFoundException;
import com.example.natwest.exception.UserAlreadyExistsException;
import com.example.natwest.exception.UserNotPresentException;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    public User saveUser(User user) throws UserAlreadyExistsException;
    public List<User> getAllUsers();
    public User getUser(String username) throws UserNotPresentException;
    public List<Transaction> getAllTransactionsOfCard(Card card);
    public Card getCard(String cardNumber,User user) throws CardNotFoundException;
    public List<Transaction> getTransactionsWithDate(Card card,String fromDate,String toDate);
    public User updateUser(User user);
    public boolean validateUser(String username,String password);
    public User getUserById(String userId) throws UserNotPresentException;
}

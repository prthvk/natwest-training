package com.example.natwest.service;

import com.example.natwest.domain.Card;
import com.example.natwest.domain.Transaction;
import com.example.natwest.domain.User;
import com.example.natwest.exception.CardNotFoundException;
import com.example.natwest.exception.UserAlreadyExistsException;
import com.example.natwest.exception.UserNotPresentException;
import com.example.natwest.repository.UserRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class UserService implements  IUserService{

    private UserRepositoryI userRepository;

    @Autowired
    public UserService(UserRepositoryI userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        Optional<User> optional = userRepository.findById(user.getUserId());
        if(optional.isPresent()){
            throw new UserAlreadyExistsException("User Already Exists");
        }
        User createdUser = userRepository.save(user);
        return  createdUser;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String username) throws UserNotPresentException {
//        Optional<User> optional = userRepository.findByUsername(username);
            Optional<User> optional =this.userRepository.findByUsername(username);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new UserNotPresentException("User Not Found");
    }
    @Override
    public User getUserById(String userId) throws UserNotPresentException {
//        Optional<User> optional = userRepository.findByUsername(username);
        Optional<User> optional =this.userRepository.findById(userId);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new UserNotPresentException("User Not Found");
    }

    @Override
    public List<Transaction> getAllTransactionsOfCard(Card card) {
        return card.getTransactionList();
    }
    @Override
    public List<Transaction> getTransactionsWithDate(Card card,String fromDate,String toDate){

        List<Transaction> transactionList = card.getTransactionList();
        List<Transaction> transactionListBetweenDates = new ArrayList<>();
        for(int i=0;i<transactionList.size();i++){
            Transaction currentTransaction = transactionList.get(i);
            try {
                Date currentTransactionDate = new SimpleDateFormat("dd-MM-yyyy").parse(currentTransaction.getTransactionDate());
                Date fromDateFormat = new SimpleDateFormat("dd-MM-yyyy").parse(fromDate);
                Date toDateFormat = new SimpleDateFormat("dd-MM-yyyy").parse(toDate);
                if(currentTransactionDate.after(fromDateFormat) && currentTransactionDate.before(toDateFormat)){
                    transactionListBetweenDates.add(currentTransaction);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
            return transactionListBetweenDates;
    }

    @Override
    public Card getCard(String cardNumber,User user) throws CardNotFoundException {
        Card card =null;
        List<Card> cardList= user.getUserCardsList();
        for (int i=0;i<cardList.size();i++){
            if(cardNumber.equals(cardList.get(i).getCardNumber())){
                card= cardList.get(i);
            }
        }
        if(card!=null){
            return card;
        }
        throw new CardNotFoundException("Card Not Found");
    }

    @Override
    public User updateUser(User user) {
         return this.userRepository.save(user);
    }

    @Override
    public boolean validateUser(String username, String password)  {
        User user = this.userRepository.findByUsernameAndUserpassword(username,password);
        if(user!=null){
            return true;
        }else{
            return false;
        }
    }
}

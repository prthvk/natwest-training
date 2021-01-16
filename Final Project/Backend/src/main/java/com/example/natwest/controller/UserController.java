package com.example.natwest.controller;


import com.example.natwest.domain.Card;
import com.example.natwest.domain.Transaction;
import com.example.natwest.domain.User;
import com.example.natwest.exception.CardNotFoundException;
import com.example.natwest.exception.UserAlreadyExistsException;
import com.example.natwest.exception.UserNotPresentException;
import com.example.natwest.service.IUserService;
import com.example.natwest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/userservice")
public class UserController {

    private IUserService userService;
    private ResponseEntity responseEntity;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity getAllUsers() {
        try {
            List<User> userList = userService.getAllUsers();
            responseEntity = new ResponseEntity(userList, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            responseEntity = new ResponseEntity("Some internal Server Error Try after some time.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @GetMapping("/users/{userName}")
    public ResponseEntity getUser(@PathVariable("userName") String userName) {
        try {
            User user = this.userService.getUser(userName);
            responseEntity = new ResponseEntity(user, HttpStatus.OK);
        } catch (UserNotPresentException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Some Internal Error Try after some Time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/cards/{userId}")
    public ResponseEntity getAllCards(@PathVariable("userId") String userId) {
        try {
            User user = this.userService.getUser(userId);
            List<Card> cardList = user.getUserCardsList();
            responseEntity = new ResponseEntity(cardList, HttpStatus.OK);
        } catch (UserNotPresentException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Some Internal Error Try after some Time", HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return responseEntity;

    }

    @GetMapping("/cards/{userId}/{cardNumber}")
    public ResponseEntity getCard(@PathVariable("userId") String userId, @PathVariable("cardNumber") String cardNumber) {

        try {
            User user = this.userService.getUser(userId);
            Card card = this.userService.getCard(cardNumber, user);
            responseEntity = new ResponseEntity(card, HttpStatus.OK);
        } catch (UserNotPresentException | CardNotFoundException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Some Internal Error Try after some Time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/transactions/{userId}/{cardNumber}")
    public ResponseEntity getTransactions(@PathVariable("userId") String userId, @PathVariable("cardNumber") String cardNumber) {


        try {
            User user = this.userService.getUserById(userId);
            Card card = this.userService.getCard(cardNumber, user);
            responseEntity = new ResponseEntity(card.getTransactionList(), HttpStatus.OK);
        } catch (UserNotPresentException | CardNotFoundException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Some Internal Error Try after some Time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("transactions/{userId}/{cardNumber}/{fromDate}/{toDate}")
    public ResponseEntity getTrasactionsWithinDates(@PathVariable("userId") String userId, @PathVariable("cardNumber") String cardNumber, @PathVariable("fromDate") String fromDate, @PathVariable("toDate") String toDate) {
        try {
            User user = this.userService.getUserById(userId);
            Card card = this.userService.getCard(cardNumber, user);

            responseEntity = new ResponseEntity(this.userService.getTransactionsWithDate(card, fromDate, toDate), HttpStatus.OK);
        } catch (UserNotPresentException | CardNotFoundException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Some Internal Error Try after some Time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("card/status/{userId}/{cardNumber}/{isActivated}")
    public ResponseEntity setStatusOfCard(@PathVariable("userId") String userId, @PathVariable("cardNumber") String cardNumber, @PathVariable("isActivated") String isActivated) {
        try {
            User user = this.userService.getUserById(userId);
            Card card = this.userService.getCard(cardNumber, user);
            card.setIsActivated(isActivated);
            for (int i = 0; i < user.getUserCardsList().size(); i++) {
                Card currentCard = user.getUserCardsList().get(i);
                if (currentCard.getCardNumber().equals(cardNumber)) {
                    user.getUserCardsList().remove(i);
                    user.getUserCardsList().add(card);
                    break;
                }
            }
            user = this.userService.updateUser(user);
            responseEntity = new ResponseEntity(user, HttpStatus.OK);
        } catch (UserNotPresentException | CardNotFoundException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Some Internal Error Try after some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("card/limit/{userId}/{cardNumber}/{debitLimit}")
    public ResponseEntity setLimit(@PathVariable("userId") String userId, @PathVariable("cardNumber") String cardNumber, @PathVariable("debitLimit") Double debitLimit) {
        try {
            User user = this.userService.getUserById(userId);
            Card card = this.userService.getCard(cardNumber, user);
            card.setDailyLimit(debitLimit);
            for (int i = 0; i < user.getUserCardsList().size(); i++) {
                Card currentCard = user.getUserCardsList().get(i);
                if (currentCard.getCardNumber().equals(cardNumber)) {
                    user.getUserCardsList().remove(i);
                    user.getUserCardsList().add(card);
                    break;
                }
            }
            user = this.userService.updateUser(user);
            responseEntity = new ResponseEntity(user, HttpStatus.OK);
        } catch (UserNotPresentException | CardNotFoundException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Some Internal Error Try after some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("transaction/{userId}/{cardNumber}/add")
    public ResponseEntity addTrasaction(@RequestBody Transaction transaction, @PathVariable("userId") String userId, @PathVariable("cardNumber") String cardNumber) {
        try {
            User user = this.userService.getUserById(userId);
            Card card = this.userService.getCard(cardNumber, user);

            if (card.getIsActivated().equals("true")) {
                if (card.getAccountBalance() >= transaction.getTransactionAmount()) {
                    Double todayTransaction = 0.0;
                    for (int i = 0; i < card.getTransactionList().size(); i++) {
                        if (card.getTransactionList().get(i).getTransactionDate().compareTo(transaction.getTransactionDate()) == 0) {
                            todayTransaction += card.getTransactionList().get(i).getTransactionAmount();
                        }
                    }
                    if (todayTransaction + transaction.getTransactionAmount() <= card.getDailyLimit()) {
                        card.getTransactionList().add(transaction);
                        card.setAccountBalance(card.getAccountBalance() - transaction.getTransactionAmount());
                        for (int i = 0; i < user.getUserCardsList().size(); i++) {
                            Card currentCard = user.getUserCardsList().get(i);
                            if (currentCard.getCardNumber().equals(cardNumber)) {
                                user.getUserCardsList().remove(i);
                                user.getUserCardsList().add(card);
                                break;
                            }
                        }
                        user = this.userService.updateUser(user);
                        responseEntity = new ResponseEntity(user, HttpStatus.OK);

                    } else {
                        responseEntity = new ResponseEntity("Daily Debit Limit Exceeded", HttpStatus.EXPECTATION_FAILED);
                    }
                } else {
                    responseEntity = new ResponseEntity("Insufficient Account Balance", HttpStatus.EXPECTATION_FAILED);
                }
            } else {
                responseEntity = new ResponseEntity("Card is Blocked", HttpStatus.EXPECTATION_FAILED);
            }
        } catch (UserNotPresentException | CardNotFoundException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Some Internal Error Try after some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}

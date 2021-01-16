package com.example.natwest.domain;

import java.util.List;

public class Card {

    private String cardNumber;
    private List<Transaction> transactionList;
    private String isActivated;
    private double dailyLimit;
    private double accountBalance;

    public Card() {
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public String getIsActivated() {
        return isActivated;
    }

    public void setIsActivated(String isActivated) {
        this.isActivated = isActivated;
    }

    public double getDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(double dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardNumber='" + cardNumber + '\'' +
                ", transactionList=" + transactionList +
                ", isActivated='" + isActivated + '\'' +
                ", dailyLimit=" + dailyLimit +
                ", accountBalance=" + accountBalance +
                '}';
    }

    public Card(String cardNumber, List<Transaction> transactionList, String isActivated, double dailyLimit, double accountBalance) {
        this.cardNumber = cardNumber;
        this.transactionList = transactionList;
        this.isActivated = isActivated;
        this.dailyLimit = dailyLimit;
        this.accountBalance = accountBalance;
    }
}

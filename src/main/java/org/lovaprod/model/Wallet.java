package org.lovaprod.model;

import org.lovaprod.utils.MoneyUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Wallet {
    private String id;
    private double balance;
    public static double MAX_TRANSACTION_AMOUNT=200_000_000;
    private User account;
    private TransactionHistory transactionHistory;

    public Wallet() {
        this.id = UUID.randomUUID().toString();
        this.balance = 0.0;
        this.transactionHistory = new TransactionHistory();
    }

    public String getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }


    public User getAccount() {
        return account;
    }

    public void setAccount(User account) {
        this.account = account;
    }

    public TransactionHistory getTransactionHistory() {
        return transactionHistory;
    }

    public void deposit(double amount) {
        if (MoneyUtils.isAmountValid(amount)){
            balance += amount;
            transactionHistory.addTransaction(amount, Transaction.TransactionType.DEPOSIT, "me");
        }
    }

    public boolean withdraw(double amount) {
        if(MoneyUtils.isAmountValid(amount, balance, MAX_TRANSACTION_AMOUNT)){
            balance -= amount;
            transactionHistory.addTransaction(amount, Transaction.TransactionType.WITHDRAW, "");
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount, User destinationUser) {
        if(MoneyUtils.isAmountValid(amount, balance, MAX_TRANSACTION_AMOUNT)){
            balance -= amount;
            destinationUser.getWallet().deposit(amount);
            transactionHistory.addTransaction(amount, Transaction.TransactionType.TRANSFER, destinationUser.getUsername());
            return true;
        }
        return false;
    }

}

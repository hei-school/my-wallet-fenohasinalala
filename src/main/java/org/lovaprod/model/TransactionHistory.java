package org.lovaprod.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionHistory {
    private String id;
    private List<Transaction> transactions;

    public TransactionHistory() {
        this.id = UUID.randomUUID().toString();
        this.transactions = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(double amount, Transaction.TransactionType type, String destination) {
        Transaction transaction = new Transaction(amount, type, destination);
        transactions.add(transaction);
    }
}


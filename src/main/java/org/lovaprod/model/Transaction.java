package org.lovaprod.model;

import org.lovaprod.utils.TimeUtils;

import java.util.UUID;

public class Transaction {
    private final String id;
    private final double amount;
    private final TransactionType type;
    private final String destination;
    private final String transactionDate;

    public Transaction(double amount, TransactionType type, String destination) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.type = type;
        this.destination = destination;
        this.transactionDate = TimeUtils.currentDatetime();
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public String getDestination() {
        return destination;
    }

    public String getTransactionDate() {
        return transactionDate;
    }


    public static enum TransactionType {
        DEPOSIT,
        WITHDRAW,
        TRANSFER
    }

}


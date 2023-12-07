package org.lovaprod.service;

import org.lovaprod.model.Transaction;
import org.lovaprod.model.User;
import org.lovaprod.utils.MoneyUtils;

import java.util.List;
import java.util.Scanner;

public class WalletService {


    public static void checkBalance(User user) {
        // Display user's wallet balance
        System.out.println("Wallet Balance: " + user.getWallet().getBalance() + MoneyUtils.MONEY_UNIT);
    }

    public static void addFunds(Scanner scanner, User user) {
        // Allow user to add funds to the wallet
        System.out.print("Enter the amount (in"+MoneyUtils.MONEY_UNIT+") to add: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (MoneyUtils.isAmountValid(amount)){
            user.getWallet().deposit(amount);
            System.out.println("Funds added successfully. New balance: " + user.getWallet().getBalance() + MoneyUtils.MONEY_UNIT);
        }
    }

    public static void withdrawMoney(Scanner scanner, User user) {
        // Allow user to withdraw money from the wallet
        System.out.print("Enter the amount (in"+MoneyUtils.MONEY_UNIT+") to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline

        if (user.getWallet().withdraw(amount)) {
            System.out.println("Withdrawal successful. New balance: " + user.getWallet().getBalance() + MoneyUtils.MONEY_UNIT);
        }
    }

    public static void peerToPeerTransfers(Scanner scanner, User user, List<User> users) {
        // Allow user to make peer-to-peer transfers
        System.out.print("Enter the destination username: ");
        String destinationUsername = scanner.nextLine();

        User destinationUser = AccountService.findUserByUsername(destinationUsername, users);

        if (destinationUser != null) {
            System.out.print("Enter the amount (in"+MoneyUtils.MONEY_UNIT+") to transfer: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline

            if (user.getWallet().withdraw(amount, destinationUser)) {
                System.out.println(amount + MoneyUtils.MONEY_UNIT + " send to : " + destinationUsername);
                System.out.println("Transfer successful. New balance: " + user.getWallet().getBalance() + MoneyUtils.MONEY_UNIT);
            }
        } else {
            System.out.println("User not found with the provided username.");
        }
    }

    public static void transactionHistory(User user) {
        // Display transaction history
        List<Transaction> transactions = user.getWallet().getTransactionHistory().getTransactions();

        System.out.println("Transaction History:");
        for (Transaction transaction : transactions) {
            System.out.println("Transaction ID: " + transaction.getId());
            System.out.println("Amount: " + transaction.getAmount() + MoneyUtils.MONEY_UNIT);
            System.out.println("Type: " + transaction.getType());
            System.out.println("Destination: " + transaction.getDestination());
            System.out.println("Transaction Date: " + transaction.getTransactionDate());
            System.out.println("------");
        }
    }
}

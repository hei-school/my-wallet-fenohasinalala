package org.lovaprod;

import org.lovaprod.model.Transaction;
import org.lovaprod.model.User;
import org.lovaprod.service.AuthenticationService;
import org.lovaprod.utils.MoneyUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<User> accountList = new ArrayList<>();
    private static AuthenticationService authManager = new AuthenticationService(accountList);
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        accountList.add(new User("mockUser1","mockPass1","mockUser1","","mockUser1@mai.com"));
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    authManager.signUpForm(scanner);
                    break;
                case 2:
                    User currentUser = authManager.signInForm(scanner);
                    if (currentUser != null) {
                        mainWalletMenu(currentUser);
                    }
                    break;
                case 3:
                    System.out.println("Exiting application. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println(" ");
        System.out.println("1. Sign Up");
        System.out.println("2. Sign In");
        System.out.println("3. Exit");
        System.out.println(" ");
        System.out.print("Enter your choice: ");
    }

    private static void mainWalletMenu(User currentUser) {
        while (true) {
            displayWalletMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    viewPersonalInformation(currentUser);
                    break;
                case 2:
                    editPersonalInformation(scanner,currentUser);
                    break;
                case 3:
                    changePassword(scanner,currentUser);
                    break;
                case 4:
                    checkBalance(currentUser);
                    break;
                case 5:
                    addFunds(currentUser);
                    break;
                case 6:
                    withdrawMoney(currentUser);
                    break;
                case 7:
                    peerToPeerTransfers(currentUser);
                    break;
                case 8:
                    transactionHistory(currentUser);
                    break;
                case 9:
                    return; // Return to the main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayWalletMenu() {
        System.out.println(" ");
        System.out.println("1. Account - View Personal Information");
        System.out.println("2. Account - Edit Personal Information");
        System.out.println("3. Account - Change Password");
        System.out.println("4. Check Balance");
        System.out.println("5. Add Funds");
        System.out.println("6. Withdraw Money");
        System.out.println("7. Peer-to-peer Transfers");
        System.out.println("8. Transaction History");
        System.out.println("9. Logout");
        System.out.println(" ");
        System.out.print("Enter your choice: ");
    }

    private static void viewPersonalInformation(User user) {
        // Display user's personal information
        System.out.println(" ");
        System.out.println("Personal Information:");
        System.out.println("Username: " + user.getUsername());
        System.out.println("First Name: " + user.getFirstName());
        System.out.println("Last Name: " + user.getLastName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Phone Number: " + user.getPhoneNumber());
    }

    private static void editPersonalInformation(Scanner scanner, User user) {
            // Get user input for sign-up
            System.out.print("Enter new Last Name: ");
            String lastName = scanner.nextLine();

            System.out.print("Enter new First Name: ");
            String firstName = scanner.nextLine();

            System.out.print("Enter new Email: ");
            String email = scanner.nextLine();

            System.out.print("Enter new PhoneNumber: ");
            String phoneNumber = scanner.nextLine();

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);

            viewPersonalInformation(user);

    }

    private static void changePassword(Scanner scanner, User user) {
        System.out.print("Enter current password: ");
        String currentPassword = scanner.nextLine();

        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();

        System.out.print("repeat new password: ");
        String repeatedPassword = scanner.nextLine();

        if (currentPassword.equals(user.getPassword()) && newPassword.equals(repeatedPassword)){
            user.setPassword(newPassword);
            System.out.println("Password updated");

        }else {
            System.out.println("Wrong password");
        }
    }

    private static void checkBalance(User user) {
        // Display user's wallet balance
        System.out.println("Wallet Balance: " + user.getWallet().getBalance() + MoneyUtils.MONEY_UNIT);
    }

    private static void addFunds(User user) {
        // Allow user to add funds to the wallet
        System.out.print("Enter the amount (in"+MoneyUtils.MONEY_UNIT+") to add: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (MoneyUtils.isAmountValid(amount)){
            user.getWallet().deposit(amount);
            System.out.println("Funds added successfully. New balance: " + user.getWallet().getBalance() + MoneyUtils.MONEY_UNIT);
        }
    }

    private static void withdrawMoney(User user) {
        // Allow user to withdraw money from the wallet
        System.out.print("Enter the amount (in"+MoneyUtils.MONEY_UNIT+") to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline

        if (user.getWallet().withdraw(amount)) {
            System.out.println("Withdrawal successful. New balance: " + user.getWallet().getBalance() + MoneyUtils.MONEY_UNIT);
        }
    }

    private static void peerToPeerTransfers(User user) {
        // Allow user to make peer-to-peer transfers
        System.out.print("Enter the destination username: ");
        String destinationUsername = scanner.nextLine();

        User destinationUser = findUserByUsername(destinationUsername);

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

    private static void transactionHistory(User user) {
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

    private static User findUserByUsername(String username) {
        // Find user by username in the account list
        for (User user : accountList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
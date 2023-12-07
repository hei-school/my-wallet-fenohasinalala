package org.lovaprod.service;

import org.lovaprod.model.User;

import java.util.List;
import java.util.Scanner;

public class AccountService {

    public static User findUserByUsername(String username, List<User> accountList) {
        // Find user by username in the account list
        for (User user : accountList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static void viewPersonalInformation(User user) {
        // Display user's personal information
        System.out.println(" ");
        System.out.println("Personal Information:");
        System.out.println("Username: " + user.getUsername());
        System.out.println("First Name: " + user.getFirstName());
        System.out.println("Last Name: " + user.getLastName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Phone Number: " + user.getPhoneNumber());
    }

    public static void editPersonalInformation(Scanner scanner, User user) {
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

    public static void changePassword(Scanner scanner, User user) {
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

}

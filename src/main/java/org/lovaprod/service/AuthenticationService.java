package org.lovaprod.service;

import org.lovaprod.model.User;

import java.util.List;
import java.util.Scanner;

public class AuthenticationService {
    private List<User> accountList;

    public AuthenticationService(List<User> accountList) {
        this.accountList = accountList;
    }

    public boolean signUpForm(Scanner scanner) {
        // Get user input for sign-up
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        return signUp(lastName, firstName, email, username, password);
    }

    public User signInForm(Scanner scanner) {
        // Get user input for sign-in
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        // Use AuthenticationManager for sign-in
        return signIn(username, password);
    }

    public boolean signUp(String lastName, String firstName, String email, String username, String password) {

        // Check if the username is already taken
        for (User user : accountList) {
            if (user.getUsername().equals(username)) {
                System.out.println("Username is already taken. Please choose a different username.");
                return false;
            }
        }

        // Create and add the user to the account list
        User newUser = new User(username, password, firstName, lastName, email);
        accountList.add(newUser);

        System.out.println("User created successfully! You can sign in now!");
        return true;
    }

    public User signIn(String username, String password) {

        for (User user : accountList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Sign In successful! You can manage you wallet now!");
                return user;
            }
        }

        System.out.println("Invalid credentials. Sign In failed.");
        return null;
    }

}

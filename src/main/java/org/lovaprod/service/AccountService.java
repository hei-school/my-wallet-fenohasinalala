package org.lovaprod.service;

import org.lovaprod.model.User;

import java.util.List;

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

}

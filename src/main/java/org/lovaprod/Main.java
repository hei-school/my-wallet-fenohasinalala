package org.lovaprod;

import org.lovaprod.model.User;
import org.lovaprod.service.AuthenticationService;
import org.lovaprod.service.WalletService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.lovaprod.service.AccountService.changePassword;
import static org.lovaprod.service.AccountService.editPersonalInformation;
import static org.lovaprod.service.AccountService.viewPersonalInformation;

public class Main {

    private static List<User> accountList = new ArrayList<>();
    private static AuthenticationService authManager = new AuthenticationService(accountList);
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        accountList.add(new User("mockUser1","mockPass1","mockUser1","","mockUser1@mai.com"));

        while (true) {
            displayHomeMenu();
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

    private static void displayHomeMenu() {
        System.out.println(" ");
        System.out.println("--- HOME ---");
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
                    WalletService.checkBalance(currentUser);
                    break;
                case 5:
                    WalletService.addFunds(scanner,currentUser);
                    break;
                case 6:
                    WalletService.withdrawMoney(scanner,currentUser);
                    break;
                case 7:
                    WalletService.peerToPeerTransfers(scanner,currentUser,accountList);
                    break;
                case 8:
                    WalletService.transactionHistory(currentUser);
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



}
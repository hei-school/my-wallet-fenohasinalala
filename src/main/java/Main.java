import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Wallet wallet = initializeWallet();

        while (true) {
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    displayItems(wallet);
                    break;
                case 2:
                    displayWalletCapacity(wallet);
                    break;
                case 3:
                    addItem(wallet, scanner);
                    break;
                case 4:
                    takeItem(wallet, scanner);
                    break;
                case 5:
                    displayTotalMoney(wallet);
                    break;
                case 6:
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
    }

    private static Wallet initializeWallet() {
        // For simplicity, let's initialize a wallet with a default capacity
        HashMap<Size, Integer> initialCapacity = new HashMap<>();
        initialCapacity.put(Size.SMALL, 20);
        initialCapacity.put(Size.MEDIUM, 20);
        initialCapacity.put(Size.LARGE, 30);
        return new Wallet("123", new ArrayList<>(), initialCapacity);
    }

    private static void displayMainMenu() {
        System.out.println("Home Menu:");
        System.out.println("1. Display a list of items from the wallet");
        System.out.println("2. Display the capacity available of the wallet");
        System.out.println("3. Add item");
        System.out.println("4. Take item");
        System.out.println("5. Display the sum of Money");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    private static void displayItems(Wallet wallet) {
        System.out.println("Items in the wallet:");
        List<Item> items = wallet.getItems();
        for (Item item : items) {
            item.viewItem();
        }
        System.out.println();
    }

    private static void displayWalletCapacity(Wallet wallet) {
        System.out.println("Wallet Capacity:");
        displaySpaceAvailable("Small", wallet, Size.SMALL);
        displaySpaceAvailable("Medium", wallet, Size.MEDIUM);
        displaySpaceAvailable("Large", wallet, Size.LARGE);
        System.out.println();
    }

    private static void displaySpaceAvailable(String sizeName, Wallet wallet, Size size) {
        int spaceAvailable = wallet.getSpaceAvailable(size);
        System.out.println("Space available for " + sizeName + ": " + spaceAvailable);
    }


    private static void addItem(Wallet wallet, Scanner scanner) {
        System.out.println("Adding item...");

        // Display item types
        System.out.println("Select the type of item:");
        System.out.println("1. ID card");
        System.out.println("2. Money");
        System.out.println("3. Bank Card");
        System.out.println("4. Driver License");
        System.out.println("5. Visitor Card");
        System.out.println("6. ID Photo");
        System.out.print("Enter your choice: ");
        int itemType = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        // Handle each item type
        switch (itemType) {
            case 1:
                addIDCard(wallet, scanner);
                break;
            case 2:
                // Print an error message for Money items
                System.out.println("ERROR: NOT IMPLEMENTED YET for Money items.");
                break;
                /*
            case 3:
                addBankCard(wallet, scanner);
                break;
            case 4:
                addDriverLicense(wallet, scanner);
                break;
            case 5:
                addVisitorCard(wallet, scanner);
                break;
            case 6:
                addIDPhoto(wallet, scanner);
                break;

                 */
            default:
                System.out.println("Invalid item type. Returning to the main menu.");
        }
    }

    private static void addIDCard(Wallet wallet, Scanner scanner) {
        System.out.println("Adding ID Card...");

        // Implement logic to gather information for an ID Card
        System.out.print("Enter state: ");
        String state = scanner.nextLine();
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter birthdate (yyyy-MM-dd): ");
        LocalDate birthdate = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter birth localisation: ");
        String birthLocalisation = scanner.nextLine();
        System.out.print("Enter ID number: ");
        String number = scanner.nextLine();

        // Create a new ID Card and add it to the wallet
        IDCard idCard = new IDCard("me", state, firstName, lastName, birthdate, birthLocalisation, number);

        wallet.add(idCard);
    }


    private static void addMoney(Wallet wallet, Scanner scanner) {
        // Implement logic to add money to the wallet
        // For simplicity, let's assume user provides necessary details
        System.out.println("Adding Money...");

        // Display supported currencies
        System.out.println("Supported currencies:");
        for (Currency currency : Currency.values()) {
            System.out.println(currency.name());
        }
        System.out.print("Select currency: ");
        String currencyInput = scanner.nextLine();
        Currency selectedCurrency = Currency.valueOf(currencyInput);

        Money money = new Money("me", selectedCurrency);
        wallet.add(money);
        System.out.println("Money added successfully.");
    }

    private static void takeItem(Wallet wallet, Scanner scanner) {
        System.out.println("Taking item...");

        // Implement logic to take an item from the wallet
        // For simplicity, let's assume user provides necessary details

        // Display item types
        System.out.println("Select the type of item to take:");
        System.out.println("1. ID card");
        System.out.println("2. Money");
        // Add cases for other item types
        System.out.print("Enter your choice: ");
        int itemType = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (itemType) {
            case 1:
                // Implement logic to take an ID card
                break;
            case 2:
                // Implement logic to take money
                break;
            // Add cases for other item types
            default:
                System.out.println("Invalid item type. Returning to the main menu.");
        }
    }

    private static void displayTotalMoney(Wallet wallet) {
        System.out.println("Total Money in the Wallet:");
        System.out.println();
    }
}

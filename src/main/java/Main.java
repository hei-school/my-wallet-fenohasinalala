import static service.WalletService.addBankCard;
import static service.WalletService.addDriverLicense;
import static service.WalletService.addIDCard;
import static service.WalletService.addIDPhoto;
import static service.WalletService.addMoney;
import static service.WalletService.addVisitorCard;
import static service.WalletService.takeMoney;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import model.Item;
import model.Money;
import model.Wallet;
import model.enums.Size;
import utils.ValidationUtils;

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Wallet wallet = initializeWallet();

    while (true) {
      displayMainMenu();
      String choiceTemp = scanner.nextLine();
      int min = 1;
      int max = 6;
      if (!ValidationUtils.isValidNumberAndValue(choiceTemp, min, max)) {
        continue;
      }
      int choice = Integer.parseInt(choiceTemp);

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
    System.out.println(" ");
    System.out.println("----- Home ------");
    System.out.println("1. Display a list of items from the wallet");
    System.out.println("2. Display the capacity available of the wallet");
    System.out.println("3. Add item");
    System.out.println("4. Take item");
    System.out.println("5. Display the sum of model.Money");
    System.out.println("6. Exit");
    System.out.print("Choose an option: ");
  }

  private static void displayItems(Wallet wallet) {
    System.out.println(" ");
    System.out.println("Items in the wallet:");
    List<Item> items = wallet.getItems();

    int count = 0;
    for (Item item : items) {
      count += 1;
      System.out.println("No. " + count);
      item.viewItem();
    }
    if (items.isEmpty()) {
      System.out.println("The wallet is empty");
    }
    System.out.println(" ");
  }


  private static void displayWalletCapacity(Wallet wallet) {
    System.out.println();
    System.out.println("model.Wallet Capacity:");
    displaySpaceAvailable("Small", wallet, Size.SMALL);
    displaySpaceAvailable("Medium", wallet, Size.MEDIUM);
    displaySpaceAvailable("Large", wallet, Size.LARGE);
  }

  private static void displaySpaceAvailable(String sizeName, Wallet wallet, Size size) {
    int spaceAvailable = wallet.getSpaceAvailable(size);
    System.out.println("Space available for " + sizeName + ": " + spaceAvailable);
  }


  private static void addItem(Wallet wallet, Scanner scanner) {
    System.out.println(" ");
    System.out.println("Adding item...");

    // Display item types
    System.out.println("Select the type of item:");
    System.out.println("1. ID card");
    System.out.println("2. model.Money");
    System.out.println("3. Bank Card");
    System.out.println("4. Driver License");
    System.out.println("5. Visitor Card");
    System.out.println("6. ID Photo");
    System.out.println("7. Return to main menu");
    System.out.println(" ");
    System.out.print("Enter your choice: ");
    String itemTypeTemp = scanner.nextLine();
    int min = 1;
    int max = 7;
    if (!ValidationUtils.isValidNumberAndValue(itemTypeTemp, min, max)) {
      return;
    }
    int itemType = Integer.parseInt(itemTypeTemp);

    // Handle each item type
    switch (itemType) {
      case 1:
        addIDCard(wallet, scanner);
        break;
      case 2:
        addMoney(wallet, scanner);
        break;
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
      case 7:
        System.out.println("Returning to the main menu.");
        return;
      default:
        System.out.println("Invalid item type. Returning to the main menu.");
    }
  }

  private static void takeItem(Wallet wallet, Scanner scanner) {
    System.out.println(" ");
    System.out.println("Taking item...");
    if (wallet.getItems().isEmpty()) {
      System.out.println("the wallet is empty");
      return;
    }
    // Display all items with numbers
    displayItems(wallet);

    // Get user input for the item number to take
    System.out.print("Enter the no. of the item to take: ");
    String itemNumberTemp = scanner.nextLine();
    int min = 1;
    int max = wallet.getItems().size();
    if (!ValidationUtils.isValidNumberAndValue(itemNumberTemp, min, max)) {
      return;
    }
    int itemNumber = Integer.parseInt(itemNumberTemp);

    // Check if the entered item number is valid
    List<Item> items = wallet.getItems();
    if (itemNumber > 0 && itemNumber <= items.size()) {
      // Confirm the action
      System.out.print("Are you sure you want to take this item? (yes/no): ");
      String confirmation = scanner.nextLine().toLowerCase();

      if (confirmation.equals("yes")) {
        // Remove the selected item from the wallet
        Item selectedItem = items.get(itemNumber - 1);

        if (selectedItem instanceof Money) {
          takeMoney((Money) selectedItem, wallet, scanner);
        } else {

          wallet.take(selectedItem);
          System.out.println("model.Item taken successfully.");
        }
      } else {
        System.out.println("Action canceled. Returning to the main menu.");
      }
    } else {
      System.out.println("Invalid item number. Returning to the main menu.");
    }
  }


  private static void displayTotalMoney(Wallet wallet) {
    System.out.println("Total model.Money in the model.Wallet:");
    List<Money> moneyList = wallet.getMoneyList();
    for (Money money : moneyList) {
      System.out.println("model.enums.Currency: " + money.getCurrency());
      System.out.println("Total amount: " + money.getTotalAmount());
      System.out.println("----------------");
    }
    if (moneyList.isEmpty()) {
      System.out.println("No money at all!");
    }
    System.out.println(" ");
  }


}

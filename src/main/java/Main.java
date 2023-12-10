import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Wallet wallet = initializeWallet();

    while (true) {
      displayMainMenu();
      String choiceTemp = scanner.nextLine();
      scanner.nextLine(); // Consume the newline character
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
    System.out.println("5. Display the sum of Money");
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
    System.out.println("Wallet Capacity:");
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
    System.out.println("2. Money");
    System.out.println("3. Bank Card");
    System.out.println("4. Driver License");
    System.out.println("5. Visitor Card");
    System.out.println("6. ID Photo");
    System.out.println("7. Return to main menu");
    System.out.println(" ");
    System.out.print("Enter your choice: ");
    String itemTypeTemp = scanner.nextLine();
    scanner.nextLine(); // Consume the newline character
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

  private static void addIDCard(Wallet wallet, Scanner scanner) {
    System.out.println(" ");
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
    IDCard idCard = new IDCard("me", state, firstName, lastName, birthdate, birthLocalisation,
        number);

    wallet.add(idCard);
  }


  private static void addMoney(Wallet wallet, Scanner scanner) {
    System.out.println(" ");
    System.out.println("Adding Money...");

    // Display supported currencies
    System.out.println("Supported currencies:");
    for (Currency currency : Currency.values()) {
      System.out.println(currency.name());
    }
    System.out.print("Select currency: ");
    String currencyInput = scanner.nextLine();
    Currency selectedCurrency = Currency.valueOf(currencyInput);
    Money money = wallet.getMoney(selectedCurrency);
    int no = 0;
    for (double v : selectedCurrency.getValues()) {
      no += 1;
      System.out.println("No. " + no + " - " + v);
    }
    System.out.print("enter the no. of the Money: ");
    String indexTemp = scanner.nextLine();
    int index = Integer.parseInt(indexTemp);
    double value = selectedCurrency.getValues()[index-1];

    System.out.print("Enter the number: ");
    String numberTemp = scanner.nextLine();
    int number = Integer.parseInt(numberTemp);
    int freeSpace = wallet.getSpaceAvailable(Size.LARGE);
    if (freeSpace < number) {
      System.out.println(
          "the number: " + number + " is more than the space available: " + freeSpace);
      return;
    }

    if (money == null) {
      Money temp = new Money("me", selectedCurrency);
      temp.addMoney(value, number);
      wallet.add(temp);
    } else {
      money.addMoney(value, number);
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
    int itemNumber = Integer.parseInt(itemNumberTemp);
    scanner.nextLine(); // Consume the newline character

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
          System.out.println("Item taken successfully.");
        }
      } else {
        System.out.println("Action canceled. Returning to the main menu.");
      }
    } else {
      System.out.println("Invalid item number. Returning to the main menu.");
    }
  }

  private static void takeMoney(Money selectedItem, Wallet wallet, Scanner scanner) {
    HashMap<Double, Integer> moneyList = (selectedItem).getPresentMoney();
    int no = 0;
    for (Map.Entry<Double, Integer> entry : moneyList.entrySet()) {
      no += 1;
      System.out.println("no." + no + " " + entry.getKey());
    }

    no = 0;

    System.out.println("Select the No. of the value to withdraw: ");
    String index = scanner.nextLine();
    double value = 0;
    for (Map.Entry<Double, Integer> entry : moneyList.entrySet()) {
      no += 1;
      if (Integer.parseInt(index) == no) {
        value = entry.getKey();
        break;
      }
    }
    Integer maxCount = selectedItem.getPresentMoney().get(value);
    System.out.println(
        "You can take up to : " + maxCount + " for " + selectedItem.getCurrency() + " " + value);
    System.out.println("Enter the money count: ");
    String moneyCountTemp = scanner.nextLine();
    Integer moneyCount = Integer.parseInt(moneyCountTemp);
    if (maxCount >= moneyCount) {
      wallet.getMoney(selectedItem.getCurrency()).retireMoney(value, moneyCount);
      System.out.println(
          "Successfully take " + maxCount + " x " + selectedItem.getCurrency() + " " + value);
    }

  }

  private static void addBankCard(Wallet wallet, Scanner scanner) {
    System.out.println(" ");
    System.out.println("Adding Bank Card...");

    // Get user input for Bank Card attributes
    System.out.print("Enter bank name: ");
    String bankName = scanner.nextLine();
    System.out.print("Enter offer: ");
    String offer = scanner.nextLine();
    System.out.print("Enter card number: ");
    String cardNumber = scanner.nextLine();
    System.out.print("Enter expiration date (yyyy-MM-dd): ");
    LocalDate expirationDate = LocalDate.parse(scanner.nextLine());
    System.out.print("Enter CVV number: ");
    String CVVNumber = scanner.nextLine();
    System.out.print("Enter card holder name: ");
    String cardHolderName = scanner.nextLine();

    // Create a BankCard object using the provided constructor
    BankCard bankCard = new BankCard("me", bankName, offer, cardNumber, expirationDate, CVVNumber,
        cardHolderName);

    // Add the BankCard to the wallet
    wallet.add(bankCard);

  }

  private static void addDriverLicense(Wallet wallet, Scanner scanner) {
    System.out.println("Adding Driver License...");

    // Get user input for Driver License attributes
    System.out.print("Enter state: ");
    String state = scanner.nextLine();
    System.out.print("Enter category (A, A1, B, BE, C, CE, D, DE): ");
    String categoryString = scanner.nextLine();

    // Convert the categoryString to DriverLicenseCategory
    DriverLicenseCategory category = DriverLicenseCategory.valueOf(categoryString.toUpperCase());

    // Create a DriverLicense object using the provided constructor
    DriverLicense driverLicense = new DriverLicense("me", state, category);

    // Add the DriverLicense to the wallet
    wallet.add(driverLicense);

  }


  private static void addVisitorCard(Wallet wallet, Scanner scanner) {
    System.out.println("Adding Visitor Card...");

    // Get user input for Visitor Card attributes
    System.out.print("Enter name: ");
    String name = scanner.nextLine();
    System.out.print("Enter email: ");
    String email = scanner.nextLine();
    System.out.print("Enter phone number: ");
    String phoneNumber = scanner.nextLine();
    System.out.print("Enter website: ");
    String website = scanner.nextLine();

    // Create a VisitorCard object using the provided constructor
    VisitorCard visitorCard = new VisitorCard("me", name, email, phoneNumber, website);

    // Add the VisitorCard to the wallet
    wallet.add(visitorCard);

  }


  private static void addIDPhoto(Wallet wallet, Scanner scanner) {

    scanner.nextLine(); // Consume the newline character
    System.out.print("Enter description: ");
    String description = scanner.nextLine();
    // Continue with other attributes

    IDPhoto idPhoto = new IDPhoto("me", description);
    wallet.add(idPhoto);
    System.out.println("ID Photo added successfully.");
  }

  private static void displayTotalMoney(Wallet wallet) {
    System.out.println("Total Money in the Wallet:");
    List<Money> moneyList = wallet.getMoneyList();
    for (Money money : moneyList) {
      System.out.println("Currency: " + money.getCurrency());
      System.out.println("Total amount: " + money.getTotalAmount());
      System.out.println("----------------");
    }
    if (moneyList.isEmpty()) {
      System.out.println("No money at all!");
    }
    System.out.println(" ");
  }
}

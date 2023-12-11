package service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import model.BankCard;
import model.DriverLicense;
import model.IDCard;
import model.IDPhoto;
import model.Money;
import model.VisitorCard;
import model.Wallet;
import model.enums.Currency;
import model.enums.DriverLicenseCategory;
import model.enums.Size;
import utils.ValidationUtils;

public class WalletService {

  public static void addIDCard(Wallet wallet, Scanner scanner) {
    System.out.println(" ");
    System.out.println("Adding ID Card...");

    // Implement logic to gather information for an ID Card
    System.out.print("Enter State (Country): ");
    String state = scanner.nextLine();
    System.out.print("Enter first name: ");
    String firstName = scanner.nextLine();
    System.out.print("Enter last name: ");
    String lastName = scanner.nextLine();
    System.out.print("Enter birthdate (yyyy-MM-dd): ");
    String birthdateTemp = scanner.nextLine();
    if (!ValidationUtils.isValidLocalDateInput(birthdateTemp)) {
      return;
    }
    LocalDate birthdate = LocalDate.parse(birthdateTemp);
    System.out.print("Enter birth localisation: ");
    String birthLocalisation = scanner.nextLine();
    System.out.print("Enter ID number: ");
    String number = scanner.nextLine();

    // Create a new ID Card and add it to the wallet
    IDCard idCard = new IDCard("me", state, firstName, lastName, birthdate, birthLocalisation,
        number);

    wallet.add(idCard);
  }


  public static void addMoney(Wallet wallet, Scanner scanner) {
    System.out.println(" ");
    System.out.println("Adding model.Money...");

    // Display supported currencies
    System.out.println("Supported currencies:");
    for (Currency currency : Currency.values()) {
      System.out.println(currency.name());
    }
    System.out.print("Select currency: ");
    String currencyInput = scanner.nextLine();
    if (!ValidationUtils.isPresentInEnumArray(currencyInput, Currency.values())) {
      return;
    }

    Currency selectedCurrency = Currency.valueOf(currencyInput);
    Money money = wallet.getMoney(selectedCurrency);
    int no = 0;
    for (double v : selectedCurrency.getValues()) {
      no += 1;
      System.out.println("No. " + no + " - " + v);
    }
    System.out.print("enter the no. of the model.Money: ");
    String indexTemp = scanner.nextLine();
    int min = 1;
    int max = no;
    if (!ValidationUtils.isValidNumberAndValue(indexTemp, min, max)) {
      return;
    }
    int index = Integer.parseInt(indexTemp);
    double value = selectedCurrency.getValues()[index - 1];

    System.out.print("Enter the number: ");
    String numberTemp = scanner.nextLine();
    if (!ValidationUtils.isValidPositiveNumber(numberTemp)) {
      return;
    }
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


  public static void takeMoney(Money selectedItem, Wallet wallet, Scanner scanner) {
    HashMap<Double, Integer> moneyList = (selectedItem).getPresentMoney();
    int no = 0;
    for (Map.Entry<Double, Integer> entry : moneyList.entrySet()) {
      no += 1;
      System.out.println("no." + no + " " + entry.getKey());
    }

    no = 0;

    System.out.println("Select the No. of the value to withdraw: ");
    String index = scanner.nextLine();
    int min = 1;
    int max = moneyList.size();
    if (!ValidationUtils.isValidNumberAndValue(index, min, max)) {
      return;
    }

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
    int min1 = 1;
    int max1 = maxCount;
    if (!ValidationUtils.isValidNumberAndValue(moneyCountTemp, min1, max1)) {
      return;
    }

    Integer moneyCount = Integer.parseInt(moneyCountTemp);
    if (maxCount >= moneyCount) {
      wallet.getMoney(selectedItem.getCurrency()).retireMoney(value, moneyCount);
      System.out.println(
          "Successfully take " + maxCount + " x " + selectedItem.getCurrency() + " " + value);
    }

  }

  public static void addBankCard(Wallet wallet, Scanner scanner) {
    System.out.println(" ");
    System.out.println("Adding Bank Card...");

    // Get user input for Bank Card attributes
    System.out.print("Enter bank name: ");
    String bankName = scanner.nextLine();
    System.out.print("Enter offer: ");
    String offer = scanner.nextLine();
    System.out.print("Enter card number: ");
    String cardNumber = scanner.nextLine();
    //validation

    int min = 12;
    int max = 19;
    if (!ValidationUtils.isValidNumberAndLength(cardNumber, min, max)) {
      return;
    }
    System.out.print("Enter expiration date (yyyy-MM-dd): ");
    String expirationDateTemp = scanner.nextLine();
    if (!ValidationUtils.isValidLocalDateInput(expirationDateTemp)) {
      return;
    }
    LocalDate expirationDate = LocalDate.parse(expirationDateTemp);
    System.out.print("Enter CVV number: ");
    String CVVNumber = scanner.nextLine();
    //validation
    int length = 3;
    if (!ValidationUtils.isValidNumberAndLength(CVVNumber, length)) {
      return;
    }

    System.out.print("Enter card holder name: ");
    String cardHolderName = scanner.nextLine();

    // Create a model.BankCard object using the provided constructor
    BankCard bankCard = new BankCard("me", bankName, offer, cardNumber, expirationDate, CVVNumber,
        cardHolderName);

    // Add the model.BankCard to the wallet
    wallet.add(bankCard);

  }

  public static void addDriverLicense(Wallet wallet, Scanner scanner) {
    System.out.println("Adding Driver License...");

    // Get user input for Driver License attributes
    System.out.print("Enter State (Countries): ");
    String state = scanner.nextLine();
    System.out.print("Enter category (A, A1, B, BE, C, CE, D, DE): ");
    String categoryString = scanner.nextLine();
    if (!ValidationUtils.isPresentInEnumArray(categoryString, DriverLicenseCategory.values())) {
      return;
    }
    // Convert the categoryString to model.enums.DriverLicenseCategory
    DriverLicenseCategory category = DriverLicenseCategory.valueOf(categoryString.toUpperCase());

    // Create a model.DriverLicense object using the provided constructor
    DriverLicense driverLicense = new DriverLicense("me", state, category);

    // Add the model.DriverLicense to the wallet
    wallet.add(driverLicense);

  }


  public static void addVisitorCard(Wallet wallet, Scanner scanner) {
    System.out.println("Adding Visitor Card...");

    // Get user input for Visitor Card attributes
    System.out.print("Enter name: ");
    String name = scanner.nextLine();
    System.out.print("Enter email: ");
    String email = scanner.nextLine();
    System.out.print("Enter phone number: ");
    String phoneNumber = scanner.nextLine();
    int min = 4;
    int max = 13;
    if (!ValidationUtils.isValidNumberAndLength(phoneNumber, min, max)) {
      System.out.print("Enter website: ");
    }
    String website = scanner.nextLine();

    // Create a model.VisitorCard object using the provided constructor
    VisitorCard visitorCard = new VisitorCard("me", name, email, phoneNumber, website);

    // Add the model.VisitorCard to the wallet
    wallet.add(visitorCard);

  }


  public static void addIDPhoto(Wallet wallet, Scanner scanner) {
    System.out.print("Enter description: ");
    String description = scanner.nextLine();
    // Continue with other attributes

    IDPhoto idPhoto = new IDPhoto("me", description);
    wallet.add(idPhoto);
    System.out.println("ID Photo added successfully.");
  }

}

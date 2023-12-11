import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ValidationUtils {

  public static boolean isValidNumber(String n) {
    try {
      Double.parseDouble(n);
      return true;
    } catch (NumberFormatException e) {
      System.out.println(n + " is not a valid number.");
      return false;
    }
  }

  public static boolean isPresentInEnumArray(String n, Enum[] list) {
    List<String> stringList = Arrays.stream(list).map(Enum::toString).toList();
    if (!stringList.contains(n)) {
      System.out.println("Wrong input: " + n);
      return false;
    }
    return true;
  }

  public static boolean hasValidLength(String n, int min, int max) {
    if (n.length() < min || n.length() > max) {
      System.out.println(n + " length must be between " + min + " and " + max + ".");
      return false;
    }
    return true;
  }

  public static boolean hasValidLength(String n, int length) {
    if (n.length() != length) {
      System.out.println(n + " length must be equal to " + length);
      return false;
    }
    return true;
  }

  public static boolean isValidPositiveNumber(String n) {
    if (!isValidNumber(n)) {
      return false;
    }
    int num = Integer.parseInt(n);
    if (num < 0) {
      System.out.println(n + " must be positive.");
      return false;
    }
    return true;
  }


  public static boolean isValidNumberAndValue(String n, int min, int max) {
    if (!isValidNumber(n)) {
      return false;
    }
    int num = Integer.parseInt(n);
    if (num < min || num > max) {
      System.out.println(n + " must be between " + min + " and " + max + ".");
      return false;
    }
    return true;
  }


  public static boolean isValidNumberAndLength(String n, int min, int max) {
    if (!isValidNumber(n)) {
      return false;
    }
    return hasValidLength(n, min, max);
  }

  public static boolean isValidNumberAndLength(String n, int length) {
    if (!isValidNumber(n)) {
      return false;
    }
    return hasValidLength(n, length);
  }

  public static boolean isValidLocalDateInput(String localDate) {
    try {
      LocalDate.parse(localDate);
      return true;
    } catch (Exception e) {
      System.out.println(localDate
          + " is an Invalid date format. Please enter a valid date in the format yyyy-MM-dd.");
      return false;
    }
  }

}

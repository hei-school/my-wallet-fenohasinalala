public class InputValidationUtils {

  public static boolean isValidNumber(String value) {
    if (value.matches("\\d+")) {
      return true;
    } else {
      System.out.println(value + " must be only a number");
      return false;
    }
  }

  public static boolean hasExactLength(String value, int expected) {
    if (value.length() == expected) {
      return true;
    } else {
      System.out.println(value + " must have a length of " + expected);
      return false;
    }
  }
}

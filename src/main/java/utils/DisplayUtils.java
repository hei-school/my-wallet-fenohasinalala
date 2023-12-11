package utils;

import java.time.LocalDate;

public class DisplayUtils {

  public static String formatNullOrEmptyValue(String value) {
    return (value == null || value.isEmpty()) ? " not specified" : value;
  }

  public static String formatNullOrEmptyValue(LocalDate value) {
    return (value == null) ? " not specified" : value.toString();
  }
}

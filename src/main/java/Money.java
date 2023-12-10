import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Money extends Item {

  private Currency currency;
  private HashMap<Double, Integer> presentMoney;

  public Money(String owner, Currency currency) {
    super(owner, "OK", Size.LARGE);
    this.currency = currency;
    this.presentMoney = new HashMap<>();
    // Initialize other attributes
  }


  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  public HashMap<Double, Integer> getPresentMoney() {
    return presentMoney;
  }

  public void setPresentMoney(HashMap<Double, Integer> presentMoney) {
    this.presentMoney = presentMoney;
  }

  public double getTotalAmount() {
    double totalAmount = 0.0;
    for (Map.Entry<Double, Integer> entry : presentMoney.entrySet()) {
      totalAmount += entry.getKey() * entry.getValue();
    }
    return totalAmount;
  }

  public int getTotalNumber() {
    int total = 0;
    for (Map.Entry<Double, Integer> entry : presentMoney.entrySet()) {
      total += entry.getValue();
    }
    return total;
  }

  public void addMoney(double value, int number) {
    presentMoney.put(value, presentMoney.getOrDefault(value, 0) + number);
  }

  public void retireMoney(double value, int number) {
    presentMoney.put(value, presentMoney.getOrDefault(value, 0) - number);
  }

  @Override
  public void viewItem() {
    System.out.println("Type: Money");
    System.out.println("Currency: " + currency);
    System.out.println("Present Money:");
    presentMoney.forEach((key, value) ->
        System.out.println(key + " x " + value + " = " + (key * value)));

    System.out.println("Owner: " + DisplayUtils.formatNullOrEmptyValue(super.getOwner()));
    System.out.println("Status: " + DisplayUtils.formatNullOrEmptyValue(super.getStatus()));
    System.out.println("Added date: " + super.getAddedDate());
    System.out.println("--------------");
  }

  private double convertStringValueToDouble(String value) {
    String validNumber = value.replaceAll("_", "");
    return Double.parseDouble(validNumber);
  }

  public void removeZeroValueEntries() {
    Iterator<Entry<Double, Integer>> iterator = presentMoney.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry<Double, Integer> entry = iterator.next();
      if (entry.getValue() == 0) {
        iterator.remove();
      }
    }
  }
}

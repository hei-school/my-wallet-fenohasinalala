package model;

import model.enums.Currency;
import model.enums.Size;
import utils.DisplayUtils;

public class Money extends Item {

  private Currency currency;

  private Integer itemCount;

  private double value;

  public Money(String owner, Currency currency, double value, Integer itemCount) {
    super(owner, "OK", Size.LARGE);
    this.currency = currency;
    this.value = value;
    this.itemCount = itemCount;
  }

  public double getValue() {
    return value;
  }

  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  public double getTotalAmount() {
    return value * itemCount;
  }

  public void addPartially(int number) {
    itemCount = itemCount + number;
  }

  public void retirePartially(int number) {
    itemCount = itemCount - number;
  }

  @Override
  public void viewItem() {
    System.out.println("Type: Money");
    System.out.println("Currency: " + currency);
    System.out.println("Value: " + value);
    System.out.println("Number: " + itemCount);

    System.out.println("Owner: " + DisplayUtils.formatNullOrEmptyValue(super.getOwner()));
    System.out.println("Status: " + DisplayUtils.formatNullOrEmptyValue(super.getStatus()));
    System.out.println("Added date: " + super.getAddedDate());
    System.out.println("--------------");
  }

  @Override
  public int getItemCount() {
    return itemCount;
  }
}

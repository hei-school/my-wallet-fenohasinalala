package model;

import java.time.LocalDate;
import model.enums.Size;
import utils.DisplayUtils;

public class BankCard extends Item {

  private String bankName;
  private String offer;
  private String cardNumber;
  private LocalDate expirationDate;
  private String CVVNumber;
  private String cardHolderName;

  public BankCard(String owner, String bankName, String offer, String cardNumber,
      LocalDate expirationDate, String CVVNumber, String cardHolderName) {
    super(owner, "OK", Size.MEDIUM_AND_THICK);
    this.bankName = bankName;
    this.offer = offer;
    this.cardNumber = cardNumber;
    this.expirationDate = expirationDate;
    this.CVVNumber = CVVNumber;
    this.cardHolderName = cardHolderName;
  }

  @Override
  public void viewItem() {
    System.out.println("Type: Bank card");
    System.out.println("Bank Name: " + DisplayUtils.formatNullOrEmptyValue(bankName));
    System.out.println("Offer: " + DisplayUtils.formatNullOrEmptyValue(offer));
    System.out.println("Card Number: " + DisplayUtils.formatNullOrEmptyValue(cardNumber));
    System.out.println("Expiration Date: " + DisplayUtils.formatNullOrEmptyValue(expirationDate));
    System.out.println("CVV Number: " + DisplayUtils.formatNullOrEmptyValue(CVVNumber));
    System.out.println("Card Holder Name: " + DisplayUtils.formatNullOrEmptyValue(cardHolderName));

    System.out.println("Owner: " + DisplayUtils.formatNullOrEmptyValue(super.getOwner()));
    System.out.println("Status: " + DisplayUtils.formatNullOrEmptyValue(super.getStatus()));
    System.out.println("Added date: " + super.getAddedDate());
    System.out.println("--------------");
  }

  @Override
  public int getItemCount() {
    return 1;
  }
}

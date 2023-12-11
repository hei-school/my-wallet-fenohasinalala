package model;

import model.enums.Size;
import utils.DisplayUtils;

public class VisitorCard extends Item {

  private String name;
  private String email;
  private String phoneNumber;
  private String website;
  private Integer itemCount;

  public VisitorCard(String owner, String name, String email, String phoneNumber, String website, Integer itemCount) {
    super(owner, "OK", Size.MEDIUM);
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.website = website;
    this.itemCount = itemCount;
  }

  @Override
  public void viewItem() {
    System.out.println("Name: " + DisplayUtils.formatNullOrEmptyValue(name));
    System.out.println("Email: " + DisplayUtils.formatNullOrEmptyValue(email));
    System.out.println("Phone Number: " + DisplayUtils.formatNullOrEmptyValue(phoneNumber));
    System.out.println("Website: " + DisplayUtils.formatNullOrEmptyValue(website));

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
